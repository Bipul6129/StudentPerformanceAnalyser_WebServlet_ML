package sps_website.db.conection.logic;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.*;
public class ReturnJsonObject {
	public static JsonObject returnJObject(HttpServletRequest req) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder requestBody = new StringBuilder();
		String line;
		
		while((line=reader.readLine())!=null) {
			requestBody.append(line);
		}
		reader.close();
		
		Gson gson = new Gson();
		JsonObject jsonData  = gson.fromJson(requestBody.toString(),JsonObject.class);
		return jsonData;
	}
}
