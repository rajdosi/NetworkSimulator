package NetworkSimBase;

import java.util.HashSet;
import java.util.Set;

public class NetworkNode
{
    public int nodeID = -1;
    public int localXCoordinate;
    public int localYCoordinate;
    public int neighborCount;
    public double lastUpdatedTime;
    
    public Set<Integer> neighbourList = new HashSet<>();
}
