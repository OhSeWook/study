package main.java.need.make.output;

import main.java.need.db.Mssql;
import main.java.need.make.FolderFileAble;
import main.java.need.make.write.java.Controller;
import main.java.need.make.write.java.Mapper;
import main.java.need.make.write.java.Service;
import main.java.need.make.write.java.VO;
import main.java.need.vo.FileVO;
import main.java.need.vo.PathVO;

public class Java {
	
	final static FolderFileAble[] needJavaFolderNames = new FolderFileAble[]{new Controller(), new Service(), new Mapper(), new VO(new Mssql())};

	public void makeJavaFile(PathVO pathVO) throws Exception {
		
		String fileName = pathVO.getFileName();
		String reName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1, fileName.length());
		
		for(int i=0; i<needJavaFolderNames.length; i++) {
			
			String javaFileName = reName + needJavaFolderNames[i].toString();
			String javaFolderName = null;
			if("controller".equals(needJavaFolderNames[i].toString().toLowerCase())) {
				javaFolderName = pathVO.getRootPath() + pathVO.getJavaRootPath() + pathVO.getFolderName() + "/" + "web";
			}else {
				javaFolderName = pathVO.getRootPath() + pathVO.getJavaRootPath() + pathVO.getFolderName() + "/" + needJavaFolderNames[i].toString().toLowerCase();
			}
			
			String javaFullPath = javaFolderName + "/" + javaFileName  + ".java";
			String packagePath = pathVO.getJavaRootPath() + pathVO.getFolderName() + "/" + needJavaFolderNames[i].toString().toLowerCase();
			
			FileVO fileVO = new FileVO(); 
			fileVO.setOrgFileNm(reName);
			fileVO.setFileName(javaFileName);
			fileVO.setFolderName(javaFolderName);
			fileVO.setFullPath(javaFullPath);
			fileVO.setPackagePath(packagePath);
			fileVO.setTableName(pathVO.getTableName());
			
			FolderFileAble f = needJavaFolderNames[i];
			f.fileOutput(javaFullPath, javaFolderName);
			f.fileWrite(fileVO);
		}
	}
}
