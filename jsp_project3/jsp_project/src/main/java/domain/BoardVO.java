package domain;

public class BoardVO {

	private int bnum;
	private String title;
	private String writer;
	private String post;
	private String write_date;
	private int count;
	private int likes;
	private String imgfile;
	
	public BoardVO(){}
	
	// 게시글 등록
	public BoardVO(String title, String writer, String post)
	{
		this.title=title;
		this.writer=writer;
		this.post=post;
	}
	
	// 게시글 리스트
	public BoardVO(int bnum, String title, String writer, String write_date, int count, int likes)
	{	
		this.bnum=bnum;
		this.title=title;
		this.writer=writer;
		this.write_date=write_date;
		this.count=count;
		this.likes=likes;
	}

	// 게시글 읽기
	public BoardVO(int bnum, String title, String writer, String write_date,int count, int like,String post, String imgfile)
	{
		this(bnum,title,writer,write_date,count,like);
		this.post = post;
		this.imgfile=imgfile;
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int like) {
		this.likes = like;
	}

	public String getImgfile() {
		return imgfile;
	}

	public void setImgfile(String imgfile) {
		this.imgfile = imgfile;
	}

	@Override
	public String toString() {
		return "BoardVO [bnum=" + bnum + ", title=" + title + ", writer=" + writer + ", post=" + post + ", write_date="
				+ write_date + ", count=" + count + ", likes=" + likes + ", imgfile=" + imgfile + "]";
	}


	



	
	
}
