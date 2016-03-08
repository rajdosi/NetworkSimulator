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
    
	private static int globalCount = 0;
    private static Map<Integer, NetworkNode> GlobalDataStructure = new HashMap<>();
    private static int distanceChecked = 2;
    private static int[][] networkMap = new int[400][400];
    
    public static void broadcast(int xCoordinate, int yCoordinate, NetworkNode currentNetworkNode) {
    	int xInitial,yInitial,xFinal,yFinal;
    	xInitial = (xCoordinate-distanceChecked > 0)?xCoordinate - distanceChecked : 0;
    	yInitial = (yCoordinate-distanceChecked > 0)?yCoordinate - distanceChecked : 0;
    	xFinal = (xCoordinate + distanceChecked < 399)?xCoordinate + distanceChecked : 399 ;
    	yFinal = (xCoordinate + distanceChecked < 399)?xCoordinate + distanceChecked : 399;	
    	for (int i= xInitial; i<=xFinal; i++){
    		for (int j= yInitial; j<=yFinal; j++){
    			if (i == xCoordinate && j == yCoordinate)
    				continue;
    			if (networkMap[i][j] != -1){ 		
    				currentNetworkNode.neighborCount++;
    				currentNetworkNode.neighbourList.add(networkMap[i][j]);
    			}
    		}
    	}
    }
    
    public static void setNetworkMap() {
    	for (int i=0;i<400;i++)
    		for (int j=0;j<400;j++)
    			networkMap[i][j] = -1;
    }
    
    public static void printNetworkMap() {
    	for (int i=0;i<400;i++)
    	{
    		for (int j=0; j<400; j++){
    			System.out.print(networkMap[i][j]+" ");
    		}
    		System.out.println("");
    	}
    }
    
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
                
                networkMap[temporaryNode.localXCoordinate][temporaryNode.localYCoordinate] = temporaryNode.nodeID;
                
                GlobalDataStructure.put(temporaryNode.nodeID,temporaryNode);
                globalCount++;
        	} 
        }
        catch (Exception e){
        	e.printStackTrace();
        	}
    }
    
    public static void main(String[] args) throws IOException {
    	setNetworkMap();
    	init();
    	
    	for (int i = 0; i < GlobalDataStructure.size(); i++ ) {
    		broadcast(GlobalDataStructure.get(i+1).localXCoordinate, GlobalDataStructure.get(i+1).localYCoordinate, GlobalDataStructure.get(i+1));	
    	}	
    	
    	for (int i = 0;i < GlobalDataStructure.size(); i++) {
    		System.out.println((i+1)+":"+GlobalDataStructure.get(i+1).neighborCount + "\t" +GlobalDataStructure.get(i+1).neighbourList);
    	} 		
    }
}

