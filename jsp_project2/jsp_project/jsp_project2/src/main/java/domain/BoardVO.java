package domain;

public class BoardVO {
//	create table board(
//	bno int primary key auto_increment,
//	title varchar(100) not null,
//	writer varchar(100) not null,
//	write_date datetime default now(),
//	post text);
	private int bnum;
	private String title;
	private String writer;
	private String post;
	private String write_date;
	private int count;
	
	public BoardVO(){}
	
	// 게시글 등록
	public BoardVO(String title, String writer, String post)
	{
		this.title=title;
		this.writer=writer;
		this.post=post;
	}
	
	// 게시글 리스트
	public BoardVO(int bnum, String title, String writer, String write_date, int count)
	{	
		this.bnum=bnum;
		this.title=title;
		this.writer=writer;
		this.write_date=write_date;
		this.count=count;
	}

	// 게시글 읽기
	public BoardVO(int bnum, String title, String writer, String write_date,int count,String post)
	{
		this(bnum,title,writer,write_date,count);
		this.post = post;
	}

	// 게시글 수정
	public BoardVO(int bnum, String title, String post)
	{
		this.bnum=bnum;
		this.title=title;
		this.post=post;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BoardVO [bnum=" + bnum + ", title=" + title + ", writer=" + writer + ", post=" + post + ", write_date="
				+ write_date + ", count=" + count + "]";
	}

	
	
}
