package com.boot.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.mapper.BoardMapper;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	
//	@Autowired
//	private TransactionManager transactionManager;
	
	
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
		// 트랜잭션 테스트 하기 위해 넣은  값 int i = 10 /0 ; 
		
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
