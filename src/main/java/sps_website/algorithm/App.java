package sps_website.algorithm;

import java.util.*;

public class App {
    public static String dec1="1";
    public static String dec2="2";
    public static String dec3="3";
    public static Integer Treedepth=0;
    public static Integer firstTime=0;
    public static DecisionNode Analyze(String[][] dataset) throws Exception {
        System.out.println("Here on decision tree "+ dataset.length);
        final String[][] dataSet=dataset;
        Map<String,String[]> attribute = new HashMap<>();
        DecisionNode rootNode = new DecisionNode();

        attribute.put("age",new String[]{"<18",">18<25",">25","0"});
        attribute.put("gender",new String[]{"male","female","other","1"});
        attribute.put("ethnicity",new String[]{"himalayan","hilly","terai","2"});
        attribute.put("status",new String[]{"rich","poor","middle","3"});

        rootNode=buildDecisionTree(dataSet,attribute);
        
//        Map<String,String> input = new HashMap<>();
//        input.put("status","rich");
//        input.put("ethnicity","hilly");
//        input.put("gender","female");
//        input.put("age","<18");
//        if(rootNode!=null) {
//        	PrintTree printRoot = new PrintTree();
//            printRoot.print(rootNode, attribute);
//        }
        
//         DecisionNode genderNode = rootNode.getChild("himalayan");
//         DecisionNode ageNode = genderNode.getChild("male");
//         DecisionNode status = ageNode.getChild(">18<25");
//         System.out.println(status.getChild("poor").getClassification());
        attribute.clear();
        return rootNode;
        
    }

    public static DecisionNode buildDecisionTree(String[][] dataset,Map<String,String[]> attribute){
        DecisionNode rootNode = new DecisionNode();
        rootNode = splitCalc(dataset, "", attribute, rootNode, "", "");
        System.out.println("The rootnode attribute is "+rootNode.getAttribute());
        attribute.clear();
        return rootNode;
    }

