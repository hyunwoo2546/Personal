package com.hyun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyun.dao.BoardDAO;
import com.hyun.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void write(BoardVO vo) throws Exception{
		
		boardDAO.write(vo);
		
	}
	
}
