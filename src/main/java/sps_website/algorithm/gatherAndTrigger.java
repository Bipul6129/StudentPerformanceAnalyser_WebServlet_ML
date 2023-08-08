package sps_website.algorithm;
import sps_website.db.conection.*;
import sps_website.model.PredictModel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;
public class gatherAndTrigger {
	public static String trigger(HttpServletRequest req,PredictModel model) throws Exception {

		String outcome="";
		HttpSession session = req.getSession();
		
		if(session.getAttribute("rootNode")!=null) {
			DecisionNode rootNode = (DecisionNode) session.getAttribute("rootNode");
			outcome=predictResult(rootNode,session,model);
		}else {
			final String[][] dbData =  getTrainingData();
			if(dbData.length<5) {
				outcome="notEnough Data";
			}else {
				session.setAttribute("rootNode", App.Analyze(dbData));
				DecisionNode rootNode = (DecisionNode) session.getAttribute("rootNode");
				outcome=predictResult(rootNode,session,model);
			}
			
		}
		 
		return outcome;
		
	}
	
	
	public static String predictResult(DecisionNode rootNode,HttpSession session,PredictModel model) {
		rootNode = (DecisionNode) session.getAttribute("rootNode");
		Map<String,String> input = new HashMap<>();
		if(input.size()>0) {
			input.clear();
		}
        input.put("status",model.getStatus());
        input.put("ethnicity",model.getEthnicity());
        input.put("gender",model.getGender());
        input.put("age",model.getAge());
        PredictValue predict = new PredictValue();
        String performance=predict.checkclass(rootNode, input);
        System.out.println("This is from predict Function "+performance);
        input.clear();
        return performance;
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
