package webservice;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import model.ProjectManagerForServlet;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.Project;
import dto.FeedObjectsMyOrders;
import dto.FeedObjectsRateCard;
import dto.FeedObjectsUserInfo;

//Url - http://localhost:8081/Rest1182016/backend/webService/getFeeds
@Path("/webService")
public class FeedService {

/*	@POST
	@Path("/putOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerOrder(@FormParam("email")String email,@FormParam("confirmlist")List<FeedObjectsRateCard> confirmlist)throws IOException{
		int status = 0;
		System.out.println(email);
		System.out.println("Confirm List : "+confirmlist);
		ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
		try{
		    status = projectManagerForServlet.putOrder(email,confirmlist);
		}catch(Exception e){
			e.printStackTrace();
		}
		if (status == 0)
			return "{result:'fail'}";
		else
			return "{result:'success'}";
	}
*/	
	@POST
	@Path("/putOrder")
/*	@Consumes(MediaType.APPLICATION_JSON)
*/
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String registerOrder(@FormParam("object") String object, @FormParam("object2") String address)throws IOException{
		int status=0;
		String email = "none@gmail.com",address11 = "sarjepura",address22="ahmednagar",pin = "414001";
		System.out.println("In putOrder");
		
		List<FeedObjectsRateCard> confirmList = new ArrayList<FeedObjectsRateCard>();
		try {
			JSONObject jsonObject = new JSONObject(object);
			email = jsonObject.optString("email");
			System.out.println("Email:"+email);
			JSONArray confirmJsonArry =jsonObject.optJSONArray("confirmlist");
			for(int i=0;i<confirmJsonArry.length();i++){
				JSONObject feedObjectsRateCardJSONObject = confirmJsonArry.getJSONObject(i);
				FeedObjectsRateCard feedObjectsRateCard = new FeedObjectsRateCard();
				feedObjectsRateCard.setId(feedObjectsRateCardJSONObject.optInt("id"));
				feedObjectsRateCard.setName(feedObjectsRateCardJSONObject.optString("name"));
				feedObjectsRateCard.setPrice(feedObjectsRateCardJSONObject.optInt("price"));
				feedObjectsRateCard.setQuantity(feedObjectsRateCardJSONObject.optInt("quantity"));
				confirmList.add(feedObjectsRateCard);
			}
			JSONObject jsonObject1 = new JSONObject(address);

			address11 = jsonObject1.optString("address1");
			System.out.println("Address1:"+address11);

			address22 = jsonObject1.optString("address2");
			System.out.println("Address2:"+address22);

			pin = jsonObject1.optString("pin");
			System.out.println("Pin: "+pin);
			
			
			System.out.println("From pash:"+confirmList.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
		try{
		    status = projectManagerForServlet.putOrder(email,confirmList,address11,address22,pin);
		}catch(Exception e){
			e.printStackTrace();
		}
		if (status == 0)
			return "{result:'fail'}";
		else
			return "{result:'success'}";
	}
	

	@POST
	@Path("/editUser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String altertUser(@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("mobile") String mobile,
			@Context HttpServletResponse servletResponse) throws IOException {
				
		int status = 0;
		ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
		FeedObjectsUserInfo object = new FeedObjectsUserInfo();
		object.name = name;
		object.email = email;
		object.mobile = mobile;
		System.out.println(name+email+mobile);
		try {
			status = projectManagerForServlet.updateFeeds(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status == 0)
			return "{result:'fail'}";
		else
			return "{result:'success'}";
		

	}
	
	
	@POST
	@Path("/putFeeds")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String registertUser(@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("pasword") String pasword,
			@FormParam("mobile") String mobile,
			@FormParam("referal") String referal,
			@Context HttpServletResponse servletResponse) throws IOException {
		int status = 0;
		ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
		FeedObjectsUserInfo object = new FeedObjectsUserInfo();
		object.name = name;
		object.email = email;
		object.pasword = pasword;
		object.mobile = mobile;
		object.referal = referal;
		System.out.println(object);
		try {
			status = projectManagerForServlet.putFeeds(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status == 0)
			return "{result:'fail'}";
		else
			return "{result:'success'}";
	}


	

	@GET
	@Path("/getFeeds")
	public String feed() {
		String feeds = null;
		try {
			ArrayList<FeedObjectsUserInfo> feedData = null;
			ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
			feedData = projectManagerForServlet.getFeeds();
			Gson gson = new Gson();
			feeds = gson.toJson(feedData);

			// System.out.println(dataposted);
		} catch (Exception e) {
			System.out.println("Excepiton " + e);
		}
		return feeds;
	}

	// Url - http://192.168.1.6:8080/Rest1182016/backend/webService/getRateCard

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/getRateCard")
	public String GetRateCard() {
		String feeds = null;
		try {
			ArrayList<FeedObjectsRateCard> feedData = null;
			ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
			feedData = projectManagerForServlet.getRateCard();
			Gson gson = new Gson();
			feeds = gson.toJson(feedData);

			// System.out.println(dataposted);
		} catch (Exception e) {
			System.out.println("Excepiton " + e);
		}
		System.out.println(feeds);
		return feeds;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/getAboutUs")
	public String GetAboutUs() {
		String feeds = null;

		String s = "null";
		try {
			
			ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
			s = projectManagerForServlet.getAboutUs();
			Gson gson = new Gson();
			feeds = gson.toJson(s);

			
			// System.out.println(dataposted);
		} catch (Exception e) {
			System.out.println("Excepiton " + e);
		}
		System.out.println(feeds);
		return feeds;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/getContactUs")
	public String GetContactUs() {
		String feeds = null;

		String s = "null";
		try {
			
			ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
			s = projectManagerForServlet.getContactUs();
			Gson gson = new Gson();
			feeds = gson.toJson(s);

			
			// System.out.println(dataposted);
		} catch (Exception e) {
			System.out.println("Excepiton " + e);
		}
		System.out.println(feeds);
		return feeds;
	}

	@POST
	@Path("/getMyOrders")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getMyOrders(@FormParam("mobile") String mobile){
		String feeds = null;
		
		System.out.println("Order generated from mobile: "+mobile);
		try{
		
			ArrayList<FeedObjectsMyOrders> list =null;
			ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();
			ArrayList<FeedObjectsMyOrders> data = projectManagerForServlet.getMyOrders(mobile);
			Gson gson = new Gson();
			feeds = gson.toJson(data);

			System.out.println(feeds);
			
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
		
		
		return feeds;
	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String loginUser(@FormParam("mobile") String mobile,
			@FormParam("pasword") String pasword,
			@Context HttpServletResponse servletResponse) throws IOException {

		FeedObjectsUserInfo userinfo = null;
		String feeds = null;
		final String SUCCESS_RESULT = "{result:'success'}";
		final String FAILURE_RESULT = "{result:'failure'}";
		boolean result = false;
		

		ProjectManagerForServlet projectManagerForServlet = new ProjectManagerForServlet();

		FeedObjectsUserInfo object = new FeedObjectsUserInfo();

		object.pasword = pasword;
		object.mobile = mobile;

		try {
			userinfo = projectManagerForServlet.validateUser(object);
			System.out.println("Feed Service"+userinfo);

			Gson gson = new Gson();
			feeds = gson.toJson(userinfo);

			
			return feeds;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * if (result == true) return userinfo; else return FAILURE_RESULT;
		 */
		return feeds;
	}

}
