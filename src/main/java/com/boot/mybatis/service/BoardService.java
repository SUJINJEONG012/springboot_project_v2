package com.boot.mybatis.service;

import java.util.List;

import com.boot.mybatis.dto.BoardDto;

public interface BoardService {

	List<BoardDto> selectBoardList() throws Exception;
	
	public void insertBoard(BoardDto boardDto) throws Exception;
}
