package MyjavaTest;

import java.util.Deque;
import java.util.LinkedList;

public class TopoLogical_sort{
  private Deque<Integer> TPL_list = new LinkedList<>();//list to record nodes in TopoLogical_sort
  private TopoLogical_sort(WeightedGraph g, int s){
    DFS dfs = new DFS_TPL(g,s,TPL_list);
  }
  private Deque<Integer> getList(){
   return TPL_list;
  }
  public static void main(String[] args) {
    In in = new In("src/main/data/8EWG_unconnect.txt");
    WeightedGraph g = new WeightedGraph(in,1);
    int source = 0;
    TopoLogical_sort tpls = new TopoLogical_sort(g, source);
    Deque<Integer> Tpl_list = tpls.getList();
    for (Integer x:Tpl_list){
      if(x == source)
      System.out.printf("%d",x);
      else
      System.out.printf("->%d",x);
    }
  }
}
// method of dfs should be rewrote, create a subclass of DFS
class DFS_TPL extends DFS {
   DFS_TPL(WeightedGraph g, int s, Deque<Integer> list){
    super(g, s);
    //renew marked[] at least
    super.reset_marked();
    dfs_TPL(g,s,list);
  }
  private void dfs_TPL(WeightedGraph g, int v, Deque<Integer> list){
    //inheritated marked , time , d ...
    //####important！！！！ after inheritatance, marked[](fields) remains the same as its superclass 
    marked[v] = true;// v is reachable->gray pattern in the book
    Node n = g.HeadNodeList().get(v).getNext();
    while (n != null) {
      int index = n.getIndex();
      if (!marked[index]) {
        dfs_TPL(g, index,list);
      }
      n = n.getNext();
    }
    list.addFirst(v);//add to the front 
  }
}