    public static boolean checkSameOutcome(String[][] dataset){
        int wellcount=0;int notwellcount=0;
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][4].equals("Well")){
                wellcount++;
            }
            if(dataset[i][4].equals("NotWell")){
                notwellcount++;
            }
        }
        if(wellcount==0||notwellcount==0){
            return true;
        }
        return false;
    }

    public static boolean checkEmpty(String[][] dataset){
        int wellcount=0;int notwellcount=0;
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][4].equals("Well")){
                wellcount++;
            }
            if(dataset[i][4].equals("NotWell")){
                notwellcount++;
            }
        }
        if(wellcount==0&&notwellcount==0){
            return true;
        }
        return false;
    }

    public static DecisionNode splitCalc(String[][] dataset,String bestattribute,Map<String,String[]> attribute,DecisionNode Treenode,String subset,String prevcommon){
        // attribute.remove(bestattribute);
    	System.out.println("The attribute length is "+attribute.size());
        if(firstTime==0){
            firstTime++;
            double bestgini=1;String bestattributee="";
            
            for(String key:attribute.keySet()){
            	System.out.print(key+">>>");
                double avgGini=Calculategini(dataset, attribute, key);
                System.out.println("The average weighted gini is "+avgGini);
                System.out.println();
                if(avgGini<bestgini){
                    bestgini=avgGini;
                    bestattributee=key;
                }
                
            }
            System.out.println("The best gini is "+bestgini+" with attribue "+bestattributee);
            Treenode.setAttribute(bestattributee);
            
            //LOOP AFTER BEST GINI AND BEST ATTRIBUTE
            for(int i=0;i<=2;i++){
                System.out.println("loop on best attribute");
                System.out.println(attribute.get(bestattributee)[i]);
                String subset1 = attribute.get(bestattributee)[i];
                System.out.println("The datasetPassed is "+dataset.length);
                String[][] splitData= splitOnAttribute(dataset, attribute.get(bestattributee)[i],Integer.parseInt(attribute.get(bestattributee)[3]));
                System.out.println("split data length "+splitData.length);
                if(checkSameOutcome(splitData)){
                    System.out.println("same value");
                    System.out.println(getCommonOutcome(splitData));
                    DecisionNode samenode = new DecisionNode();
                    samenode.setClassification(getCommonOutcome(splitData));
                    samenode.setAttribute(attribute.get(bestattributee)[i]);
                    Treenode.addChild(subset1,samenode);
                    System.out.println("-^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                }else{
                    splitCalc(splitData,bestattributee,attribute,Treenode,subset1,getCommonOutcome(splitData));
                    System.out.println();
                    if(i==2) {
                    	attribute.clear();
                    	firstTime=0;
                    }
                    System.out.println("-^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                }
                Treedepth=0;
            }
            
        }else{
            Map<String,String[]> newattribute= new HashMap<>();
            newattribute.putAll(attribute);
            newattribute.remove(bestattribute);
            
            double bestgini = 1;String best="";double avgGini=0;
            for(String key:newattribute.keySet()){
                System.out.println("keys are "+key);
                avgGini = Calculategini(dataset, attribute, key);
                System.out.println("second ginis "+avgGini);
                if(avgGini<bestgini){
                    bestgini=avgGini;
                    best=key;
                }
            }
            System.out.println("The second bestgini is "+avgGini+" and key is "+best);
            DecisionNode secondNode = new DecisionNode();
            secondNode.setAttribute(best);
            Treenode.addChild(subset, secondNode);
            
            System.out.println("ADDING NODE "+secondNode.getAttribute()+"on "+Treenode.getAttribute()+ " of "+subset);
            for(int i=0;i<=2;i++){
                System.out.println(attribute.get(best)[i]+"??");
                String[][] splitData= splitOnAttribute(dataset, attribute.get(best)[i],Integer.parseInt(attribute.get(best)[3]));
                System.out.println("split data length "+splitData.length);
                if(checkEmpty(splitData)){
                    System.out.println("Empty dataset");
                    System.out.println("prev common is "+prevcommon);
                    System.out.println("MAEK NEWWW NODEEEEEE"+attribute.get(best)[i]+" TREE att "+secondNode.getAttribute());
                    // Treenode.setClassification(prevcommon);
                    DecisionNode newnode = new DecisionNode();
                    newnode.setClassification(prevcommon);
                    secondNode.addChild(attribute.get(best)[i],newnode);
                    

                    System.out.println("-------------------------------");
                }else if(checkSameOutcome(splitData)){
                    System.out.println("same value");
                    System.out.println("MAKEEEE NEWWWW NODEEEEEEE"+attribute.get(best)[i]+" TREE att "+secondNode.getAttribute());
                    DecisionNode newnode = new DecisionNode();
                    newnode.setClassification(splitData[0][4]);
                    secondNode.addChild(attribute.get(best)[i],newnode);
                    System.out.println(splitData[0][4]);
                   
                    // Treenode.setClassification(splitData[0][4]);
                    
                    
                    System.out.println("-------------------------------");
                    
                } else if(Treedepth==2){
                    System.out.println("max depth reached");
                    System.out.println(getCommonOutcome(splitData));
                    // Treenode.setClassification(getCommonOutcome(splitData));
                    System.out.println("MAKEEEE NEWWWW NODEEEEEEE"+attribute.get(best)[i]+" TREE att "+secondNode.getAttribute());
                    DecisionNode newnode = new DecisionNode();
                    newnode.setClassification(getCommonOutcome(splitData));
                    secondNode.addChild(attribute.get(best)[i],newnode);
                    
                    System.out.println("-------------------------------");

                }else{
                    Treedepth++;
                    System.out.println("Tree depth "+Treedepth);
                    String newsub = attribute.get(best)[i];
                    String common = getCommonOutcome(splitData);
                    System.out.println("SENDING TEEE NODE "+secondNode.getAttribute()); 
                    splitCalc(splitData,best,newattribute,secondNode,newsub,common);
                    System.out.println();
                    System.out.println("-------------------------------");
                }
                
            }
            newattribute.clear();
        }
        
        
        
        return Treenode;

    }

    public static String getCommonOutcome(String[][] dataset){
        int wellcount=0;
        int nowellcount=0;
        String sendOutcome="";
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][4].equals("Well")){
                wellcount++;
            }
            if(dataset[i][4].equals("NotWell")){
                nowellcount++;
            }
        }
        if(wellcount>nowellcount){
            sendOutcome="Well";
        }else if(nowellcount>wellcount){
            sendOutcome="NotWell";
        }else if(wellcount==nowellcount){
            sendOutcome="EqualOutcome";
        }

        return sendOutcome;
    }

    public static String[][] splitOnAttribute(String[][]dataset,String target,Integer targetindex){
        int count=0;
        int countArrayIndex=0;
//        System.out.println("The target is "+target+" and index is "+targetindex);
        for(int i=0;i<dataset.length;i++){
//        	System.out.println(dataset[i][targetindex]);
            if(dataset[i][targetindex].equals(target)){
//            	System.out.println("The hit on splitAttrib");
                count++;
                             
            }
        }
//        System.out.println("The count is "+count);
        String[][] newdataset = new String[count][dataset[0].length];
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][targetindex].equals(target)){
                for(int j=0;j<5;j++){
                    newdataset[countArrayIndex][j]= dataset[i][j];
                    
                }
                countArrayIndex++;
            }
        }
//        System.out.println("The new dataset length "+newdataset.length);
        return newdataset;
    }


    public static double Calculategini(String[][]dataset,Map<String,String[]> attributePassed,String target){
        Map<String,String[]> attribute = new HashMap<>();
        attribute.putAll(attributePassed);
        
    	HashMap<String,Integer> counts = new HashMap<>();
        counts.put("Well", 0);
        counts.put("NotWell", 0);
        Integer index = Integer.parseInt(attribute.get(target)[3]);
        double avgGini = 0;
        int flag=0;

        for(int i=0;i<=2;i++){   // LOOP FOR ATTRIBUTE SUBSET
            System.out.print(attribute.get(target)[i] +"  ");
            for(int j=0;j<dataset.length;j++){  //LOOP FOR DATASET
                if(dataset[j][index].equals(attribute.get(target)[i])){  //IF DATASET 
                    flag=1;
                    for(String key:counts.keySet()){     
                        if(dataset[j][4].equals(key)){
                            int value = counts.get(key);
                            counts.put(key,value+1);
                        }
                    }
                }
            }
            if(flag!=0){
                System.out.println(counts.get("Well")+" "+counts.get("NotWell"));
                double totalsubset = counts.get("Well")+counts.get("NotWell");
                double Singlegini = 1-(Math.pow((counts.get("Well")/totalsubset),2)+Math.pow((counts.get("NotWell")/totalsubset),2)); 
                System.out.println(Singlegini);
                avgGini = avgGini+(Singlegini*(totalsubset/dataset.length));
            }else if(flag==0){
                System.out.println(counts.get("Well")+" "+counts.get("NotWell"));
                System.out.println("0");
                avgGini =avgGini+0;
                
            }
            flag=0;
            counts.put("Well", 0);
            counts.put("NotWell", 0);
            
        }
        attribute.clear();
        
        counts.clear();
        return avgGini;
    }

    
}

