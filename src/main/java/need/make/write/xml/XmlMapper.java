package main.java.need.make.write.xml;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import main.java.need.db.Column;
import main.java.need.db.MyDb;
import main.java.need.make.FolderFileAble;
import main.java.need.vo.FileVO;

public class XmlMapper extends FolderFileAble {
	
	private MyDb myDb;
	
	public XmlMapper(MyDb myDb) {
		this.myDb = myDb;
	}

	@Override
	public void fileWrite(FileVO fileVO) throws Exception {
		
		String namespace = (fileVO.getJavaRootPath().replace("/java", "") + ".mapper." + fileVO.getFileName()+"Mapper").replaceAll("/", ".");

		try {

			BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileVO.getFullPath()), "UTF-8"));
			
			file.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			file.newLine();
			file.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
			file.newLine();
			file.write("<mapper namespace=\"" +  namespace.substring(1,namespace.length()) + "\">");
			file.newLine();
			file.newLine();
			
			if(fileVO.getTableName().isEmpty() == false) {
				List<Column> columnList = myDb.getColumnList(fileVO.getTableName());
				
				if(columnList.size() > 0) {
					file.write("	<select id=\"selectList\" resultType=\"egovMap\">");
					file.newLine();
					file.write("		SELECT");
					file.newLine();
					for(int i=0; i<columnList.size(); i++) {
						Column c = columnList.get(i);
						String name = (i == 0) ? c.getColumnName().toLowerCase() : ", " + c.getColumnName().toLowerCase();
						file.write("			"+name);
						file.newLine();
					}
					file.write("		FROM " + fileVO.getTableName());
					file.newLine();
					file.write("		WHERE 1=1");
					file.newLine();
					file.write("	</select>");
					file.newLine();
					file.newLine();
					
					//insert
					file.write("	<insert id=\"insert\">");
					file.newLine();
					file.write("		INSERT INTO "+fileVO.getTableName() + " (");
					file.newLine();
					
					for(int i=0; i<columnList.size(); i++) {
						Column c = columnList.get(i);
						
						String comment = "";
						if(c.getComment() != null && c.getComment().isEmpty() == false) {
							comment = c.getComment();
						}
						
						String name = (i == 0) ? c.getColumnName().toLowerCase() : ", " + c.getColumnName().toLowerCase();
						file.write("			"+name+ "		/* "+comment +" */");
						file.newLine();
					}
					file.write("		) VALUES (");
					file.newLine();
					for(int i=0; i<columnList.size(); i++) {
						Column c = columnList.get(i);
						String name = (i == 0) ? "#{" + c.columnNameToLower() + "}" : ", #{" + c.columnNameToLower() + "}";
						file.write("			"+name);
						file.newLine();
					}
					file.write("		)");
					file.newLine();
					file.write("	</insert>");					
					file.newLine();
					file.newLine();
					
					//update
					file.write("	<update id=\"update\">");
					file.newLine();
					file.write("		UPDATE "+fileVO.getTableName());
					file.newLine();
					file.write("		SET");
					file.newLine();
					
					for(int i=0; i<columnList.size(); i++) {
						Column c = columnList.get(i);
						String name = (i == 0) ? c.getColumnName().toLowerCase() : ", " + c.getColumnName().toLowerCase();
						String param = c.columnNameToLower();
						file.write("			"+name+ " = #{" + param +"}");
						file.newLine();
					}
					
					file.write("		WHERE " +columnList.get(0).getColumnName().toLowerCase()+ " = #{" + columnList.get(0).columnNameToLower() +"}");
					file.newLine();
					file.write("	</update>");
					file.newLine();
					file.newLine();
					
					//delete
					file.write("	<delete id=\"delete\">");
					file.newLine();
					file.write("		DELETE");
					file.newLine();
					file.write("		FROM " + fileVO.getTableName());
					file.newLine();
					file.write("		WHERE " +columnList.get(0).getColumnName().toLowerCase()+ " = #{" + columnList.get(0).columnNameToLower() +"}");
					file.newLine();
					file.write("	</delete>");
					file.newLine();
				}
				
			}
			file.newLine();
			file.write("</mapper>");

			file.close();
		} catch (IOException e) {
			System.out.println("Xml 파일 쓰기 에러.........." + fileVO.getFileName());
			throw e;
		}
		
	}

}
