package main.java.need.mig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.need.db.Column;

public class ShowColumn {

	public List<Column> getColumnList(String tableName) throws Exception{
		List<Column> list = new ArrayList<Column>();
		
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs  = null;
        
        StringBuffer sql = new StringBuffer(); 
 
        try {
        	String connectionUrl =
            		"jdbc:sqlserver://127.0.0.1:11434;"
                            + "database=TEST;"
                            + "user=TEST;"
                            + "password=TEST;";
        	
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionUrl);
            stmt = conn.createStatement();

		    sql.append(" SELECT ");
        	sql.append(" A.Column_Name, ");
        	sql.append(" B.value Colum_Description, ");
        	sql.append(" A.Data_Type  ");
	        sql.append(" FROM INFORMATION_SCHEMA.ColumnS A  ");
	        sql.append(" 	LEFT JOIN SYS.EXTENDED_PROPERTIES B  ");
	        sql.append(" 		ON B.Major_Id = OBJECT_ID(A.Table_Name)  ");
	        sql.append(" 		AND B.Minor_Id = A.Ordinal_Position  ");
	        sql.append(" 	LEFT JOIN ( SELECT OBJECT_ID(Objname) Table_Id,Value FROM ::FN_LISTEXTENDEDPROPERTY(NULL, 'user','dbo','Table',NULL, NULL, NULL) ) c "); 
	        sql.append(" 		ON C.Table_id = OBJECT_ID(A.Table_Name) ");
	        sql.append(" WHERE A.Table_Name = '"+ tableName +"' ");
	        sql.append(" ORDER BY A.Table_Name, A.Ordinal_Position ");
	        
            rs = stmt.executeQuery(sql.toString());
            while(rs.next()) {
            	
            	Column c = new Column();
            	String name = rs.getString(1);
            	
            	
            	if(name.contains("REGISTER_") || name.contains("REGIST_") || name.contains("UPDUSR_") || name.contains("UPDT_")) {
            		continue;
            	}
            	
            	c.setColumnName(name);
            	c.setComment(rs.getString(2));
            	c.setColumnType(rs.getString(3));
            	list.add(c);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
        	if( conn != null && !conn.isClosed()){
                conn.close();
            }
            if( stmt != null && !stmt.isClosed()){
            	stmt.close();
            }
            if( rs != null && !rs.isClosed()){
                rs.close();
            }
		}
		
        return list;
	}
	
}
