package main.java.need.make.write.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.java.need.make.FolderFileAble;
import main.java.need.vo.FileVO;

public class Controller extends FolderFileAble {
	
	@Override
	public String toString() {
		return "Controller";
	}

	@Override
	public void fileWrite(FileVO fileVO) throws Exception {

		String fullPath = fileVO.getFullPath();
		String fileName = fileVO.getFileName();
		String packagePath = fileVO.getPackagePath();
		String orgFileNm = fileVO.getOrgFileNm();
		
		String stringPackagePath = packagePath.replaceAll("/", ".").substring(6, packagePath.length());
		String importPath = stringPackagePath.replace(".controller", "");
		
		try {

			BufferedWriter file = new BufferedWriter(new FileWriter(fullPath, true));

			file.write("package " + importPath + ".web;");
			
			file.newLine();
			file.newLine();
			file.write("import org.springframework.stereotype.Controller;");
			file.newLine();
			file.write("import org.springframework.web.bind.annotation.GetMapping;");
			file.newLine();
			file.write("import org.springframework.web.bind.annotation.RequestMapping;");
			file.newLine();
			file.write("import egovframework.com.cmm.web.AbstractController;");
			file.newLine();
			file.write("import "+importPath + ".service." + orgFileNm + "Service;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "Vo;");
			file.newLine();
			file.write("import "+importPath + ".vo." + orgFileNm + "DefaultVo;");
			
			file.newLine();
			file.newLine();
			file.write("@Controller");
			file.newLine();
			file.write("@RequestMapping(value = \"\")");
			file.newLine();
			
			//기본
//			file.write("public class " + fileName + " {");
			
			//**누리웨어만 사용
			file.write("public class " + fileName + " ");
			file.write("extends AbstractController{");
			file.newLine();
			file.newLine();
			file.write("    @GetMapping(value = \"page\")");
			file.newLine();
			file.write("    public String getPage() throws Exception {");
			file.newLine();
			file.write("        return \"\"; ");
			file.newLine();
			file.write("    }");
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
