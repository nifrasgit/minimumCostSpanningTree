import java.util.*;  //to use Scanner class
 
public class MinimumCostSpanningTree{
  
  Scanner S= new Scanner(System.in);
  final double INFTY=Double.POSITIVE_INFINITY;
  int n; //number of nodes
  double cost[][];
  double minCost;
 
  int near[];
  boolean addedToMCST[];
  Edge spt[];
   
  MinimumCostSpanningTree(){ //constructor
  initialiseFields();
  inputCostMatrix();
  printCostMatrix();
  findMinCostTree();
  printDetails();
  }//end of constructor
  /******************************/
 void initialiseFields()
 {
	System.out.print("Give the Number of nodes:  ");
	n=S.nextInt();
	cost=new double[n][n];
	spt=new Edge[n-1];
	near=new int[n];
	addedToMCST=new boolean[n];
		
	for(int i=0;i<n;i++)
	{
		near[i]=-1;
		addedToMCST[i]=false;
	}
  }
  /******************************/
 void inputCostMatrix(){
  int iNode,  jNode; double lcost;
  for(int i=0; i< n; i++){
    for(int j=0; j<n; j++)
      cost[i][j]=INFTY;
    }
  System.out.println("Give the cost in the form edge edge cost ");
  System.out.println("Ignoring the non-existing edges");
  System.out.println("Finishing with 9999 9999 9999"); 
  iNode=S.nextInt(); 
  jNode=S.nextInt();
  lcost=S.nextDouble();
  
  while(iNode!=9999){
    cost[iNode][jNode]=lcost;
    cost[jNode][iNode]=lcost;
    iNode=S.nextInt(); 
	jNode=S.nextInt(); 
	lcost=S.nextDouble();
    }
  }
  /******************************/
  void findMinCostTree(){
  int iNode,jNode; 
  spt[0]=minCostLink(); //to start with
  iNode=spt[0].iNode(); 
  jNode=spt[0].jNode(); 
  minCost=spt[0].cost();
  
  for(int i=0; i< n; i++)
    if(cost[iNode][i] > cost[jNode][i]) 
		near[i]=jNode;
    else 
		near[i]=iNode;
	addedToMCST[iNode]=true; 
	addedToMCST[jNode]=true;
  
  int nextNear;
  for(int num=1; num<n-1;num++){
    spt[num]=minCostNearLink();
    nextNear=spt[num].iNode();
    addedToMCST[nextNear]=true;
    minCost=minCost+spt[num].cost();
    
	for(int k=0; k<n; k++) //update near[]
		if(!addedToMCST[k] &&(cost[near[k]][k] > cost[nextNear][k])) 
		near[k]=nextNear;
    
    }
  }
  /******************************/  
  
  Edge minCostLink(){
  int iNode=9999, jNode=9999; 
  double minC=INFTY;
  for(int i=0; i< n; i++)
    for(int j=0; j< n; j++)
      if(cost[i][j]<minC)
	  {
		iNode=i;
		jNode=j;
		minC=cost[i][j];
      }
   
   return new Edge(iNode, jNode, minC);
  }
  /******************************/
  Edge minCostNearLink()
  {
	double lcost=INFTY; 
	int iLink=9999;
	 
	for(int i=0; i<n;i++){
		if(!addedToMCST[i] & (lcost > cost[i][near[i]]))
		{
			iLink=i; 
			lcost=cost[i][near[i]];
		}
  }
 
  return new Edge(iLink, near[iLink], lcost);
  }
  /******************************/  
   void printCostMatrix(){
    for(int i=0;i<n;i++){
		for(int j=0; j<n; j++)
			if(cost[i][j]==INFTY) 
				System.out.print("\tinf");
			else 
				System.out.print("\t"+cost[i][j]);
		System.out.println();
    }
  }
  /******************************/
  void printDetails(){
    System.out.println("Near Link addedToMCST to each node ");
    int link,nlink;
    
	//minimum cost in each node
	for(int i=0;i<n-1;i++){
      link=spt[i].iNode(); nlink=spt[i].jNode();
      System.out.println(nlink+" <==> "+link+" with cost "+spt[i].cost());
    }
	
	//Total No Of costs
    System.out.println("The Minimum Total Cost = "+minCost);
    
    
    }

  /******************************/
  public static void main(String arg[]){
	 MinimumCostSpanningTree mspt=new MinimumCostSpanningTree();
  }
  /******************************/
}
/** vertex vertex cost
0 1 10                                                                           
0 4 45                                                                           
0 3 30
1 2 50                                                                           
1 4 40                                                                           
1 5 25                                                                           
2 4 35                                                                           
2 5 15                                                                           
3 5 20                                                                           
4 5 55                                                                           
9999 9999 9999
*/
