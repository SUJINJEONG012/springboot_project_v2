package com.boot.mybatis.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boot.mybatis.dto.BoardDto;

public interface BoardService {

	// 게시물 리스트
	List<BoardDto> selectBoardList() throws Exception;
	
	// 게시물 등록
	public void insertBoard(BoardDto boardDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	
	// 게시글 조회, 게시글 조회수 증가
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	// 게시글 수정
	public void updateBoard(BoardDto boardDto) throws Exception;

	public void deleteBoard(int boardIdx) throws Exception;
	
	
}
