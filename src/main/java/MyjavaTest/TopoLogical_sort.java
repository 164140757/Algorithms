package MyjavaTest;
import java.util.Stack;
public class TopoLogical_sort{
  private Stack<Integer> TPL_stack = new Stack<>();//list to record nodes in TopoLogical_sort
  public TopoLogical_sort(Graph g,int s){
    DFS dfs = new DFS_TPL(g,s);
    for(int v = 0;v<g.V();v++)
      TPL_stack = dfs.pathTo(v);
    
  }
  class DFS_TPL extends DFS {

  }
}