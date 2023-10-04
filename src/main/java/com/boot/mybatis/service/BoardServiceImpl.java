
package com.boot.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boot.mybatis.common.FileUtils;
import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.dto.BoardFileDto;
import com.boot.mybatis.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	
//	@Autowired
//	private TransactionManager transactionManager;
	
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {		
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto boardDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		
		boardMapper.insertBoard(boardDto);
		
		List<BoardFileDto> list = fileUtils.parseFileInfo(boardDto.getBoardIdx(), multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false) {
			boardMapper.insertBoardFileList(list);
		}
		
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		// 선택된 게시글 내용 조회
		BoardDto boardDto = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		boardDto.setFileList(fileList);
		
		//조회수 증가
		boardMapper.updateHitCount(boardIdx);
		// 트랜잭션 테스트 하기 위해 넣은  값 int i = 10 /0 ; 
				
		return boardDto;
	}
	
	@Override
	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
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
