package MyjavaTest;

public class UF {
    private int[] parent; // parent[i] = parent of i
    private byte[] rank; // similar to the depth of the tree
    private int count; //number of components

    /**
     *
     * @param n : the number of sites
     * @throws IllegalArgumentException if ( @code n < 0)
     */
    UF(int n) {
        if(n < 0) throw new IllegalArgumentException();
        count = n; // number of sites
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0;i< n;i++) {
            parent[i] = 0;
            rank[i] = 0;
        }
    }

    /**
     *
     * @param p the integer representing one site
     * @return the component identifier {@code p}
     */
    int find(int p){
        validate (p);
        while(p != parent[p]){
            parent[p] = parent[parent[p]]; // steps with greater distance & get a
            p = parent[p];
        }
        return p;
    }

    private void validate(int p){
        int n = parent.length;
        if(p < 0 || p > n)
            throw new IllegalArgumentException("index" + p + "is not between 0 and " + (n-1));
    }
    void union(int p , int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;

        if(rank[rootP]< rank[rootQ]) parent[rootP] = rootQ;
        else if(rank[rootP]> rank[rootQ]) parent[rootQ] = rootP;
        else{
            parent[rootP] = rootQ;
            rank[rootQ] ++;
        }
        count -- ;
    }

    /**
     *
     * @param p site p
     * @param q site q
     *
     * @return connected or not
     */
    boolean connected(int p, int q){
        return find(p) == find(q);
    }
    private int count(){
        return count;
    }

    public static void main(String[] args) {
        In in = new In("src/main/data/txtdata/tinyUF.txt");
        int n = in.readInt();
        UF uf = new UF(n);
        for(int i=0;i<n;i++){
            int p = in.readInt();
            int q = in.readInt();
            if(!uf.connected(p,q))
                uf.union(p,q);
                System.out.println(p + " " + q + " is connected");
        }
        System.out.println(uf.count() + " components in total ");
    }
}
