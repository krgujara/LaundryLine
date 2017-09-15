package model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.FeedObjectsMyOrders;
import dto.FeedObjectsRateCard;
import dto.FeedObjectsUserInfo;
import dao.Database;
import dao.Project;

public class ProjectManagerForServlet {

	public ArrayList<FeedObjectsUserInfo> getFeeds() throws Exception {

		ArrayList<FeedObjectsUserInfo> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			feeds = project.getFeeds(connection);
			System.out.println("printing feeds" + feeds);
		} catch (Exception e) {
			System.out.println(e);
		}
		return feeds;
	}

	public ArrayList<FeedObjectsRateCard> getRateCard() throws Exception {

		ArrayList<FeedObjectsRateCard> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			feeds = project.getRateCard(connection);
			System.out.println("printing RateCard" + feeds);
		} catch (Exception e) {
			System.out.println(e);
		}
		return feeds;
	}

	public String getAboutUs() {
		// TODO Auto-generated method stub

		String s = "laundry";
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			s = project.getAboutUs(connection);
			System.out.println("printing about us" + s);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return s;
	}

	public String getContactUs() {
		// TODO Auto-generated method stub

		String s = "laundry";
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			s = project.getContactUs(connection);
			System.out.println("printing about us" + s);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return s;
	}

	public ArrayList<FeedObjectsMyOrders> getMyOrders(String mobile) {
		// TODO Auto-generated method stub
		ArrayList<FeedObjectsMyOrders> feeds = null;
		try{
		
			Database database =new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			feeds = project.getMyOrders(connection,mobile);
		}catch(Exception e){
			System.out.println(e);
		}
		return feeds;
		
	}

	
	public int putFeeds(FeedObjectsUserInfo object) throws Exception {
		int status = 0;
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();

			System.out.println(object);
			status = project.putFeeds(connection, object);
			return status;

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;

	}
	
	public int updateFeeds(FeedObjectsUserInfo object) {
		int status = 0;
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			System.out.println("Connection : "+connection);
			Project project = new Project();

			System.out.println(object);
			status = project.updateFeeds(connection, object);
			return status;

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	
	
	public int putOrder(String email,
			List<FeedObjectsRateCard> confirmlist, String address11, String address22,String pin) throws Exception {
		// TODO Auto-generated method stub
		int status=0;
		try{
			Database database = new Database();
			Connection connection= database.get_connection();
			Project project = new Project();
			status = project.putOrder(connection, email,confirmlist,address11,address22,pin);
			
		}catch(Exception e){
			System.out.print(e);
		}
		
		return status;
	}

	
	
	public FeedObjectsUserInfo validateUser(FeedObjectsUserInfo object) throws Exception {
		//boolean result=false;
		FeedObjectsUserInfo userinfo;
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();

			userinfo = project.validateuser(connection, object);
			System.out.println(userinfo);
			return userinfo;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}




	

}
