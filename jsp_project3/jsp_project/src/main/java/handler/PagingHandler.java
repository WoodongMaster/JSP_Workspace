package handler;

import domain.PagingVO;

public class PagingHandler {
	
	private int startPage;	 // 시작 페이지 (현재 화면에서 보여줄 시작 페이지네이션 번호)
	private int endPage;	// 끝 페이지 (현재 화면에서 보여줄 끝 페이지네이션 번호)
	private boolean prev, next; // 이전, 다음버튼 존재 유무
	private int totalCount; // 전체 게시물 수
	private PagingVO pgvo;
	
	public PagingHandler() {}
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount=totalCount;
		//나머지 값 계산
		//127개의 게시글일 경우 127.0/10 = 12.7
		//1~13까지 13페이지 필요
		//1페이지 기준 startpage=1, endpage=10 다음>
		//11페이지 기준 <이전 startpage=11, endpage=13
		
		// * Math.ceil = 반올림이 아니고 그냥 올림 1.1 => 2 (= x와 같거나 큰 수 중에서 가장 작은 정수를 반환함.)
		// (페이지 번호 / 한 화면의 게시글 수) * 한 화면의 게시글 수
		// ex) (1 / 10.0) * 10 => 0.1(1) * 10 => 10
		// ex) (2 / 10.0) * 10 => 0.2(1) * 10 => 10
		// ex) (12 / 10.0) * 10 => 1.2(2) * 10 => 20		
		// ex) (21 / 10.0) * 10 => 2.1(3) * 10 => 30
		this.endPage = (int)(Math.ceil(pgvo.getPageNum() / (pgvo.getQty()*1.0))) * pgvo.getQty();
		this.startPage = this.endPage-9;
		int realEndPage = (int)Math.ceil((totalCount*1.0) / pgvo.getQty()); // 마지막 페이지네이션 번호
		
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		//현재 화면에서 보여지는 startpage = 1, 11, 21 ...
		//현재 화면에서 보여지는 endpage = 10, 20, 30 ... realEndPage까지
		this.prev = this.startPage>1;
		this.next = this.endPage<realEndPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}

	
	
}
