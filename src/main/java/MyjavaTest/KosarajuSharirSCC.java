package MyjavaTest;

import java.util.Stack;

public class KosarajuSharirSCC {
    private boolean[] marked;// marked[v] = v has been visited
    private Stack<Integer> finished ; //to record the decreasing order of u.f
    private int count; //number of strongly_connected components

    private KosarajuSharirSCC(WeightedGraph G){
        finished = new  Stack<>();
        marked = new boolean[G.V()];// default to false
        for(int v =0; v< G.V();v++){
            if(!marked[v])
                dfsFir(G,v);
        }
        reverseGraph(G);
        // init marked[]
        marked = new boolean[G.V()];
        while(!finished.empty()){
            int testVertex = finished.pop();
            if(!marked[testVertex]){
                System.out.println("\nSCC:");
                dfsSnd(G,testVertex);
                count++;
            }
        }
    }

    private void reverseGraph(WeightedGraph G){
        G.reverse();
    }

    private void dfsFir(WeightedGraph G,int s){// put the finished nodes into the stack (the first time
        marked[s] = true;
        Node node = G.HeadNodeList().get(s).getNext();
        while(node !=null){
            int index = node.getIndex();
            if(!marked[index])
                dfsFir(G,index);
            node = node.getNext();
        }
        //finished

        finished.push(s);
    }
    private void dfsSnd(WeightedGraph G,int v){// after reversing the graph to output the SCC (the second time)
        marked[v] = true;
        Node node = G.HeadNodeList().get(v);
        System.out.printf("%s ",node.getTag());
        node = G.HeadNodeList().get(v).getNext();
        while(node !=null){
            int index = node.getIndex();
            if(!marked[index])
                dfsSnd(G,index);
            node = node.getNext();
        }

    }

    public static void main(String[] args) {
        In in = new In("src/main/data/11EWG.txt");
        WeightedGraph G = new WeightedGraph(in,1,false);
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);
        System.out.printf("\nnumber of SCC:%d",scc.count);
    }
}
