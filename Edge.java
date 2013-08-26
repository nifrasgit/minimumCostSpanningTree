
public class Edge{
	private int iNode;
	private int jNode;
	private double cost;
	public  Edge(int node1, int node2, double cost)
	{
      this.cost=cost;
      iNode=node1; 
	  jNode=node2; 
	}
  
   public int iNode(){
      return iNode;	
    }
   public int jNode(){
      return jNode;
    }
  public  double cost(){
      return cost;
      }
  }
