package MyjavaTest;

import java.util.Stack;

public class Strongly_Connected_Components {
    private boolean[] marked;// marked[v] = v has been visited
    private Stack<Integer> finished; //to record the decreasing order of u.f

    public Strongly_Connected_Components(WeightedGraph G){
        marked = new boolean[G.V()];// default to false
        for(int v =0; v< G.V();v++){
            dfsFir(G,v);
        }
        reverseGraph(G);
        while(!finished.empty()){
            int testVertex = finished.pop();
            if(!marked[testVertex]){
                dfsSnd(G,testVertex);
            }
        }
    }
    private void reverseGraph(WeightedGraph G){
        G.reverse();
    }

    private void dfsFir(WeightedGraph G,int s){// put the finished nodes into the stack (the first time)
        Node node = G.HeadNodeList().get(s).getNext();
        while(node !=null){
            
        }
    }
    private void dfsSnd(WeightedGraph G,int s){// after reversing the graph to output the SCC (the second time)

    }

    public static void main(String[] args) {
        In in = new In("src/main/data/10EWG.txt");
        WeightedGraph G = new WeightedGraph(in,1);
        Strongly_Connected_Components ssc = new Strongly_Connected_Components(G);
    }
}
