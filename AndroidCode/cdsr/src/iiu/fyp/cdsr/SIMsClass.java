package iiu.fyp.cdsr;

public class SIMsClass {

	private int Simid;
	private String Simnumber;

	public long getSimId() {
		return Simid;
	}

	public void setSimId(int Simid) {
		this.Simid = Simid;
	}

	public String getSimNumber() {
		return this.Simnumber;
	}

	public void setSimNumber(String Simnumber) {
		this.Simnumber = Simnumber;
	}
	@Override
	public String toString() {
	    return Simnumber;
	}
	
}