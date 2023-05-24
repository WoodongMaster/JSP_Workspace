package domain;

public class PagingVO {
	
	private int pageNum; // 현재 페이지가 아니고 12의 경우 11~20 사이 limit 11,10 이니까 
	private int qty;	// 한 페이지에 보여줄 게시글 수(10개)
	
	private String type;	// 검색 종류 (제목(T), 작성자(W), 내용(C) 둥)
	private String keyword;	// 검색 내용
	
	private String id;
	
	public PagingVO() { // 페이지네이션을 클릭하기전 기본 값으로 지정
		this(1, 10);
	}
	
	public PagingVO(int pageNum, int qty) {
		this.pageNum=pageNum;
		this.qty=qty;
	}

	public PagingVO(int pageNum, int qty, String id) {
		this.pageNum=pageNum;
		this.qty=qty;
		this.id=id;
	}
	
	public int getStartPage() {	// limit 번지 구하기  -> limit(번지,개수)
		return (this.pageNum-1)*10; 
		// DB는 0번지부터 시작
		// pageNum-1해줘서 0번지부터 시작하기
		// ex) 1페이지는 그냥 찍으니까 pass 2페이지부터 pageNum=2 qty=10 startPage = 10번지부터 10개 10~19번지
		// ex) 6페이지 pageNum=6 qty=10 startPage = 50~59번지
	}
	
	//여러 조건이 있는 타입이 있으니 타입종류를 배열로 받아서 분리
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
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

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PagingVO [pageNum=" + pageNum + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + ", id="
				+ id + "]";
	}

	
}
