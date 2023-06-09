package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.CommentController;
import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {
	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}
	public int post(CommentVO cvo) {
		log.info(">>> post Service 진입");
		
		return cdao.post(cvo);
	}
	
	public List<CommentVO> getList(int bnum) {
		log.info(">>> getlist Service 진입"); 
		return cdao.list(bnum);
	}
	@Override
	public int remove(int cnum) {
		log.info(">>> cmt remove Service 진입");
		return cdao.remove(cnum);
	}
	@Override
	public int modify(CommentVO cvo) {
		log.info(">>> cmt modify Service 진입");
		return cdao.modify(cvo);
	}

}
