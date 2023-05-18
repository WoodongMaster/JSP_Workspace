package service;

import java.util.List;

import domain.ProductVO;
import repository.DAO;
import repository.ProductDAO;

public class ProductService implements Service {

	private DAO dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public int register(ProductVO pvo) {
		// JSP에서 받은 객체 pvo를 가지고 db에 넣어달라고 dao에게 요청
		// dao에게 연결할 메서드는 db 구문으로 하는것이 일반적
		System.out.println(">>> register service 진입");
		return dao.insert(pvo);
	}

	@Override
	public List<ProductVO> list() {
		System.out.println(">>> list service 진입");
		return dao.selectList();
	}

	@Override
	public ProductVO detail(int pno) {
		System.out.println(">>> detail service 진입");
		return dao.detail(pno);
	}

	@Override
	public int remove(int remove_pno) {
		// TODO Auto-generated method stub
		return dao.remove(remove_pno);
	}

	@Override
	public int modify(ProductVO modify_pvo) {
		
		return dao.modify(modify_pvo);
	}



}
