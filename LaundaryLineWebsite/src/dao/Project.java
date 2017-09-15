package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dto.FeedObjectsMyOrders;
import dto.FeedObjectsRateCard;
import dto.FeedObjectsUserInfo;

public class Project {
	ArrayList<FeedObjectsUserInfo> list = new ArrayList<FeedObjectsUserInfo>();

	public int putOrder(Connection connection, String email, List<FeedObjectsRateCard> confirmlist, String address11,
			String address22, String pin) {

		int flag = 0;
		try {
			PreparedStatement statement = connection
					.prepareStatement("select id from userinfo where email = '" + email + "'");
			ResultSet set = statement.executeQuery();

			set.next(); // exactly one result so allowed
			int id = set.getInt(1); // use indexed retrieval since the column
									// has no name

			System.out.println(id);
			System.out.println("ConfirmList :" + confirmlist);
			System.out.println("address1: " + address11);
			System.out.println("address2: " + address22);
			System.out.println("Pin: "+ pin);
			
			// calculating estimated -
			int estimated = 0;

			for (FeedObjectsRateCard feedObjectsRateCard : confirmlist) {
				estimated = estimated + (feedObjectsRateCard.getPrice() * feedObjectsRateCard.getQuantity());
			}

			System.out.println("Estimated: " + estimated);

			PreparedStatement psgetnextId = connection.prepareStatement("select seq_ordertable.nextval from dual");
			ResultSet rs = psgetnextId.executeQuery();

			rs.next(); // exactly one result so allowed
			int orderid = rs.getInt(1); // use indexed retrieval since the

			// column has no name

			String adpin = address22+", "+pin;
			System.out.println(adpin);
			System.out.println("orderid" + orderid);
			PreparedStatement statement1 = connection.prepareStatement("insert into ordertable values (" + orderid + ","
					+ id + "," + estimated + ",'" + address11 + "','" + adpin + "')");
			statement1.executeQuery();

			for (FeedObjectsRateCard feedObjectsRateCard : confirmlist) {

				System.out.println("Storing Data in Order Item");

				PreparedStatement statement12 = connection.prepareStatement("select seq_orderitem.nextval from dual");
				ResultSet rs12 = statement12.executeQuery();

				PreparedStatement statement2 = connection
						.prepareStatement("insert into orderitem values (seq_orderitem.nextval," + orderid + ","
								+ feedObjectsRateCard.getId() + "," + feedObjectsRateCard.getQuantity() + ")");
				statement2.executeQuery();

			}

			PreparedStatement statement1112 = connection.prepareStatement(
					"insert into orderdelivary (delid,customerid,orderid,status) values(seqdel.nextval,'" + id + "', '"
							+ orderid + "', 'pending')");
			statement1112.executeQuery();

			PreparedStatement statement113 = connection.prepareStatement("commit");
			statement113.executeQuery();

			connection.close();
			System.out.println("Successful Connection");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Catch Block");
		}
		System.out.println("Insert Success");
		// TODO Auto-generated method stub
		return 1;
	}

