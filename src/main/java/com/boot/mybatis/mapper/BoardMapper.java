package com.boot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.dto.BoardFileDto;

@Mapper
public interface BoardMapper {

	List<BoardDto> selectBoardList() throws Exception;
	List<BoardFileDto> selectBoardFileList(int boardIdx) throws Exception;
	
	public void insertBoard(BoardDto boardDto) throws Exception;
	public void insertBoardFileList(List<BoardFileDto> list) throws Exception;
	public void updateHitCount(int boardIdx) throws Exception;
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	BoardFileDto selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx")int boardIdx);
	
	public void updateBoard(BoardDto boardDto) throws Exception;
	public void deleteBoard(int boardIdx) throws Exception;
	
	
	
}
