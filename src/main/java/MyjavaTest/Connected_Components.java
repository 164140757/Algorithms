package MyjavaTest;

import java.util.LinkedList;
import java.util.Queue;

/**
 * DFS to search for CC
 */
public class Connected_Components {
    private boolean[] marked; //marked for been visited
    private int[] id; // connected component has the same id
    private int[] size;// sized[id] = numbers of vertices in given component
    private int count; //numbers of connected components__begin with 0

    public Connected_Components(UnweightedGraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for(int v=0;v<G.V();v++){
            if(!marked[v]){
                dfs(G,v);
                count ++;
            }
        }
    }

    public Connected_Components(WeightedGraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for(int v=0;v<G.V();v++){
            if(!marked[v]){
                dfs(G,v);
                count ++;
            }
        }
    }

    private void dfs(WeightedGraph G,int v){
        marked[v] = true;
        id[v] = count;
        size[count] ++;
        Node node;
        node = G.HeadNodeList().get(v).getNext();

        while(node !=null){
            int index = node.getIndex();
            if(!marked[index]){
                dfs(G,index);
            }
            node = node.getNext();
        }

    }

    private void dfs(UnweightedGraph G,int v){
        marked[v] = true;
        id[v] = count;
        size[count]++;
        Node node;
        node = G.HeadNodeList().get(v).getNext();

        while(node !=null){
            int index = node.getIndex();
            if(!marked[index]){
                dfs(G,index);
            }
            node = node.getNext();
        }

    }
    public int id(int v){
        return id[v];
    }
    public int size(int v){
        return size[id[v]];
    }
    public int count(){
        return count;
    }

    public static void main(String[] args) {
        In in = new In("src/main/data/mediumG.txt");
        UnweightedGraph G = new UnweightedGraph(in,2,false);
        Connected_Components cc = new Connected_Components(G);

        int m = cc.count();
        System.out.println(m+" components:");
        //Queue[Queue]
        Queue<Integer>[]components = (Queue<Integer>[])new Queue[m];
        for(int i= 0;i<m;i++){
            components[i] = new LinkedList<>();
        }
        for (int v=0;v<G.V();v++){
            components[cc.id(v)].add(v);
        }

        for(int i=0;i<m;i++){
            System.out.printf("%d:",i);
            for(int v:components[i]){
                System.out.printf(v +" ");
            }
            System.out.println();
        }



    }
}
