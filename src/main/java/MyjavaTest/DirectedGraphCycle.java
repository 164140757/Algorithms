package MyjavaTest;

import java.util.Stack;

/**
 * The class represents a data type for determining whether an edge-weighted digraph
 * has a directed cycle
 * implemented by depth-first search
 */
public class DirectedGraphCycle {
    private boolean[] marked; // marked[v] = has vertex v been marked?
    private String[] lastNode; // lastNode[v] = previous node on path to v
    private boolean[] onStack; // onStack[v] = is vertex on the stack?
    private Stack<Node> cycle; // directed cycle (or null if no such cycle)

    /**
     * Determines whether the edge-weighted digraph{@code G} has a directed cycle and
     * if so, find such a cycle
     */
    public DirectedGraphCycle(WeightedGraph G) {
        marked = new boolean[G.V()];
        lastNode = new String[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }
    private void dfs(WeightedGraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        
    }
}
