package com.boot.mybatis.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//@Transactional
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
	public void insertBoard(BoardDto boardDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		
		//boardMapper.insertBoard(boardDto);
		if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name = iterator.next();
				log.info("File tag Name : " + name);
				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile : list) {
					log.info("@@  Start file information");
					log.info(" file name : " + multipartFile.getOriginalFilename());
					log.info(" file size : " + multipartFile.getSize());
					log.info(" file content type : " + multipartFile.getContentType() );
					log.info("@@ End file information.\n");
				}
			}
			
		}
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
