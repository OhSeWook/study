package main.java.need.make.write.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import main.java.need.db.Column;
import main.java.need.db.MyDb;
import main.java.need.make.FolderFileAble;
import main.java.need.vo.FileVO;

public class VO extends FolderFileAble {
	
	private MyDb myDb;
	
	public VO(MyDb myDb) {
		this.myDb = myDb;
	}

	@Override
	public String toString() {
		return "Vo";
	}

	@Override
	public void fileWrite(FileVO fileVO) throws Exception {
		String fullPath = fileVO.getFullPath();
		String fileName = fileVO.getFileName();
		String packagePath = fileVO.getPackagePath();
//		String orgFileNm = fileVO.getOrgFileNm();
		
		String stringPackagePath = packagePath.replaceAll("/", ".").substring(6, packagePath.length());
		String subFileName = fileVO.getFileName().replace("Vo","") + "DefaultVo";
		try {
			
//			BufferedWriter file = new BufferedWriter(new FileWriter(fullPath, true));
			BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fullPath), "UTF-8"));
			
			List<Column> columnList = myDb.getColumnList(fileVO.getTableName());

			file.write("package " + stringPackagePath + ";");
			file.newLine();
			file.newLine();
			
			if(myDb.containDateType()) {
				file.write("import java.util.Date;");
				file.newLine();	
			}
			
			file.newLine();
			file.write("public class " + fileName + " extends " + subFileName +" {");
			file.newLine();
			file.newLine();
			
				
			for(Column c : columnList) {
				file.write("	/*");
				if( c.getComment() != null ) {
					file.write(c.getComment());
				}
				file.write("*/");
				file.newLine();
				file.write("	private " +c.columnTypeToJava() + " " + c.columnNameToLower()+";");
				file.newLine();
				file.newLine();
			}
			
			file.write("}");

			file.close();
		} catch (IOException e) {
			System.out.println("파일 쓰기 에러.........." + fileName);
			throw e;
		}
		
		makeSearchVO(fileVO);
	}

	
	private void makeSearchVO(FileVO fileVO) throws Exception {
		
		String subFileName = fileVO.getFileName().replace("Vo","") + "DefaultVo";
		String subFolderName = fileVO.getFolderName();
		String subFullPath = subFolderName + "/" + subFileName + ".java";
//		String orgFileNm = fileVO.getOrgFileNm();
		
		String packagePath = fileVO.getPackagePath();
		String stringPackagePath = packagePath.replaceAll("/", ".").substring(6, packagePath.length());
		
		File subFile = new File(subFolderName);
		if(!subFile.exists()) {
			subFile.mkdirs();
		}	
		
		try {
			FileOutputStream fos = new FileOutputStream(subFullPath);
			fos.close();
			System.out.println("파일 생성................" + subFullPath);
			
		} catch (Exception e) {
			System.out.println("Java 파일 생성 에러................" + subFullPath);
			throw e;
		}
		
		try {

			BufferedWriter file = new BufferedWriter(new FileWriter(subFullPath, true));

			file.write("package " + stringPackagePath + ";");
			
			file.newLine();
			file.newLine();
			file.write("import egovframework.com.cmm.vo.PaginationVo;");
			file.newLine();
			file.newLine();
			file.write("public class " + subFileName + " extends PaginationVo {");
			file.newLine();
			file.newLine();
			file.write("}");

			file.close();
		} catch (IOException e) {
			System.out.println("파일 쓰기 에러.........." + subFileName);
			throw e;
		}
		
	}

}
