package com.boot.mybatis.controller;

import java.util.List;

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

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public ModelAndView openBoardList() throws Exception{
		
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
		mv.addObject("board", boardDto);
		return mv;
	}
	
	
	
}
