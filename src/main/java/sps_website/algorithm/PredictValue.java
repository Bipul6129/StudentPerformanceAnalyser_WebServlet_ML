package sps_website.algorithm;
import java.util.Map;


public class PredictValue {
	int gotResult=0;
	String performance="";
    public String checkclass(DecisionNode root,Map<String,String> checkvalue){
        String attribute;
        String checkattribute;
        DecisionNode nextnode;
        
        
        if(root.isclassification())
        {
        	gotResult=1;
        	performance=root.getClassification();
            System.out.println("The given student will likely perform "+root.getClassification());
            checkvalue.clear();
            return performance;
        }
        if(gotResult==0){
        	if(!root.isclassification()){
                attribute = root.getAttribute();
                checkattribute=checkvalue.get(attribute);
                nextnode = root.getChild(checkattribute);
                checkclass(nextnode, checkvalue);
            }
            
            checkvalue.clear();
            System.out.println("This is from predictValue "+performance);
        	
        }
        return performance;
        
        
        
    }
}
