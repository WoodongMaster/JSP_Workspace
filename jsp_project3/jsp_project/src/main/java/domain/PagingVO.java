package domain;

public class PagingVO {
	
	private int pageNum; // 현재 화면에 출력되는 페이지네이션 번호
	private int qty;	// 한 페이지에 보여줄 게시글 수(10개)
	
	public PagingVO() { // 페이지네이션을 클릭하기전 기본 값으로 지정
		this(1, 10);
	}
	
	public PagingVO(int pageNum, int qty) {
		this.pageNum=pageNum;
		this.qty=qty;
	}

	public int getStartPage() {	// limit 번지 구하기  -> limit(번지,개수)
		return (this.pageNum-1)*10;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	
}
