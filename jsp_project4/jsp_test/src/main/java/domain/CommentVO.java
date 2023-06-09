package domain;

public class CommentVO {
	private int cnum;
	private int bnum;
	private String writer;
	private String comment;
	private String reg_date;
	
	public CommentVO(){}
	
	//list
	public CommentVO(int bnum, String writer, String comment, String reg_date){
		this.bnum=bnum;
		this.writer=writer;
		this.comment=comment;
		this.reg_date=reg_date;
	}
	
	//insert
	public CommentVO(int bnum, String writer, String comment){
		this.bnum=bnum;
		this.writer=writer;
		this.comment=comment;
	}
	
	//modify
	public CommentVO(String writer, String comment, int cnum){
		this.cnum=cnum;
		this.comment=comment;
		this.writer=writer;
	}
	
	//전체
	public CommentVO(int cnum, int bnum, String writer, String comment, String reg_date){
		this.cnum=cnum;
		this.bnum=bnum;
		this.writer=writer;
		this.comment=comment;
		this.reg_date=reg_date;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "CommentVO [cnum=" + cnum + ", bnum=" + bnum + ", writer=" + writer + ", comment=" + comment
				+ ", reg_date=" + reg_date + "]";
	}
	
	
}
