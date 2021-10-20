package main.java.need.make;

import java.io.File;
import java.io.FileOutputStream;

import main.java.need.vo.FileVO;

public abstract class FolderFileAble {
	
	public void fileOutput(String fullPath, String folderName) throws Exception{
		try {
			
			File file = new File(folderName);
			if(!file.exists()) {
				file.mkdirs();
			}	
			
			FileOutputStream fos = new FileOutputStream(fullPath);
			
			System.out.println("파일 생성................" + fullPath);
			
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 에러................" + fullPath);
			throw e;
		}
	}
	
	public abstract void fileWrite(FileVO fileVO) throws Exception;
}
