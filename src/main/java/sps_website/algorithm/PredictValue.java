package sps_website.algorithm;
import java.util.Map;


public class PredictValue {

    public String checkclass(DecisionNode root,Map<String,String> checkvalue){
        String attribute;
        String checkattribute;
        DecisionNode nextnode;
        String performance="";
        if(!root.isclassification()){
            attribute = root.getAttribute();
            checkattribute=checkvalue.get(attribute);
            nextnode = root.getChild(checkattribute);
            checkclass(nextnode, checkvalue);
        }
        if(root.isclassification())
        {
        	performance=root.getClassification();
            System.out.println("The given student will likely perform "+root.getClassification());
        }
        checkvalue.clear();
        return performance;
        
    }
}
