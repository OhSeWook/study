package main.java.need.make.write.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import main.java.need.make.FolderFileAble;
import main.java.need.vo.FileVO;

public class Service extends FolderFileAble {
	
	@Override
	public String toString() {	
		return "Service";
	}

	@Override
	public void fileWrite(FileVO fileVO) throws Exception {

		String fullPath = fileVO.getFullPath();
		String fileName = fileVO.getFileName();
		String packagePath = fileVO.getPackagePath();
		String orgFileNm = fileVO.getOrgFileNm();
		
		String stringPackagePath = packagePath.replaceAll("/", ".").substring(6, packagePath.length());
		String importPath = stringPackagePath.replace(".service", "");
		
		
		try {

			BufferedWriter file = new BufferedWriter(new FileWriter(fullPath, true));

			file.write("package " + stringPackagePath + ";");
			
			file.newLine();
			file.newLine();
			
			file.write("import egovframework.com.cmm.service.GenericService;");
			file.newLine();
			file.write("import egovframework.rte.psl.dataaccess.util.EgovMap;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "Vo;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "DefaultVo;");
			file.newLine();
			file.newLine();
			file.write("public interface " + fileName + " extends GenericService<EgovMap, " + orgFileNm + "Vo, " + orgFileNm + "DefaultVo> {");
			file.newLine();
			file.newLine();
			file.write("}");

			file.close();
		} catch (IOException e) {
			System.out.println("파일 쓰기 에러.........." + fileName);
			throw e;
		}
		
		makeImpl(fileVO);
	}

	private void makeImpl(FileVO fileVO) throws Exception {
		
		String subFileName = fileVO.getFileName() + "Impl";
		String subFolderName = fileVO.getFolderName() + "/impl";
		String subFullPath = subFolderName + "/" + subFileName  + ".java";
		String orgFileNm = fileVO.getOrgFileNm();
		
		String packagePath = fileVO.getPackagePath();
		String stringPackagePath = packagePath.replaceAll("/", ".").substring(6, packagePath.length());
		String importPath = stringPackagePath.replace(".service", "");
		
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

			file.write("package " + stringPackagePath + ".impl;");
			
			file.newLine();
			file.newLine();
			
			file.newLine();
			file.write("import org.springframework.stereotype.Service;");
			file.newLine();
			file.write("import egovframework.com.cmm.service.impl.GenericServiceImpl;");
			file.newLine();
			file.write("import egovframework.rte.psl.dataaccess.util.EgovMap;");
			file.newLine();
			
			file.write("import "+importPath + ".mapper." + orgFileNm + "Mapper;");
			file.newLine();
			file.write("import "+importPath + ".service." + orgFileNm + "Service;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "Vo;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "DefaultVo;");
			
			file.newLine();
			file.newLine();
			file.write("@Service");
			file.newLine();
			file.write("public class " + subFileName + " extends GenericServiceImpl<" + orgFileNm+ "Mapper, EgovMap, " + orgFileNm + "Vo, " + orgFileNm + "DefaultVo> implements " + orgFileNm + "Service {");
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
