package com.boot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.mybatis.dto.BoardDto;

@Mapper
public interface BoardMapper {

	List<BoardDto> selectBoardList() throws Exception;
	public void insertBoard(BoardDto boardDto) throws Exception;
	public void updateHitCount(int boardIdx) throws Exception;
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
}
