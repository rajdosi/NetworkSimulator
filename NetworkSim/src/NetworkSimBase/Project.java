package NetworkSimBase;

import java.io.*;
import java.util.Scanner;
import NetworkSimBase.NetworkNode;

public class Project {
    public char myChar[] = new char[500];
    public int nodenumber[]=new int[100];
    public int x[]=new int[50];
    public int y[]=new int[50];
    public double time=new double[51]{0};
    
    public void broadcast(int num)
    {
       int i,j=0;
       for(i=0;;i++)
       {
           x[j] = x[j] + i;
           if(x[j] == x[j+1])
           {
               NetworkNode. x[i];
               break;
            }
       //System.out.println("The nieghbours of node "+num+" are:");
       } 
       for(i=0;;i++)
       {
           y[j] = y[j] + i;
           if(y[j] == y[j+1])
           {
               break;
           }
       //System.out.println("The nieghbours of node "+num+" are:");
       } 
    } 
    
    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("/Users/jayeshparekh/Desktop/manet/mobilityfile.txt");
        BufferedReader br = new BufferedReader(in);
        int i=0,j=0,length=0,l=1,comma,del,dot,at;
        double k;
        
        time[0]=0.0;

        try {
            
            String line;
            while ((line=br.readLine())!=null)
            {
               
                comma = line.indexOf(",");
                del = line.indexOf("|");
                dot=line.indexOf(".");
                at =line.indexOf("@");
                int size = line.length();
                nodenumber[i] = Integer.parseInt(line.substring(0,del));

                time[l]= Double.parseDouble(line.substring(at+1,size));
                x[j] = Integer.parseInt(line.substring(del+1,comma));
                y[j] = Integer.parseInt(line.substring(comma+1,at));
                j++;
                i++;
                l++;

            }

                   
        }
        finally {
            
        }
        for(i=0;i<50;i++)
        {
            System.out.println(nodenumber[i]);
            
        }
        for(i=0;i<40;i++)
      {
          long tx = (long) (time[i+1]-time[i]);
          try {
              Thread.sleep(tx*10);
            } catch (InterruptedException ie) {
                //Handle exception
            }
            System.out.println("node "+nodenumber[i]+" at "+x[i]+","+y[i]+" at time "+time[i]);
            broadcast(nodenumber[i]);
        }
        broadcast(nodenumber[0]);
        
       

    }
    
}

