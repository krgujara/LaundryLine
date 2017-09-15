package dto;

public class FeedObjectsUserInfo {
	public static int idNumber=1;
	public int id = idNumber;
	public String name, email, pasword, mobile, referal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id + 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getReferal() {
		return referal;
	}

	public void setReferal(String referal) {
		this.referal = referal;
	}

	@Override
	public String toString() {
		return "FeedObjectsUserInfo [id="+id + ", name=" + name + ", email=" + email
				+ ", pasword=" + pasword + ", mobile=" + mobile + ", referal="
				+ referal + "]";
	}

}
