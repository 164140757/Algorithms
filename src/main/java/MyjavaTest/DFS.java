package MyjavaTest;

import java.util.Stack;

public class DFS{
  private boolean[] marked;//marked reachable
  private int[] edgeTo; // last edge 
  private final int s = 0; // source vertex
  private int[] d;// time to start
  private int[] f;// time to finish
  private int time = 0;// global time 

  public DFS(Graph g,int s){
    marked = new boolean[g.V()];
    edgeTo = new int[g.V()];
    d = new int[g.V()];
    f = new int[g.V()];
    validateVertex(s);
    dfs(g,s);
  }
  private void dfs(Graph g,int v){
    marked[v] = true;// v is reachable
    time = time +1;//discover
    d[v] = time;
    Node n = g.headNodeList.get(v).next;
    while(n!=null){
      int index = n.getIndex();
      if(!marked[index]){
        edgeTo[index] = v;
        dfs(g,index);
      }
      n = n.next;
    }
    time = time + 1;// finish visiting one node ,time plus one
    f[v] = time;
  }
  
  public boolean hasPathTo(int v) {
    validateVertex(v);
    return marked[v];
  }
  public Stack<Integer> pathTo(int v){
    validateVertex(v);
    if(!hasPathTo(v)) return null;
    Stack<Integer> path = new Stack<Integer> ();
    for (int x = v;x!=s;x = edgeTo[x])
      path.push(x);
    path.push(s);
    return path;
  }

  private void validateVertex(int v){
    int V = marked.length;
    if(v<0 || v>V) 
      throw new IllegalArgumentException("vertex "+v+" is not between 0 and "+(V-1));
  }
  public static void main(String[] args) {
    In in = new In("src/main/data/DFSdataBook.txt");
    Graph g = new Graph(in,1);
    int source = 0;
    DFS dfs = new DFS(g, source);

    for(int v = 0;v<g.V();v++){//check if every vertex is connected
      if(dfs.hasPathTo(v)){
        Stack<Integer> path = new Stack<Integer>();
        path = dfs.pathTo(v);
        while(!path.empty()){
          int x = path.pop();
          if (x == source)
            System.out.printf("%d (%d/%d)", x,dfs.d[x],dfs.f[x]);
          else
          System.out.printf("->%d (%d/%d)",x, dfs.d[x], dfs.f[x]);
        } 
        System.out.println();
    }
    else{
      System.out.printf("%d to %d : not connected\n",source,v);
    }
  }
}
}
