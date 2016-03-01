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
    private static int distanceChecked = 2;
    private static int[][] networkMap = new int[400][400];
    
    public static void broadcast(int xCoordinate, int yCoordinate, NetworkNode currentNetworkNode) {
    	count++;
    	if (xCoordinate == -1 || yCoordinate == -1 || xCoordinate == 400 || yCoordinate == 400 || count > distanceChecked )
    		return;
    	if (networkMap[xCoordinate][yCoordinate] != -1){ 		
    		currentNetworkNode.neighborCount++;
    		currentNetworkNode.neighbourList.add(networkMap[xCoordinate][yCoordinate]);
    	}
    	broadcast(xCoordinate-1,yCoordinate,currentNetworkNode);
    	broadcast(xCoordinate-1,yCoordinate-1,currentNetworkNode);
    	broadcast(xCoordinate,yCoordinate-1,currentNetworkNode);
    	broadcast(xCoordinate+1,yCoordinate-1,currentNetworkNode);
    	broadcast(xCoordinate+1,yCoordinate,currentNetworkNode);
    	broadcast(xCoordinate+1,yCoordinate+1,currentNetworkNode);
    	broadcast(xCoordinate,yCoordinate+1,currentNetworkNode);
    	broadcast(xCoordinate-1,yCoordinate+1,currentNetworkNode);
    }
    
    public static void setNetworkMap() {
    	for (int i=0;i<400;i++)
    		for (int j=0;j<400;j++)
    			networkMap[i][j] = -1;
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
                count++;
        	} 
        }
        catch (Exception e){
        	e.printStackTrace();
        	}
    }
    
    public static void main(String[] args) throws IOException {
    	init();
    	setNetworkMap();
    	
    	for (int i = 0; i < GlobalDataStructure.size(); i++ ) {
    		count=0;
    		broadcast(GlobalDataStructure.get(i+1).localXCoordinate, GlobalDataStructure.get(i+1).localYCoordinate, GlobalDataStructure.get(i+1));	
    	}		
    }
}

