package secretary.board.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secretary.board.model.BoardReplyVO;
import secretary.board.model.BoardVO;
import secretary.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list")
	public String getBoardList(Model model, BoardVO boardVO,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range) throws Exception {
		// 전체 게시글 개수
		int listCnt = boardService.getBoardListCnt(boardVO);

		boardVO.pageInfo(page, range, listCnt);
		model.addAttribute("pagination", boardVO);
		model.addAttribute("boardList", boardService.selectBoardList(boardVO));
		return "kims:/board/boardList";
	}

	@RequestMapping(value = "/view")
	public String getBoardContent(Model model, @RequestParam("id") String id) throws Exception {
		model.addAttribute("boardContent", boardService.selectBoardInfo(id));
		model.addAttribute("replyVO", new BoardReplyVO());
		return "kims:/board/boardView";
	}

	@RequestMapping("/edit")
	public String editBoard(Model model, BoardVO boardVO) throws Exception {
		BoardVO resultVo = boardService.selectBoardInfo(boardVO.getId());
		if (resultVo == null || StringUtils.isEmpty(resultVo.getId())) {
			resultVo = new BoardVO();
		}
		model.addAttribute("boardVO", resultVo);
		return "kims:/board/boardEdit";
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public BoardVO saveBoard(BoardVO boardVO) throws Exception {
		BoardVO returnVo = boardService.saveBoard(boardVO);
		return returnVo;
	}

	@RequestMapping(value = "/delete")
	public String deleteBoard(@RequestParam("id") String id) throws Exception {
		boardService.deleteBoard(id);
		return "kims:/board/list.do";
	}

	@ExceptionHandler(RuntimeException.class)
	public String exceptionHandler(Model model, Exception e) {
		logger.info("exception : " + e.getMessage());
		model.addAttribute("exception", e);
		return "kims:/error/exception";
	}

}
