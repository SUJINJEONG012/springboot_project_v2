package com.boot.mybatis.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {

	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdId;
	private LocalDateTime createdDateTime;
	private String updaterId;
	private LocalDateTime updatedDateTime;
	
	private List<BoardFileDto> fileList;
	
}
