package com.boot.mybatis.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	//로그 설정
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public ModelAndView openBoardList() throws Exception{
		
		log.info("log 테스트 ");
		
		ModelAndView mv = new ModelAndView("/board/boardlist");		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		return mv;	
	}
	
	@GetMapping("/write")
	public String boardWrite() throws Exception{
		return "/board/boardwrite";
	}
	
	@PostMapping("/insertboard")
	public String insertBoard(BoardDto boardDto) throws Exception {
		boardService.insertBoard(boardDto);
		return "redirect:/board/list";
	}
	
	// 글 상세 내용
	@GetMapping("/boarddetail")
	public ModelAndView boardDetail(@RequestParam int boardIdx) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boarddetail");
		
		BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
		mv.addObject("boardDto", boardDto);
		return mv;
	}
	
	// 게시판 수정하기
	@RequestMapping("/updateboard")
	public String updateBoard(BoardDto boardDto) throws Exception {
		boardService.updateBoard(boardDto);
		return "redirect:/board/list";
	}
	
	// 게시판 삭제하기
	@RequestMapping("/deleteboard")
	public String deleteBoard(int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/list";
	}
}
