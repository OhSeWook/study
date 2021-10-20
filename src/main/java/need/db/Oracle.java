package main.java.need.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Oracle implements MyDb {
	
	List<Column> list = new ArrayList<Column>();
	
	@Override
	public boolean containDateType() {
		for(Column c : list) {
			if("Date".equals(c.columnTypeToJava())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Column> getColumnList(String tableName) throws Exception{
		
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs  = null;
        
        StringBuffer sql = new StringBuffer(); 
        
 
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:TEST";
        String id = "TEST";
        String pw = "TEST!";
 
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
 
            conn = DriverManager.getConnection(url, id, pw);
            
            sql.append(" SELECT  cc.column_name, cc.comments, tc.data_type");
            sql.append(" FROM USER_COL_COMMENTS cc");
            sql.append(" INNER JOIN (SELECT  COLUMN_NAME , DATA_TYPE FROM all_tab_columns where table_name = ?) tc ON cc.column_name = tc.column_name");
            sql.append(" WHERE cc.TABLE_NAME = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, tableName);
            pstmt.setString(2, tableName);
            
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	Column c = new Column();
            	
            	String name = rs.getString(1);
            	if(name.contains("FRST_") || name.contains("UPDT_") ) {
            		continue;
            	}
            	
            	c.setColumnName(rs.getString(1));
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
            if( pstmt != null && !pstmt.isClosed()){
            	pstmt.close();
            }
            if( rs != null && !rs.isClosed()){
                rs.close();
            }
		}
		
        return list;
	}
	
}
