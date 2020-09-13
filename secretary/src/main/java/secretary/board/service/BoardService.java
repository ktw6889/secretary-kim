package secretary.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import secretary.board.model.BoardReplyVO;
import secretary.board.model.BoardVO;
import secretary.common.service.CommonService;

@Service
public class BoardService extends CommonService {

	public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception {
		logger.info("service selectBoardList");
		return sqlSession.selectList("seckim.board.select", boardVO);
	}

	// 총 게시글 개수 확인
	public int getBoardListCnt(BoardVO boardVO) throws Exception {
		return sqlSession.selectOne("seckim.board.listCnt", boardVO);
	}

	public BoardVO selectBoardInfo(String id) throws Exception {
		updateViewCnt(id);
		return sqlSession.selectOne("seckim.board.selectBoardInfo", id);
	}

	public BoardVO saveBoard(BoardVO boardVO) throws Exception {
		if (StringUtils.isEmpty(boardVO.getId())) {
			String id = getNewId("ST_BOARD");
			boardVO.setId(id);
			insertBoard(boardVO);
		} else {
			updateBoard(boardVO);
		}

		return boardVO;
	}

	public void insertBoard(BoardVO boardVO) throws Exception {
		sqlSession.insert("seckim.board.insert", boardVO);
	}

	public void updateBoard(BoardVO boardVO) throws Exception {
		sqlSession.update("seckim.board.update", boardVO);
	}

	public void deleteBoard(String id) throws Exception {
		sqlSession.update("seckim.board.delete", id);
	}

	public void updateViewCnt(String id) throws Exception {
		sqlSession.update("seckim.board.updateViewCnt", id);
	}

	public List<BoardReplyVO> getReplyList(String boardId) throws Exception {
		return sqlSession.selectList("seckim.board.listReply", boardId);
	}

	public BoardReplyVO saveReply(BoardReplyVO replyVO) throws Exception {
		if (StringUtils.isEmpty(replyVO.getId())) {
			String id = getNewId("ST_BOARD_REPLY");
			replyVO.setId(id);
			insertReply(replyVO);
		} else {
			updateReply(replyVO);
		}

		return replyVO;
	}

	public void insertReply(BoardReplyVO replyVO) throws Exception {
		sqlSession.insert("seckim.board.saveReply", replyVO);
	}

	public void updateReply(BoardReplyVO replyVO) throws Exception {
		sqlSession.update("seckim.board.updateReply", replyVO);
	}

	public void deleteReply(String id) throws Exception {
		sqlSession.update("seckim.board.deleteReply", id);
	}

}
