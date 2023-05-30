package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.MemberController;
import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {

	//log 설정
	//private static final Logger log = LoggerFactory.getLogger(클래스명.class);
	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}
		
	@Override
	public int register(MemberVO mvo) {
		log.info(">>> register service 진입");
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>> login service 진입");
		return mdao.selectOne(mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info(">>> logout service 진입");		
		return mdao.lastLogin(id);
	}

	@Override
	public int edit(MemberVO mvo) {
		log.info(">>> logout service 진입");
		return mdao.edit(mvo);
	}

	@Override
	public int delete(String id) {
		log.info(">>> delete service 진입");
		return mdao.delete(id);
	}

	@Override
	public List<MemberVO> showlist() {
		log.info(">>> showlist service 진입");
		return mdao.showlist();
	}

	@Override
	public int dupcheck(String id) {
		log.info(">>> 중복체크 service 진입");
		return mdao.dupcheck(id);
	}

}
