package secretary.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import secretary.board.model.BoardReplyVO;
import secretary.board.service.BoardService;

@RestController
@RequestMapping(value = "/restBoard")
public class RestBoardController {

	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/listReply.do", method = RequestMethod.POST)
	public List<BoardReplyVO> getReplyList(@RequestParam("boardId") String boardId) throws Exception {
		return boardService.getReplyList(boardId);
	}

	@RequestMapping(value = "/saveReply.do", method = RequestMethod.POST)
	public Map<String, Object> saveReply(@RequestBody BoardReplyVO replyVO) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			boardService.saveReply(replyVO);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}

		return result;
	}

	@RequestMapping(value = "/deleteReply.do", method = RequestMethod.POST)
	public Map<String, Object> deleteReply(@RequestParam("id") String id) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			boardService.deleteReply(id);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}

		return result;
	}
}
