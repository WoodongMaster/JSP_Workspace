package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileHandler {
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	// 파일 이름과 경로를 받아서 파일을 삭제하는 메서드
	// return : int => 잘 삭제되었는지 체크용
	public int deleteFile(String imgFileName, String savePath) {
		boolean isDel=false; // 파일을 삭제하는 메서드의 리턴 타입이 boolean
		log.info(">>> deleteFile 메서드 접속");
		log.info(">>> imgFileName : "+imgFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imgFileName);
		File removeThumFile = new File(fileDir+File.separator+"th_"+imgFileName);
		
		//파일이 있는지(존재하는지) 확인되어야 삭제가능
		if(removeFile.exists() || removeThumFile.exists()) {
			isDel = removeFile.delete(); // 삭제가 됐다면 true 안됐다면 false
			log.info("img delete" + (isDel ? "Success" : "Fail"));
			if(isDel) { // 메인 이미지 파일이 지워졌으면 썸네일도 삭제
				isDel = removeThumFile.delete();			}
			log.info("img thumnail delete"+(isDel ? "Success" : "Fail"));
		}
		log.info(">>>> FileHandler remove over");
		return isDel?1:0;
	}
}
