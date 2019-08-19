package MyjavaTest;

import java.util.LinkedList;
import java.util.Queue;

public class BellmanFord {
    private double[] distTo; // disTo[v] = distance of shortest s->v path
    private String[] lastNode; // lastNode[v] = index of the last node of on shortest s -> v path
    private boolean[] onQueue; // onQueue[v] = is v currently on Queue
    private Queue<Integer> queue; // queue of vertices to relax
    private int cost; // number of cost to relax
    private Iterable<Node> cycle; // negative cycle (or null if no such edges)

    /**
     * computes a shortest paths tree from {@code s} to every other vertex in
     * @param G the acyclic digraph
     * @param s the source vertex
     * //@throws  illegalArgumentException unless{@code 0 <= s < V}
     */
    public BellmanFord(WeightedGraph G,int s){
        distTo = new double[G.V()];
        lastNode = new String[G.V()];
        onQueue = new boolean[G.V()];
        for (int v =0;v< G.V();v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        //Bellman-Ford algorithm
        queue = new LinkedList<Integer>();
        queue.add(s);
        onQueue[s] = true;
        while(!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.remove();
            onQueue[v] = false;
            relax(G,v);
        }
       // assert check(G,s);
}
// relax vertex v and other endpoints on queue if changed
    private void relax(WeightedGraph G, int v){
        Node node = G.HeadNodeList().get(v).getNext();
        while(node != null){
            int w = node.getIndex();
            if(distTo[w]>distTo[v]+node.getWeight()){
                distTo[w] = distTo[v] + node.getWeight();
                lastNode[w] = node.indextoTag(v);
                if(!onQueue[w]){
                    queue.add(w);
                    onQueue[w] = true;
                }
            }
            if(cost++ % G.V() == 0){
                findNegativeCycle();
                if(hasNegativeCycle())return ;
            }
            node = node.getNext();
        }
    }

    /**
     * @return a negative cycle Return a negative cycle reachable from the source vertex {@code s}, or{@code null}
     * if there is no such cycle.
     */
    private boolean hasNegativeCycle(){
        return cycle != null;
    }

    // by finding a cycle in predecessor graph
    private void findNegativeCycle(){
        int V = distTo.length;
        WeightedGraph wg = new WeightedGraph(V);
        for(int v = 0;v < V;v++){
            if(lastNode[v]!=null) wg.addEdge((int)lastNode[v].charAt(0)-(int)'A'+1,v,0);//we don't need value here -> 0

        }


    }
    public static void main(String[] args) {
       // BellmanFord bellmanford = new BellmanFord();
    }
}
