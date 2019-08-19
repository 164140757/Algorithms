package MyjavaTest;

import java.util.Stack;

/**
 * The class represents a data type for determining whether an edge-weighted digraph
 * has a directed cycle
 * implemented by depth-first search
 */
public class DirectedGraphCycle {
    private boolean[] marked; // marked[v] = has vertex v been marked?
    private Node[] lastNode; // lastNode[v] = previous node on path to v
    private boolean[] onStack; // onStack[v] = is vertex on the stack?
    private Stack<Node> cycle; // directed cycle (or null if no such cycle)
    
}
