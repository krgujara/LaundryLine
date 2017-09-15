package dto;

public class FeedObjectsMyOrders {
int userid;
String email;
int orderid;
int estimated;
String orderdate;
String status;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public int getEstimated() {
	return estimated;
}
public void setEstimated(int estimated) {
	this.estimated = estimated;
}
public String getOrderdate() {
	return orderdate;
}
public void setOrderdate(String orderdate) {
	this.orderdate = orderdate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "FeedObjectsMyOrders [userid=" + userid + ", email=" + email + ", orderid=" + orderid + ", estimated="
			+ estimated + ", orderdate=" + orderdate + ", status=" + status + "]";
}



}
