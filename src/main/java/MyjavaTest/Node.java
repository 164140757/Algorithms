package MyjavaTest;

class Node {
  private double weight;// for edges
  private String tag;
  private Node next;
  private int index;
  private int head;

  Node() {
    head = 0;
    index = -1;
    next = null;
    tag = "#";
    // weight ->null
  }
  int getHead(){return head;}
  void setHead(int head){this.head = head;}
  int getIndex() {
    return index;
  }

  void setIndex(int index) {
    this.index = index;
  }

  void setNext(Node next) {
    this.next = next;
  }

  Node getNext() {
    return next;
  }

  String getTag() {
    return tag;
  }

  void setTag(String tag) {
    this.tag = tag;
  }

  void AutoAddtagForEnd(int indexOfEnd,int model) {
    switch (model) {
      case 1:
        String init = "A";
        int ascii = (int) init.charAt(0) + indexOfEnd;
        init = Character.toString((char) ascii);
        this.setTag(init);
        break;
      case 2:
        int temp = indexOfEnd;
        String tString = Integer.toString(temp);
        this.setTag(tString);
      default:
        break;
    }
  }

  double getWeight() {
    return weight;
  }

  void setWeight(double weight) {
    this.weight = weight;
  }
  String indextoTag(int index){
    String init = "A";
    int ret = (int)init.charAt(0) + index -1;
    return  Integer.toString(ret);
  }

}