package sps_website.algorithm;
import sps_website.db.conection.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;
public class gatherAndTrigger {
	public static void trigger(HttpServletRequest req) throws Exception {
		String[][] dataset ={
	            {">18<25","male","himalayan","poor","Well"},
	            {">25","female","terai","middle","Well"},
	            {">25","other","hilly","rich","NotWell"},
	            {">18<25","other","terai","middle","Well"},
	            {">18<25","male","himalayan","poor","NotWell"},
	            {">25","other","hilly","middle","Well"},
	            {">18<25","female","hilly","rich","NotWell"},
	            {"<18","male","terai","poor","Well"},
	            {"<18","male","hilly","middle","NotWell"},
	            {">25","female","himalayan","rich","NotWell"},
	            {"<18","male","hilly","middle","Well"},
	            {">18<25","female","terai","rich","NotWell"},
	            {"<18","female","himalayan","poor","Well"},
	            {">18<25", "other", "terai", "middle", "Well"},
	            {">25", "male", "himalayan", "middle", "Well"},
	            {">25", "male", "terai", "rich", "Well"},
	            {">18<25", "male", "hilly", "poor", "NotWell"},
	            {">18<25", "female", "terai", "middle", "Well"},
	            {"<18", "other", "hilly", "poor", "Well"},
	            {">25", "female", "himalayan", "middle", "NotWell"},
	            {">18<25", "male", "terai", "middle", "Well"},
	            {">18<25", "other", "terai", "rich", "Well"},
	            {">18<25", "male", "himalayan", "middle", "NotWell"},
	            {">25", "female", "hilly", "middle", "NotWell"},
	            {">25", "other", "himalayan", "middle", "Well"},
	            {">18<25", "male", "terai", "poor", "NotWell"},
	            {"<18", "male", "hilly", "poor", "NotWell"},
	            {">25", "male", "terai", "middle", "Well"},
	            {">18<25", "female", "hilly", "rich", "Well"},
	            {">18<25", "male", "hilly", "middle", "NotWell"},
	            {">25", "other", "hilly", "middle", "NotWell"},
	            {">25", "female", "terai", "poor", "NotWell"},
	            {"<18", "male", "terai", "middle", "Well"},
	            {">25", "female", "himalayan", "rich", "Well"},
	            {">18<25", "male", "hilly", "middle", "Well"},
	            {">18<25", "female", "terai", "poor", "NotWell"},
	            {">25", "male", "hilly", "middle", "Well"},
	            {"<18", "other", "himalayan", "middle", "Well"},
	            
	            
	        };
//		String Result = getTrainingData();
//		System.out.print(Result+" is what we got ");
		HttpSession session = req.getSession();
		
		if(session.getAttribute("rootNode")!=null) {
			DecisionNode rootNode = (DecisionNode) session.getAttribute("rootNode");
			Map<String,String> input = new HashMap<>();
			if(input.size()>0) {
				input.clear();
			}
	        input.put("status","poor");
	        input.put("ethnicity","himalayan");
	        input.put("gender","male");
	        input.put("age","<18");
	        PredictValue predict = new PredictValue();
	        String performance=predict.checkclass(rootNode, input);
	        input.clear();
		}else {
			final String[][] dbData =  getTrainingData();
			session.setAttribute("rootNode", App.Analyze(dbData));
		}

//		
		
	}
	
	public static String[][] getTrainingData() throws Exception {
		Connection con = EstablishConnection.getConnection();
		String query = "select *,(select count(*) from analyzedata) as total_count from analyzedata";
		PreparedStatement pst = con.prepareStatement(query);
		int firstLoop=0;
		int i=0;
		
		String countQuery = "select count(*) as total_count from analyzedata";
		PreparedStatement pst2 = con.prepareStatement(countQuery);
		ResultSet countSet = pst2.executeQuery();
		int arraySize=0;
		while(countSet.next()) {
			arraySize=countSet.getInt("total_count");
		}
		
		String[][] dataset = new String[arraySize][5];
		System.out.println("initialize phase");
		ResultSet set = pst.executeQuery();
		while(set.next()) {

			for(int j=0;j<5;j++) {
				if(j==0) {
					dataset[i][j]=set.getString("age");
				}
				if(j==1) {
					dataset[i][j]=set.getString("gender");
				}
				if(j==2) {
					dataset[i][j]=set.getString("ethnicity");
				}
				if(j==3) {
					dataset[i][j]=set.getString("familyStatus");
				}
				if(j==4) {
					dataset[i][j]=set.getString("performance");
				}
				
			}
			i++;
		}
		System.out.println("data supply phase");
		for(int a=0;a<dataset.length;a++) {
			System.out.print('{');
			for(int m=0;m<5;m++) {
				System.out.print("\""+dataset[a][m]+"\",");
			}
			System.out.println("},");
		}
		return dataset;
		
	}
	
}
