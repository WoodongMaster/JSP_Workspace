package repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;
import service.MemberService;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	
	private String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession(); //객체에서 펙토리를 가져와 sql연결
	}
	
	@Override
	public int insert(MemberVO mvo) {
		log.info(">>> register DAO 진입");
		String id = mvo.getId();
		MemberVO tmp = sql.selectOne(NS+"dupcheck", id);
		int isOk = sql.insert(NS+"reg", mvo); 
		log.info(">>> tmp : "+tmp);
		log.info(">>> isOk : "+isOk);
		if(isOk>0 && tmp==null) { 
			sql.commit();
			return isOk;
		}else {			
			return 0;
		}
	}

	@Override
	public MemberVO selectOne(MemberVO mvo2) {
		log.info(">>> login DAO 진입");
		return sql.selectOne(NS+"login", mvo2);
	}

	@Override
	public int lastLogin(String id2) {
		log.info(">>> logout DAO 진입");
		int isOk = sql.update(NS+"logout", id2); 
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int edit(MemberVO mvo2) {
		log.info(">>> edit DAO 진입");
		int isOk = sql.update(NS+"edit", mvo2); 
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(String id2) {
		log.info(">>> delete DAO 진입");
		int isOk = sql.delete(NS+"delete", id2); 
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}



	@Override
	public List<MemberVO> showlist() {
		log.info(">>> showlist DAO 진입");
		List<MemberVO> list = sql.selectList(NS+"list");
		return list;
	}

}
