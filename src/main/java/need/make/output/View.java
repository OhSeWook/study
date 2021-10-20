package main.java.need.make.output;

import java.io.File;

import main.java.need.vo.PathVO;

public class View {

	public void makeViewFile(PathVO pathVO) throws Exception {
		
		String xmlFolderName = pathVO.getRootPath() + pathVO.getViewRootPath() + pathVO.getFolderName();
		
		File xmlFile = new File(xmlFolderName);
		if(!xmlFile.exists()) {
			xmlFile.mkdirs();
		}	
	}
}
