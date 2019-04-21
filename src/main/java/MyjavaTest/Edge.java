package MyjavaTest;
public class Edge implements Comparable<Edge> {
  private final int v;
  private final int w;
  private final double weight;

  public Edge(int v, int w, double weight){
    if(v<0) throw new IllegalArgumentException("vertex must be a nonegative integer");
    if(w<0) throw new IllegalArgumentException("vertex must be a nonegative integer");
    if(Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public static void main(String[] args) {
    Edge a = new Edge(2, 3, 2);
    System.out.println(a.weight);
  }
  
  public int either() {
    return v;
  }
  
  public int other(int vertex) {
    if (vertex == v)
      return w;
    else if (vertex == w)
      return v;
    else
      throw new IllegalArgumentException("Illegal endpoint");
  }
  
  public int compareTo(Edge that) {
    return Double.compare(this.weight, that.weight);
  }
}