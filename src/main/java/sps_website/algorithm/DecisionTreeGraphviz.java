package sps_website.algorithm;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
public class DecisionTreeGraphviz {
	private static int nodeCounter = 0;

    public static String generateDot(DecisionNode node) {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph DecisionTree {\n");
        dot.append("  node [shape=box];\n");  // This sets the shape of nodes to boxes
    
        generateDotRecursive(node, dot);
    
        dot.append("}\n");
        return dot.toString();
    }
    
    private static int generateDotRecursive(DecisionNode node, StringBuilder dot) {
        int nodeId = nodeCounter++;
    
        if (node.isclassification()) {
            dot.append("  ").append(nodeId).append(" [label=\"").append(node.getClassification()).append("\"];\n");
        } else {
            dot.append("  ").append(nodeId).append(" [label=\"").append(node.getAttribute()).append("\"];\n");
            for (Map.Entry<String, DecisionNode> entry : node.getChildren().entrySet()) {
                int childId = generateDotRecursive(entry.getValue(), dot);
                dot.append("  ").append(nodeId).append(" -> ").append(childId).append(" [label=\"").append(entry.getKey()).append("\"];\n");
            }
        }
    
        return nodeId;
    }
    

    public static void thisMain(DecisionNode root) {
        // Create your decision tree here
        // DecisionNode root = new DecisionNode();
        // ... build the tree ...

        // Generate the DOT description
        String dotDescription = generateDot(root);
        System.out.println(dotDescription);
        try {
            FileWriter writer = new FileWriter("C:\\Users\\hzeru\\OneDrive\\Desktop\\decisionTree.txt");
            writer.write(dotDescription);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
