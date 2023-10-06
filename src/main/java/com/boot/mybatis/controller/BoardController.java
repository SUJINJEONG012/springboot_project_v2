package com.boot.mybatis.controller;



import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.boot.mybatis.dto.BoardDto;
import com.boot.mybatis.dto.BoardFileDto;
import com.boot.mybatis.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	//로그 설정
	//private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public ModelAndView openBoardList() throws Exception{
		
		log.debug("@@@ log 테스트 ");
		
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
	public String insertBoard(BoardDto boardDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardService.insertBoard(boardDto, multipartHttpServletRequest);
		log.info("@@@@@ 게시판 글쓰기 ");
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
	
	@RequestMapping("/downloadBoardFile")
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception{
		//데이터베이스에서 선택된 파일의 정보를 조회
		BoardFileDto boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
		
		if(ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();
			
			//메소드로 조회된 파일의 정보 중 stroedFilePath값을 이용해 실제 저장되어 있는 파일을 읽어온 후 byte[]형태로 변환
			byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));
			
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			//파일은 반드시 utf-8로 인코딩
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8")+"\";");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}
	}

	// 게시물 수정하기
	@RequestMapping("/updateboard")
	public String updateBoard(BoardDto boardDto) throws Exception {
		boardService.updateBoard(boardDto);
		return "redirect:/board/list";
	}
	
	// 게시물 삭제하기
	@RequestMapping("/deleteboard")
	public String deleteBoard(int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/deleteBoardFile")
	public String deleteBoardFile(@RequestParam int idx, @RequestParam int boardIdx) throws Exception {
		boardService.deleteBoardFile(idx, boardIdx);
		return "redirect:/board/boarddetail?boardIdx="+boardIdx;
	}
	
}
