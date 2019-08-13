package MyjavaTest;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MST_Prim {

    private int[] edgeTo; // edgeTo[v] = shortest edge from tree vertex to no_tree vertex
    private double[] disTo; // disTo[v] = weight of shortest such edge
    //build min queue heap :min_heap
    private PriorityQueue<Node> pq; // query for the min_weight connected to the tree
    private boolean[] marked; // marked[v] = show if the vertex has been visited


    MST_Prim(WeightedGraph G){
        disTo = new double[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        PriorityQueue<Node> pq = new PriorityQueue<>(G.E(),new EdgeComparator());
        for(int i=0;i<G.V();i++){
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        for(int i=0;i< G.V();i++)
            if(!marked[i]) prim(G,i);
    }

    private void prim(WeightedGraph G, int s){
        Node sNode = G.HeadNodeList().get(s);
        disTo[s] = 0.0;
        pq.add(sNode);
        while(!pq.isEmpty()){
            Node v = pq.poll();
            int i = v.getIndex();
            scan(G,i);
        }
    }

    private void scan(WeightedGraph G, int v){
        marked[v] = true;
        Node node = G.HeadNodeList().get(v);
        while(node!= null){
            node = node.getNext();
            pq.add(node);
        }
        node = pq.
    }
    class EdgeComparator implements Comparator<Node> {
        // overriding compare()method of Comparator
        public int compare(Node n1,Node n2){
            if(n1.getWeight()<n2.getWeight()) return -1;
            else if (n1.getWeight()>n2.getWeight()) return 1;
            return 0;
        }

    }

}