	public int putFeeds(Connection connection, FeedObjectsUserInfo object) throws Exception {

		int flag = 0;
		try {

			PreparedStatement ps = connection.prepareStatement("select mobile, email from userinfo");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println(rs.getString(1));
				if (rs.getString(1).equals(object.mobile)
				/* && rs.getString(2).equals(object.pasword) */) {
					System.out.println("User already exists");
					flag = 1;
					break;
				}

			}
			// connection.close();

			if (flag != 1) {

				/*
				 * PreparedStatement statement = connection .prepareStatement(
				 * "insert into userinfo values (" +object.id+",'" + object.name
				 * + "', '" + object.email + "', '" + object.pasword + "', '" +
				 * object.mobile + "', '" + object.referal + "')"); ResultSet
				 * set = statement.executeQuery();
				 */
				PreparedStatement statement = connection.prepareStatement("insert into userinfo values ("
						+ "seq_Category_id.nextval,'" + object.name + "', '" + object.email + "', '" + object.pasword
						+ "', '" + object.mobile + "', '" + object.referal + "')");
				ResultSet set = statement.executeQuery();

				PreparedStatement statement1 = connection.prepareStatement("commit");
				ResultSet set1 = statement1.executeQuery();

				connection.close();
				System.out.println("Successful Connection");
			}
		} catch (Exception e) {
			System.out.println(e);
			return 0;

		}
		System.out.println("Succcessful insert");
		return 1;
	}

	
	public int updateFeeds(Connection connection, FeedObjectsUserInfo object) {
		int flag = 0;
		
		System.out.println("In update feeds");

		try{
			
			System.out.println("trying");
			System.out.println("Connection : "+connection);

			
			PreparedStatement ps11 = connection.prepareStatement("update userinfo set name= 'komals' where mobile = '8007240712'");
			ResultSet rs11  = ps11.executeQuery();
			System.out.println(rs11);
			
			
			PreparedStatement ps1 = connection.prepareStatement("commit");
			ResultSet rs1  = ps1.executeQuery();
			System.out.println(rs1);
			
		
			System.out.println("Still trying");
			
		}catch(Exception e){
			System.out.println(e);
		}
				

		return 1;
	}

	
	
	public ArrayList<FeedObjectsUserInfo> getFeeds(Connection connection) throws Exception {

		try {

			PreparedStatement ps = connection.prepareStatement("select * from userinfo");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				FeedObjectsUserInfo feedObject = new FeedObjectsUserInfo();
				feedObject.setId(rs.getInt(1));
				feedObject.setName(rs.getString(2));
				feedObject.setEmail(rs.getString(3));
				feedObject.setPasword(rs.getString(4));
				feedObject.setMobile(rs.getString(5));
				feedObject.setReferal(rs.getString(6));
				list.add(feedObject);
			}
			connection.close();
			return list;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}

	}

	// Retrive Rate Card
	public ArrayList<FeedObjectsRateCard> getRateCard(Connection connection) throws Exception {
		ArrayList<FeedObjectsRateCard> list = new ArrayList<FeedObjectsRateCard>();

		try {
			PreparedStatement ps = connection.prepareStatement("select * from ratecard order by price");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				FeedObjectsRateCard feedObject = new FeedObjectsRateCard();
				feedObject.setId(rs.getInt(1));
				feedObject.setName(rs.getString(2));
				feedObject.setPrice(rs.getInt(3));

				list.add(feedObject);
			}
			connection.close();
			return list;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	
	public String getAboutUs(Connection connection) throws Exception  {

		String s = "about us";
		try {
			PreparedStatement ps = connection.prepareStatement("select aboutus from managerinfo");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				s = rs.getString(1);

			}
			connection.close();
			return s;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	public String getContactUs(Connection connection) throws Exception  {

		String s = "call";
		String s1 = "mail";
		try {
			PreparedStatement ps = connection.prepareStatement("select mail,call from managerinfo");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				s = rs.getString(1);
				s1 = rs.getString(2);

			}
			connection.close();
			
			
			return "Our email id is "+s+" Or you can contact us at"+s1;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}
	
	public ArrayList<FeedObjectsMyOrders> getMyOrders(Connection connection, String mobile) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<FeedObjectsMyOrders> list = new ArrayList<FeedObjectsMyOrders>();
		try {

			System.out.println("In Project.java getOrders");

			PreparedStatement ps = connection.prepareStatement(
					"select u.id,u.email,o.orderid,o1.estimated,o.orderdate,o.status from orderdelivary o, ordertable o1, userinfo u where u.id=o.customerid and o1.id = o.orderid and u.id=o1.customerid and u.mobile='"
							+ mobile + "'");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				FeedObjectsMyOrders object = new FeedObjectsMyOrders();
				object.setUserid(rs.getInt(1));
				object.setEmail(rs.getString(2));
				object.setOrderid(rs.getInt(3));
				object.setEstimated(rs.getInt(4));
				object.setOrderdate(rs.getString(5));
				object.setStatus(rs.getString(6));

				list.add(object);

			}

			System.out.println("My Orders: " + list);

			connection.close();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("catch block");
			e.printStackTrace();

			throw e;
		}

		return list;
	}

	public FeedObjectsUserInfo validateuser(Connection connection, FeedObjectsUserInfo object) throws Exception {
		ArrayList<FeedObjectsUserInfo> list = new ArrayList<FeedObjectsUserInfo>();

		try {

			PreparedStatement ps = connection
					.prepareStatement("select mobile, pasword,email,name,id,referal from userinfo");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				if (rs.getString(1).equals(object.mobile) && rs.getString(2).equals(object.pasword)) {

					object.setEmail(rs.getString(3));
					object.setName(rs.getString(4));
					object.setId(rs.getInt(5));
					object.setReferal(rs.getString(6));
					System.out.println(
							rs.getString(1) + "&&" + rs.getString(2) + "object" + object.mobile + object.pasword);
					return object;
				}

			}
			connection.close();
			return null;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}

	}



}
