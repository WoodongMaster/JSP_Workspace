package service;

import java.util.List;
import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> list();

	BoardVO post(int bnum);

	int edit(BoardVO bvo);

	int remove(int bnum);

	int getTotal(PagingVO pgvo);

	List<BoardVO> getPageList(PagingVO pgvo);

}
