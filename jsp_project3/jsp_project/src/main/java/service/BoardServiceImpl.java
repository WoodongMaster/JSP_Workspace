package service;

import domain.BoardVO;
import domain.LikesList;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceImpl implements BoardService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}
	@Override
	public int insert(BoardVO bvo) {
		log.info(">>> insert Service 진입");
		return bdao.insert(bvo);
	}
	@Override
	public List<BoardVO> list() {
		log.info(">>> list Service 진입");
		return bdao.list();
	}
	@Override
	public BoardVO post(int bnum) {
		log.info(">>> post Service 진입");
		return bdao.post(bnum);
	}
	@Override
	public int edit(BoardVO bvo) {
		log.info(">>> post Service 진입");
		return bdao.edit(bvo);
	}
	@Override
	public int remove(int bnum) {
		log.info(">>> remove Service 진입");
		return bdao.delete(bnum);
	}
	@Override
	public int like(LikesList ll) {
		log.info(">>> like Service 진입");
		return bdao.like(ll);
	}
	@Override
	public int getTotal(PagingVO pgvo) {
		log.info(">>> getTotal Service 진입");
		return bdao.getTotal(pgvo);
	}
	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		log.info(">>> getPageList Service 진입");
		return bdao.getPageList(pgvo);
	}
}
