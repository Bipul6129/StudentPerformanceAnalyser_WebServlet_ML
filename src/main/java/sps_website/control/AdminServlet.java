package sps_website.control;
import sps_website.db.conection.logic.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import com.google.gson.*;
import javax.servlet.http.*;
import sps_website.model.*;
import javax.servlet.annotation.*;


@WebServlet("/webpages/adminData")
public class AdminServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		List<UserSpecModel> userModel = AdminLogics.getUsers();
		System.out.println(userModel.size());
		Gson gson = new Gson();
		String modelLists = gson.toJson(userModel);
		res.setContentType("application/json");
		res.getWriter().write(modelLists);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		JsonObject jsonData = ReturnJsonObject.returnJObject(req);
		
		int userId = jsonData.get("userId").getAsInt();
		int blockStatus = jsonData.get("blockStatus").getAsInt();
		boolean status=AdminLogics.setBlockStatus(userId, blockStatus);
		if(status) {
			SendJsonMessage.sendJson(res,"success");
		}else {
			SendJsonMessage.sendJson(res,"failed");
		}
		System.out.println(userId+","+blockStatus);
	}
}
