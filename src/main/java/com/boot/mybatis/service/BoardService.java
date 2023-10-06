package com.boot.mybatis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.dto.BoardFileDto;

public interface BoardService {

	// 게시물 리스트
	List<BoardDto> selectBoardList() throws Exception;
	
	// 게시물 등록
	public void insertBoard(BoardDto boardDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	
	// 게시글 조회, 게시글 조회수 증가
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception;
	// 게시글 수정
	public void updateBoard(BoardDto boardDto) throws Exception;

	public void deleteBoard(int boardIdx) throws Exception;
		
	//첨부파일 삭제
	public void deleteBoardFile(@Param("inx") int idx, @Param("boardIdx") int boardIdx);
}
