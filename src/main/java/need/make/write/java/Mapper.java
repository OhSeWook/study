package main.java.need.make.write.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.java.need.make.FolderFileAble;
import main.java.need.vo.FileVO;

public class Mapper extends FolderFileAble {
	
	@Override
	public String toString() {
		return "Mapper";
	}

	@Override
	public void fileWrite(FileVO fileVO) throws Exception {

		String fullPath = fileVO.getFullPath();
		String fileName = fileVO.getFileName();
		String packagePath = fileVO.getPackagePath();
		String orgFileNm = fileVO.getOrgFileNm();
		
		String stringPackagePath = packagePath.replaceAll("/", ".").substring(6, packagePath.length());
		String importPath = stringPackagePath.replace(".mapper", "");
		
		try {

			BufferedWriter file = new BufferedWriter(new FileWriter(fullPath, true));

			file.write("package " + stringPackagePath + ";");
			file.newLine();
			file.newLine();
			file.write("import egovframework.com.cmm.mapper.GenericMapper;");
			file.newLine();
			file.write("import egovframework.rte.psl.dataaccess.util.EgovMap;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "Vo;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "DefaultVo;");
			file.newLine();
			file.newLine();
			
			file.write("public interface " + fileName + " extends GenericMapper<EgovMap, " + orgFileNm + "Vo, " + orgFileNm + "DefaultVo> {");
			file.newLine();
			file.newLine();
			file.write("}");

			file.close();
		} catch (IOException e) {
			System.out.println("파일 쓰기 에러.........." + fileName);
			throw e;
		}
		
	}

}
