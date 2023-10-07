package com.boot.mybatis.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.dto.BoardFileDto;
import com.boot.mybatis.service.BoardService;

@Controller
public class RestBoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ModelAndView  openBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("/board/restBoardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String boardWrite() throws Exception{
		return "/board/restBoardWrite";
	}
	
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.GET)
	public ModelAndView boardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
			ModelAndView mv = new ModelAndView("/board/restBoardDedatil");
			BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
			mv.addObject("boardDto", boardDto);
			return mv;
	}
	
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.PUT)
	public String updateBoard(BoardDto boardDto) throws Exception{
		boardService.updateBoard(boardDto);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.DELETE)
	public String deleteBaord(@PathVariable("boardIdx")int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/baord/file", method=RequestMethod.GET)
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception {
		BoardFileDto boardFileDto = boardService.selectBoardFileInformation(idx, boardIdx);
		if(ObjectUtils.isEmpty(boardFileDto) == false) {
			String fileName = boardFileDto.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File(boardFileDto.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
	
	@RequestMapping(value="/board/file", method=RequestMethod.DELETE)
	public String deleteBoardFile(@RequestParam int idx, @RequestParam int boardIdx) throws Exception {
		boardService.deleteBoardFile(idx , boardIdx);
		return "redirect:/board/"+boardIdx;
	}
	
}
