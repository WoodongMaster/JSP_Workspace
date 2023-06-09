package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	
	//NameSpace = SQL이 mapper를 구분하기 위한 이름
	//NameSpace.SQL구문이름
	private String NS = "CommentMapper.";
	
	public CommentDAOImpl() {
		new DatabaseBuilder(); //class객체 생성
		sql = DatabaseBuilder.getFactory().openSession(); //객체에서 펙토리를 가져와 sql연결
	}
	@Override
	public int post(CommentVO cvo) {
		log.info(">>> Comment postDAO 진입");
		int isOk = sql.insert(NS+"reg", cvo);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}
	@Override
	public List<CommentVO> list(int bnum) {
		log.info(">>> Comment listDAO 진입");
		return sql.selectList(NS+"list",bnum);
	}
	@Override
	public int remove(int cnum) {
		log.info(">>> Comment removeDAO 진입");
		int isOk = sql.delete(NS+"del", cnum);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}
	@Override
	public int modify(CommentVO cvo) {
		log.info(">>> Comment modifyDAO 진입");
		int isOk = sql.update(NS+"edit", cvo);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

}
