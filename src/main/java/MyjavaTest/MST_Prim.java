package MyjavaTest;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MST_Prim {

    private int[] edgeTo; // edgeTo[v] = shortest edge from tree vertex to no_tree vertex
    private double[] disTo; // disTo[v] = weight of shortest such edge
    //build min queue heap :min_heap
    private PriorityQueue<Node> pq; // query for the min_weight connected to the tree
    private boolean[] marked; // marked[v] = show if the vertex has been visited
    double weight;

    MST_Prim(WeightedGraph G){
        disTo = new double[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        pq = new PriorityQueue<>(G.V(),new EdgeComparator());
        for(int i=0;i<G.V();i++){
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        for(int i=0;i< G.V();i++)
            if(!marked[i]) prim(G,i);
    }

    private void prim(WeightedGraph G, int s){
        Node sNode = G.HeadNodeList().get(s);
        disTo[s] = 0.0;
        marked[s] = true;
        pq.add(sNode);
        while(!pq.isEmpty()){
            Node v = pq.poll();
            System.out.printf("->%s",v.getTag());
            int i = v.getIndex();
            scan(G,i);
        }
        // hierarchical traversal
    }


    private void scan(WeightedGraph G, int v){
        Node node = G.HeadNodeList().get(v).getNext();

        while (node != null) {
            boolean add_node = true;// check if pq has node yet
            int v1 = node.getIndex();

            if (!marked[v1]) {

                if (disTo[v1] > node.getWeight()) {
                    disTo[v1] = node.getWeight();
                    if(pq.contains(node)) {
                        pq.remove(node); // if there's an element, delete it and re-insert to update the heap
                        pq.add(node);
                    }
                    else pq.add(node);
                }


            }
            node = node.getNext();
        }

        if(pq.isEmpty()) return;
        node = pq.peek(); // min & unvisited
        int v2 = node.getIndex();
        marked[v2] = true;
        weight += disTo[v2];

    }



    class EdgeComparator implements Comparator<Node> {
        // overriding compare()method of Comparator
        public int compare(Node n1,Node n2){
            int index1 = n1.getIndex();
            int index2 = n2.getIndex();
            if(disTo[index1]<disTo[index2]) return -1;
            else if (disTo[index1]>disTo[index2]) return 1;
            return 0;
        }
    }



    public static void main(String[] args) {
        In in = new In("src/main/data/txtdata/prim_book.txt");
        WeightedGraph g = new WeightedGraph(in, 1,true);
        MST_Prim mst_prim = new MST_Prim(g);
        System.out.printf("\nWeight: %f\n",mst_prim.weight);
    }
}