package com.multicampus.biz.board;

import java.sql.SQLException;
import java.util.List;

public class BoardDAOClient {

	public static void main(String[] args) throws SQLException {
		BoardDAO boardDAO = new BoardDAO();

		BoardVO vo = new BoardVO();
		vo.setTitle("iBATIS 제목");
		vo.setWriter("테스터");
		vo.setContent("iBATIS 테스트 중입니다.");
		//boardDAO.insertBoard(vo);
		
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

}
