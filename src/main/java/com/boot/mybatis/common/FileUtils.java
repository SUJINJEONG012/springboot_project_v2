package com.boot.mybatis.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boot.mybatis.dto.BoardFileDto;


// FileUtils클래스를 스프링 빈으로 등록
@Component
public class FileUtils {

	public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		
		
		List<BoardFileDto> fileList = new ArrayList<>();
		
		//파일이 업로드 될 폴더를 생성
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()) {
			
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			
			for(MultipartFile multipartFile : list) {
			//파일형식을 확인하고 그에 따라 이미지 확장자를 지정
				if(multipartFile.isEmpty() == false) {
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					}else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}else {
							break;
						}					
					}
					//파일 형식 확인하기 위함
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					
					// 데이터베이스에 저장할 팔일 정보를 앞에서 만든 BoardFileDto
					BoardFileDto boardFile = new BoardFileDto();
					boardFile.setBoardIdx(boardIdx);
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
					boardFile.setStoredFilePath(path + "/" + newFileName);
					fileList.add(boardFile);
					//서버에 저장될 파일 이름을 생성
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		
		return fileList;
	}
}
