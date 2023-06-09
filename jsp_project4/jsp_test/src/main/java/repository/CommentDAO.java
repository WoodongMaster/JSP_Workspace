package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int post(CommentVO cvo);

	List<CommentVO> list(int bnum);

	int remove(int cnum);

	int modify(CommentVO cvo);

}
