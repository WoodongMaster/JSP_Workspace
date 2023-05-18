package service;

import java.util.List;

import domain.ProductVO;

public interface Service {
	public int register(ProductVO pvo);

	public List<ProductVO> list();

	public ProductVO detail(int pno);

	public int remove(int remove_pno);

	public int modify(ProductVO modify_pvo);
}
