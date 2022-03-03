package com.hyun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyun.vo.BoardVO;
import com.hyun.vo.SearchCriteria;

public interface BoardDAO {

	/* # 게시글 작성 */
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception;
	
	/* # 게시물 목록 조회 */
	public List<BoardVO> list(SearchCriteria scri) throws Exception;
	
	/* # 게시물 총 갯수 */
	public int listCount(SearchCriteria scri) throws Exception;
	
	/* # 특정 게시글 조회 */
	public BoardVO read(int bno) throws Exception;
	
	/* # 게시글 수정 */
	public void update(BoardVO vo) throws Exception;
	
	/* # 게시글 삭제 */
	public void delete(int bno) throws Exception;
	
	/* # 첨부파일 업로드 */
	public void insertFile(Map<String, Object> map) throws Exception;
	
	/* # 첨부파일 조회 */
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	
}
