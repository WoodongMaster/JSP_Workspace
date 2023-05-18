package service;

import java.util.List;

import domain.BoardVO;


public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> list();

	BoardVO post(int bnum);

	int edit(BoardVO bvo);

	int remove(int bnum);

}
