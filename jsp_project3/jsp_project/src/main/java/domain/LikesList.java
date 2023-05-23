package domain;

public class LikesList {
	private String id;
	private int bnum;
	private boolean value;
	
	public LikesList(){}
	
	public LikesList(String id, int bnum, boolean value) {
		this.id=id;
		this.bnum=bnum;
		this.value=value;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	

}
