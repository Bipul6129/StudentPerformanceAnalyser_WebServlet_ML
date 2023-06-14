package sps_website.db.conection.logic;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
public class SendJsonMessage {
	public static void sendJson(HttpServletResponse res,String message) throws IOException {
		res.setContentType("application/json");
		String resData = "{\"message\":\""+message+"\"}";
		PrintWriter out = res.getWriter();
		out.print(resData);
		out.flush();
	}
}
