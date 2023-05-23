package repository;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.LikesList;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	
	//NameSpace = SQL이 mapper를 구분하기 위한 이름
	//NameSpace.SQL구문이름
	private String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder(); //class객체 생성
		sql = DatabaseBuilder.getFactory().openSession(); //객체에서 펙토리를 가져와 sql연결
	}
	
	@Override
	public int insert(BoardVO bvo) {
		log.info(">>> insertDAO 진입");
		int isOk = sql.insert(NS+"reg", bvo); 
		
		if(isOk > 0 ) {
			sql.commit();
		}
		
		return isOk;
	}

	@Override
	public List<BoardVO> list() {
		log.info(">>> listDAO 진입");
		List<BoardVO> board_list = sql.selectList(NS+"list");
		return board_list;
	}

	@Override
	public BoardVO post(int bnum) {
		log.info(">>> postDAO 진입");
		int isOk = sql.update(NS+"countUP", bnum);
		if(isOk > 0 ) {
			sql.commit();
		}
		BoardVO bvo = sql.selectOne(NS+"select", bnum);
		return bvo;
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info(">>> postDAO 진입");
		int isOk = sql.update(NS+"edit", bvo); 
		
		if(isOk > 0 ) {
			sql.commit();
		}
		
		return isOk;
	}

	@Override
	public int delete(int bnum) {
		log.info(">>> removeDAO 진입");
		int isOk = sql.delete(NS+"remove", bnum); 
		
		if(isOk > 0 ) {
			sql.commit();
		}
		
		return isOk;
	}

	@Override
	public int like(LikesList ll) {
		log.info(">>> likeDAO 진입");
		int isOk1, isOk2;

		if(ll.isValue()) {			
			isOk1 = sql.insert("LikeListMapper.like", ll);
			isOk2 = sql.update(NS+"like");
			
		}else {
			isOk1 = sql.delete("LikeListMapper.unlike", ll);
			isOk2 = sql.update(NS+"unlike");
		}
		
		if(isOk1==1 && isOk2==1) {
			sql.commit();
		}
		return (isOk1==1 && isOk2==1)? 1 : 0;
	}

	@Override
	public int getTotal() {
		log.info(">>> getTotalDAO 진입");
		int totalCount = sql.selectOne(NS+"getTotal");
		return totalCount;
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		log.info(">>> getTotalDAO 진입");
		return sql.selectList(NS+"getPageList",pgvo);
	}

}
