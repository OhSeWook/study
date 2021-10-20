package main.java.need.make.output;

import main.java.need.db.Mssql;
import main.java.need.make.FolderFileAble;
import main.java.need.make.write.xml.XmlMapper;
import main.java.need.vo.FileVO;
import main.java.need.vo.PathVO;

public class Xml {

	public void makeXmlFile(PathVO pathVO) throws Exception {
		
		String fileName = pathVO.getFileName();
		String xmlName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1, fileName.length());
		String xmlFolderName = pathVO.getRootPath() + pathVO.getXmlRootPath() + pathVO.getFolderName();
		String xmlFullPath = xmlFolderName + "/" + xmlName  + "Mapper.xml";
		
		FileVO fileVO = new FileVO(); 
		fileVO.setOrgFileNm(fileName);
		fileVO.setFileName(xmlName);
		fileVO.setFolderName(xmlFolderName);
		fileVO.setFullPath(xmlFullPath);
		fileVO.setTableName(pathVO.getTableName());
		fileVO.setJavaRootPath(pathVO.getJavaRootPath() + pathVO.getFolderName());
		
		FolderFileAble f = new XmlMapper(new Mssql());
		f.fileOutput(xmlFullPath, xmlFolderName);
		f.fileWrite(fileVO);
	}
	
}
