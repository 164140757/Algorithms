package MyjavaTest;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
class OpenData{
    public static boolean contains = false;
}
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
            int v1 = node.getIndex();
            OpenData.contains = false;
            if (!marked[v1]&disTo[v1] > node.getWeight()) {
                    disTo[v1] = node.getWeight();
                    Iterator<Node> nvalue = pq.iterator();
                    while(nvalue.hasNext()){
                        if(nvalue.next().getIndex() == node.getIndex()){
                            OpenData.contains = true;//use other class to save common data, to enlarge the scale of the variable
                            break;
                        }
                    }
                    if(OpenData.contains) {
                        int index = node.getIndex();
                        // if there's an element, delete it and re-insert to update the heap
                       for(Iterator<Node> iterator = pq.iterator();iterator.hasNext();){
                           Node n = iterator.next();
                           if(n.getIndex() == node.getIndex()){
                               iterator.remove();//can only be changed by iterator
                           }
                       }
                        pq.add(node);

                    }
                    else pq.add(node);
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
        In in = new In("src/main/data/txtdata/11EWG.txt");
        WeightedGraph g = new WeightedGraph(in, 1,true);
        MST_Prim mst_prim = new MST_Prim(g);
        System.out.printf("\nWeight: %f\n",mst_prim.weight);
    }
}