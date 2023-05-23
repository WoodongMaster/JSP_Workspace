package repository;

import java.util.List;

import domain.BoardVO;
import domain.LikesList;
import domain.PagingVO;

public interface BoardDAO {
	
	int insert(BoardVO bvo);

	List<BoardVO> list();

	BoardVO post(int bnum);

	int edit(BoardVO bvo);

	int delete(int bnum);

	int like(LikesList ll);

	int getTotal();

	List<BoardVO> getPageList(PagingVO pgvo);

}
