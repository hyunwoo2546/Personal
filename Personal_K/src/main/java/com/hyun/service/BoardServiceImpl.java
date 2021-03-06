package com.hyun.service;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyun.dao.BoardDAO;
import com.hyun.util.FileUtils;
import com.hyun.vo.BoardVO;
import com.hyun.vo.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private FileUtils fileUtils;
	
	/* # 게시글 작성 */
	@Override
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception{
		boardDAO.write(vo,mpRequest);
		
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			boardDAO.insertFile(list.get(i));
		}
	}
	
	/* # 게시물 목록 조회 */
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
		return boardDAO.list(scri);
	}
	
	/* # 게시물 총 갯수 */
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return boardDAO.listCount(scri);
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
	
	/* # 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return boardDAO.selectFileList(bno);
	}
	
}
