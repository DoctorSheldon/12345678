
public enum cell {
    live("1"),death("0");
	private String string;
	private cell(String string){
		this.string=string;
	}
	public String getString() {
		return this.string;
	}
	public void setString(String string) {
		this.string=string;
	}
}
