package com.hyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyun.dao.BoardDAO;
import com.hyun.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	/* # 게시글 작성 */
	@Override
	public void write(BoardVO vo) throws Exception{
		boardDAO.write(vo);
	}
	
	/* # 게시물 목록 조회 */
	@Override
	public List<BoardVO> list() throws Exception {
		return boardDAO.list();
	}
	
	/* # 특정 게시글 조회 */
	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDAO.read(bno);
	}
	
	/* # 게시글 수정 */
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDAO.update(vo);
	}
	
	/* # 게시글 삭제 */
	@Override
	public void delete(int bno) throws Exception {
		boardDAO.delete(bno);
	}
	
}
