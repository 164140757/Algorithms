package MyjavaTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * BFS O(V+E)
 */
public class BFS{
  private boolean[] marked;//marked[v] s->v path exist (GRAY pattern)
  private double[] distTo;//disto[v]  s->v path(shortest) pnly when every edge's weight is equal
  private int[] edgeTo;//edgeTo[v] preVertex
  private static final int INFINITY = Integer.MAX_VALUE;

  public BFS(Graph G,int s){//from s to the other vertexs
    //initialize a graph
      marked = new boolean[G.V()];
      distTo = new double[G.V()];
      edgeTo = new int[G.V()];
      for(int i=0;i<G.V();i++){
        distTo[i] = INFINITY;
      }
      //even if s is not valid ,the graph has been created(allocated)
      validateVertex(s);
      bfs(G,s);
  }
  //BFS from single source
  private void bfs(Graph g,int s){
    Queue<Integer> q = new LinkedList<>();
    marked[s] = true;
    distTo[s] = 0;
    q.add(s);
    while(!q.isEmpty()){
      int v = q.remove();
      Node n =g.headNodeList.get(v).next;//the first element
      while(n!=null){
        int index = n.getIndex();
        if(!marked[index]){
          distTo[index] =distTo[v]+n.getWeight();// mathematical induction
          edgeTo[index] = v;
          marked[index] = true;
          q.add(index);
        }
        n = n.next;  
      }
    }
  }
  public boolean hasPathTo(int v){
    validateVertex(v);
    return marked[v];
  }
  public double distTo(int v){
    validateVertex(v);
    return distTo[v];
  }
  
  public Iterable<Integer> pathTo(int v){
    validateVertex(v);

    if(!hasPathTo(v)) return null;
    Stack<Integer> path = new Stack<Integer>();//Stack to review the nodes in the path
    int x;
    for (x=v;distTo[x]!=0;x = edgeTo[x]){
      path.push(x);
    }
    path.push(x);
    return path;
  }

  private void validateVertex(int v){
    int V = marked.length;
    if(v <0 || v>V) throw new IllegalArgumentException("vertex"+v+"is not between 0 and "+(V-1));
  }

   public static void main(String[] args) {
    In in = new In("src/main/data/10EWG_bfs.txt");
    Graph g = new Graph(in,1);
    int source = 0;
    BFS bfs = new BFS(g,source);//one time bfs
    for(int v = 0;v<g.V();v++){//check if every vertex is connected
      if(bfs.hasPathTo(v)){
        //show path
        for(int x:bfs.pathTo(v)){
          //pathTo:instance of iterable
          if(x == source)System.out.printf("%d",x);
          else System.out.printf("%d<- ",x);
      }
      System.out.println();
    }
    else{
      System.out.printf("%d to %d : not connected\n",source,v);
    }
  }
  }
}