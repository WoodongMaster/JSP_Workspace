package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	
	private String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int dupcheck(String id) {
		log.info(">>> 중복체크 DAO 진입");
		int isOk = sql.selectOne(NS+"dupcheck",id); 
		return isOk;
	}
	
	@Override
	public int insert(MemberVO mvo) {
		log.info(">>> register DAO 진입");
		int isOk = sql.insert(NS+"reg", mvo); 
		log.info(">>> isOk : "+isOk);
		if(isOk>0) { 
			sql.commit();
		}
		return isOk;
	}

	@Override
	public MemberVO selectOne(MemberVO mvo) {
		log.info(">>> login DAO 진입");
		return sql.selectOne(NS+"login", mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info(">>> logout DAO 진입");
		int isOk = sql.update(NS+"logout", id); 
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int edit(MemberVO mvo) {
		log.info(">>> edit DAO 진입");
		int isOk = sql.update(NS+"edit", mvo); 
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(String id) {
		log.info(">>> delete DAO 진입");
		int isOk = sql.delete(NS+"delete", id); 
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
