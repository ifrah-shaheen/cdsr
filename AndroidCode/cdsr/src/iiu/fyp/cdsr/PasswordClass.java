package iiu.fyp.cdsr;

public class PasswordClass {


	private int UserPasswordid;
	private String UserPassword;
	
	public long getUserPasswordId() {
		return UserPasswordid;
	}

	public void setUserPasswordId(int UserPasswordid) {
		this.UserPasswordid = UserPasswordid;
	}

	public String getUserPassword() {
		return this.UserPassword;
	}

	public void setUserPassword(String UserPassword) {
		this.UserPassword = UserPassword;
	}
	
	@Override
	public String toString() {
	    return UserPassword;
	}

	
}