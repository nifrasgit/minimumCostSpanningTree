
public class Edge{
   private int iNode;
   private int jNode;
   private double cost;
  public  Edge(int nd1, int nd2, double cst){
      
      cost=cst;
      iNode=nd1; jNode=nd2; //order may matter...
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
