package MyjavaTest;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MST_Kruskal {
    private double weight;
    private Queue<Node> mst = new LinkedList<>();
    MST_Kruskal(WeightedGraph G){
        //build min queue heap :min_heap
        PriorityQueue<Node> pq = new PriorityQueue<>(G.E(),new EdgeComparator());
        //get weight of each edges
        for(int i = 0;i< G.V();i++){
            Node node = G.HeadNodeList().get(i);
            node = node.getNext();
            while(node != null){
                //double weight = node.getWeight();
                pq.add(node);
                node = node.getNext();

            }
        }

        // run greedy algorithm
        UF uf = new UF(G.V());
        while(!pq.isEmpty()){
            Node min_node = pq.poll();
            double weight = min_node.getWeight();
            // get the edges' two vertices
            int indexNode1 = min_node.getIndex();
            int indexNode2 = min_node.getHead();
            if(uf.find(indexNode1)!=uf.find(indexNode2)){
                uf.union(indexNode1,indexNode2);
                mst.add(min_node);
            }
        }

    }
    void check(){
        for(Node node:mst){
            String headTag =Character.toString((char)( node.getHead()+(int)'A'));
            String tag = node.getTag();
            System.out.printf("%s->%s ",headTag,tag);
        }
    }

    public static void main(String[] args) {
        In in = new In("src/main/data/txtdata/11EWG.txt");
        WeightedGraph g = new WeightedGraph(in,1);
        MST_Kruskal mstkruskal = new MST_Kruskal(g);
        mstkruskal.check();
    }
}
class EdgeComparator implements Comparator<Node> {
    // overriding compare()method of Comparator
    public int compare(Node n1,Node n2){
        if(n1.getWeight()<n2.getWeight()) return -1;
        else if (n1.getWeight()>n2.getWeight()) return 1;
        return 0;
    }

}

class Test{
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(4);
        pq.add(2);
        pq.add(3);
        System.out.println(pq);
    }
}