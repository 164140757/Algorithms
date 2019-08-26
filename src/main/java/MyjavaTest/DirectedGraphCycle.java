package MyjavaTest;

import java.util.Iterator;
import java.util.Stack;

/**
 * The class represents a data type for determining whether an edge-weighted digraph
 * has a directed cycle
 * implemented by depth-first search
 */
public class DirectedGraphCycle {
    private boolean[] marked; // marked[v] = has vertex v been marked?
    private Node[] preNode; // preNode[v] = previous node on path to v
    private boolean[] onStack; // onStack[v] = is vertex on the stack?
    private Stack<Node> cycle; // directed cycle (or null if no such cycle)
    /**
     * Determines whether the edge-weighted digraph{@code G} has a directed cycle and
     * if so, find such a cycle
     */
    public DirectedGraphCycle(WeightedGraph G) {
        marked = new boolean[G.V()];
        preNode = new Node[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }
    private boolean hasCycle(){
        return cycle!=null;
    }
    private void dfs(WeightedGraph G, int v){
        onStack[v] = true;
        marked[v] = true;

        Node preNode1 = G.HeadNodeList().get(v);
        Node node = preNode1.getNext();
        while(node!=null){
            int w = node.getIndex();
            // short circuit if directed cycle found
            if(cycle !=null) return;
            // found new vertex, so recur
            else if(!marked[w]){
                preNode[w] = preNode1;
                dfs(G,w);
            }
            //find cycle
            else if(onStack[w]){
                cycle = new Stack<Node>();
                // index = the index of current node visited
                int index = preNode1.getIndex();
                while(index!=w){
                    cycle.add(preNode1);
                    preNode1 = preNode[index];
                    index = preNode1.getIndex();
                }
                cycle.add(preNode1);
            }
            else{
                node = node.getNext();
            }
        }
        onStack[v] = false;

    }

    public static void main(String[] args) {
        In in = new In("src/main/data/txtdata/8EWG_unconnect.txt");
        WeightedGraph G = new WeightedGraph(in,1,false);
        DirectedGraphCycle DGC = new DirectedGraphCycle(G);
        DGC.dfs(G,0);
        if(DGC.hasCycle()) {
            Iterator<Node> iterator = DGC.cycle.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next().getTag());
            }
        }
        else System.out.println("No cycle has been found!");
    }
}
