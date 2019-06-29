package MyjavaTest;

public class Node {
  double weight;// for edges
  String tag;
  Node next;
  int index;

  public Node() {
    index = -1;
    next = null;
    tag = "#";
    // weight ->null
  }

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

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
}