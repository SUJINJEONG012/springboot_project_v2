package com.boot.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {		
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto boardDto) throws Exception {
		boardMapper.insertBoard(boardDto);
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		//조회수 증가
		boardMapper.updateHitCount(boardIdx);
		// 선택된 게시글 내용 조회
		BoardDto boardDto = boardMapper.selectBoardDetail(boardIdx);
		return boardDto;
	}

	@Override
	public void updateBoard(BoardDto boardDto) throws Exception {
		boardMapper.updateBoard(boardDto);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
		
	}

	
}
