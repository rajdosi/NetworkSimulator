package NetworkSimBase;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import NetworkSimBase.NetworkNode;

public class Project{
    
	private static int count = 0;
    private static Map<Integer, NetworkNode> GlobalDataStructure = new HashMap<>();
    
//    public void broadcast(int num)
//    {
//       int i,j=0;
//       for(i=0;;i++)
//       {
//           commonVariables.xCordinate[j] = commonVariables.xCordinate[j] + i;
//           if(commonVariables.xCordinate[j] == commonVariables.xCordinate[j+1])
//           {
//               networkNode.localXCoordinate = commonVariables.xCordinate[i];
//               break;
//       //System.out.println("The nieghbours of node "+num+" are:");
//       } 
//       for(i=0;;i++)
//            }
//       {
//           commonVariables.yCordinate[j] = commonVariables.yCordinate[j] + i;
//           if(commonVariables.yCordinate[j] == commonVariables.yCordinate[j+1])
//           {
//               break;
//           }
//       //System.out.println("The nieghbours of node "+num+" are:");
//       } 
//    } 
    
    public static void init() throws IOException{
    	FileReader in = new FileReader("/home/wazza/workplace/NetworkSimulator/NetworkSim/src/initFile.txt");
        BufferedReader br = new BufferedReader(in);
        
        try {
        	String line;
        	while ((line=br.readLine())!=null) {
        		NetworkNode temporaryNode = new NetworkNode();
        		int comma = line.indexOf(",");
        		int del = line.indexOf("|");
                int dot=line.indexOf(".");
                int at =line.indexOf("@");
                
                temporaryNode.nodeID= Integer.parseInt(line.substring(0,del));
                temporaryNode.lastUpdatedTime= Double.parseDouble(line.substring(at+1,line.length()));
                temporaryNode.localXCoordinate = Integer.parseInt(line.substring(del+1,comma));
                temporaryNode.localYCoordinate = Integer.parseInt(line.substring(comma+1,at));
                
                GlobalDataStructure.put(temporaryNode.nodeID,temporaryNode);
                count++;
        	} 
        }
        catch (Exception e){
        	e.printStackTrace();
        	}
    }
    
    public static void main(String[] args) throws IOException {
    	init();
    	
    	for (int i=1; i<GlobalDataStructure.size() + 1; i++) {
    		System.out.println(GlobalDataStructure.get(i).localXCoordinate);		
    	}
    }
}

