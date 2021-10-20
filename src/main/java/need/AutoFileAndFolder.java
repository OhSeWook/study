package main.java.need;

import java.io.PrintWriter;
import java.io.StringWriter;

import main.java.need.vo.PathVO;

public class AutoFileAndFolder {

	public String start(String rootPath, String javaRootPath, String xmlRootPath, String viewRootPath, String filePath, String tableName) {
		
		String fileName = filePath;
		String folderName = filePath;
		
		PathVO p = new PathVO();
		p.setRootPath(rootPath);
		p.setJavaRootPath(javaRootPath);
		p.setXmlRootPath(xmlRootPath);
		p.setViewRootPath(viewRootPath);
		p.setTableName(tableName);
		p.setFolderName(folderName);
		p.setFileName(fileName);
		
		String result = "success";
		try {
			Provider provider = new Provider(); 
			provider.make(p);
		} catch (Exception e) {
			result = e.getMessage() + "\n";
			result = getPrintStackTrace(e);
		}
		
		return result;
	}
	
	public static String getPrintStackTrace(Exception e) {
        
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
	
}
