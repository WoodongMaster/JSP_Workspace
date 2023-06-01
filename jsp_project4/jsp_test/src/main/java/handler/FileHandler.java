package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileHandler {
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	public int deleteFile(String imgFileName, String savePath) {
		boolean isDel=false;
		log.info(">>> deleteFile 메서드 접속");
		log.info(">>> imgFileName : "+imgFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imgFileName);
		File removeThumFile = new File(fileDir+File.separator+"th_"+imgFileName);
		
		if(removeFile.exists() || removeThumFile.exists()) {
			isDel = removeFile.delete();
			log.info("img delete" + (isDel ? "Success" : "Fail"));
			if(isDel) {
				isDel = removeThumFile.delete();			}
			log.info("img thumnail delete"+(isDel ? "Success" : "Fail"));
		}
		log.info(">>>> FileHandler 삭제 끗");
		return (isDel?1:0);
	}
}
