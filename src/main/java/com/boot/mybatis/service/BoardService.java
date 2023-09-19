package com.boot.mybatis.service;

import java.util.List;

import com.boot.mybatis.dto.BoardDto;

public interface BoardService {

	// 게시물 리스트
	List<BoardDto> selectBoardList() throws Exception;
	
	// 게시물 등록
	public void insertBoard(BoardDto boardDto) throws Exception;
	
	// 게시글 조회, 게시글 조회수 증가
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
			
}
