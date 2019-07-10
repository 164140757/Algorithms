package MyjavaTest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * I plan to create a Graph superclass ,but failed
 * because of the data format we have in data/.txt.
 * To be more specific,
 * the weighted graph has to add another parameter to
 * set a weight for every edge
 * while the unweighted doesn't
 *
 * so I decide to create different classes, although it
 * seems a little improper.
 */
public class WeightedGraph {//double ->WeightType of Edges
  private int V;//cannot be modified
  private int E;//number of edges
  private List<Node> headNodeList;
  private int model;
  //inner class & Generic Class

  //constructor of empty adjacency list
  public WeightedGraph(int vertex) {
    E = 0;
    if(vertex<0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
    this.V = vertex;
    headNodeList = new ArrayList<>(V);//headlist with space & ArrayList to instantiate List
    //List<Node> headNodeList - > cannot visit class WeightedGraph's field instead -> a new list
    for (int i=0;i<V;i++){//nodes -> list
      Node node = new Node();
      node.setIndex(i);
      node.setNext(null);//only null in java
      headNodeList.add(node);
    }
  }

  WeightedGraph(In in,int model){//model: 1 for characters from A-Z ; 2 for Integer from 1 to n
    this.model = model;
    try{
      this.V = in.readInt();
      if(V<0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
      if(model!=1 & model!=2) throw new IllegalArgumentException("Only model number 1 or 2 can be selected!");
      if (model == 1 && V>23) throw new IllegalArgumentException("model 1 supports only number of vertexs within 23,please try another tag model");
      this.E = in.readInt();
      if(E<0) throw new IllegalArgumentException("Number of edges must be nonnegative");
      headNodeList = new ArrayList<>(V);//()gives the initalCapacity
      for (int i=0;i<V;i++){//nodes -> list
        Node node = new Node();
        node.setIndex(i);
        node.setNext(null);//only null in java
        headNodeList.add(node);
      }
      //add tags
      headNodeListAddTags();
      //add Edges
      for (int i =0;i<E;i++){
        int v = in.readInt();
        int u = in.readInt();
        double w = in.readDouble();
        validateVertex(v);
        validateVertex(u);
        addEdge(v, u, w);
      }
    }
    catch(NoSuchElementException e){ //if the number of edges is not equal to the data 
      throw new IllegalArgumentException("invalid input format in WeightedGraph constructor");
    }
  }

  //methods
  //{@code 0<= v <= V}
  List<Node> HeadNodeList(){return headNodeList;}
  int V() {
    return V;
  }

  int E() {
    return E;
  }
  private void validateVertex(int v){
    if(v<0 || v>V) throw new IllegalArgumentException("vertex"+V+"is not between 0 and"+(V-1));
    //() make sure parameter given to IllegalArgumentException is a string
  }
  private void headNodeListAddTags(){
    switch (model) {
      case 1:
        String init = "A";
        for (int i = 0; i < V; i++) {
          headNodeList.get(i).setTag(init);
          int ascii = (int) init.charAt(0) + 1;
          init = Character.toString((char) ascii);
      }
        break;
      case 2:
        for(int i=0;i<V;i++){
          String tString = Integer.toString(i);
          headNodeList.get(i).setTag(tString);
        }
      default:
        break;
    }
  }
   private void addEdge(int v,int u,double VALUE){
    validateVertex(v);
    validateVertex(u);
    Node node = new Node();
    node = headNodeList.get(v);//headNode[v] & get(i) returns the element at the position i
    while(node.getNext()!=null){//headNode(n-1) already exits
      node = node.getNext();
      int index = node.getIndex();
      if(index == u ) {
        node.setWeight(VALUE);
        return;
      }
    }
    //headNode(n) & for new node to insert in the end of list
    if(node.getIndex()==u){
      node.setWeight(VALUE);
      return;
    }
    //headNode[u][v] does not exit
    Node Nnode = new Node();
    Nnode.setIndex(u);
    Nnode.setWeight(VALUE);
    Nnode.AutoAddtagForEnd(u,model);
    node.setNext(Nnode);
  }
  void reverse(){
    List<Node> tempHeadNodeList = new ArrayList<>();
    // template to save the result list
    // init
    for (int v = 0;v<V;v++){
      Node node = new Node();
      node.setNext(null);
      node.setIndex(v);
      tempHeadNodeList.add(node);
    }
    // create new Graph(reverse)
    for(int i=0;i<V;i++){
      String tag = headNodeList.get(i).getTag();
      // tag to set is from the headNodeList[i]'s tag
      Node node = headNodeList.get(i).getNext();
      while(node !=null){
        int index = node.getIndex();
        double value = node.getWeight();

        Node nodeTemp = new Node();
        nodeTemp.setTag(tag);
        nodeTemp.setNext(null);
        nodeTemp.setIndex(i);
        nodeTemp.setWeight(value);
        // get to the end of the node list
        Node headNode = tempHeadNodeList.get(index);
        Node preNode = new Node();
        while(headNode!= null){
          preNode = headNode;
          headNode = headNode.getNext();
        }
        preNode.setNext(nodeTemp);
        node = node.getNext();
      }
    }
    headNodeList = tempHeadNodeList;
    headNodeListAddTags();
  }
  private void traverseHeadList(){
    Node node = new Node();
    System.out.printf("List:[ ");
    for (int i =0;i<V;i++){
      System.out.printf("%s ",headNodeList.get(i).getTag());
    }
    System.out.printf("]\n");
    //iterator
    Iterator<Node> iterator = headNodeList.iterator();
    while(iterator.hasNext()){
      node = iterator.next();//iterate over headNodeList
      //iterate over headNodeList[i]
      System.out.printf("%s :",node.getTag());
      node = node.getNext();//first element of the vertex
      while(node!=null){
        System.out.printf("->%s %s   ",node.getTag(),node.getWeight());
        node = node.getNext();
      }
      System.out.println();
    }

  }

  public static void main(String...args) {
    // WeightedGraph g = new WeightedGraph(3);
    // g.addEdge(2, 1, 2.21);
    // g.addEdge(0, 2, 3.21);
    // g.traverseHeadList();
    In in = new In("src/main/data/11EWG.txt");
    WeightedGraph g = new WeightedGraph(in,1);
    g.traverseHeadList();
    g.reverse();
    System.out.println("After reversing:");
    g.traverseHeadList();
  }
} 