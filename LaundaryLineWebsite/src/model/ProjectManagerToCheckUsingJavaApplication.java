package model;

import java.sql.Connection;
import java.util.Scanner;
import java.util.ArrayList;

import dto.FeedObjectsRateCard;
import dto.FeedObjectsUserInfo;
import dao.Database;
import dao.Project;

public class ProjectManagerToCheckUsingJavaApplication {

	public static void main(String[] args) {

		//FOR RETRIVING DATA FROM DATABASE
	/*	
		
		  ArrayList<FeedObjectsUserInfo> feeds = null; try { Database database = new
		  Database();
		  Connection connection = database.get_connection();
		  Project project = new Project(); 
		  feeds =project.getFeeds(connection); 
		  System.out.println("printing feeds" +feeds); 
		  } catch (Exception e) 
		  { System.out.println(e); } 
		  // return feeds;
		 */
		
		//FOR PUTTING DATA IN DATABASE
/*		
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			Scanner sc = new Scanner(System.in);

			String name = sc.next();
			String email = sc.next();
			String pasword = sc.next();
			String mobile = sc.next();
			String referal = sc.next();

			FeedObjectsUserInfo object = new FeedObjectsUserInfo();
			object.name = name;
			object.email = email;
			object.pasword = pasword;
			object.mobile = mobile;
			object.referal = referal;
			
			project.putFeeds(connection, object);

		} catch (Exception e) {
			System.out.println(e);
		}
*/

		//Retriving Rate Card
/*		
		ArrayList<FeedObjectsRateCard>feeds = null;
		try
		{
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			feeds = project.getRateCard(connection);
			System.out.println("Printing Rate Card"+feeds);
		}catch(Exception e)
		{
			System.out.println(e);
		}

	}
	*/
		try {
			Database database = new Database();
			Connection connection = database.get_connection();
			Project project = new Project();
			Scanner sc = new Scanner(System.in);

			String mobile = sc.next();
			String pasword = sc.next();

			FeedObjectsUserInfo object = new FeedObjectsUserInfo();
			object.pasword = pasword;
			object.mobile = mobile;
	
			FeedObjectsUserInfo userinfo;
			userinfo  = project.validateuser(connection, object);
			/*if(result==true){
				System.out.println("match");
				
			}else{
				System.out.println("not match");
			}
*/
		System.out.println("userinfo that will be returned"+userinfo);	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
