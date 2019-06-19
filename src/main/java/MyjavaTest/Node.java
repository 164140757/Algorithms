package MyjavaTest;

public class Node {
  protected double weight;// for edges
  protected String tag;
  protected Node next;
  protected int index;

  public Node() {
    index = -1;
    next = null;
    tag = "#";
    // weight ->null
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public Node getNext() {
    return next;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public void AutoAddtagForEnd(int indexOfEnd,int model) {
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