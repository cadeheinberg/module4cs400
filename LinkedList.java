// --== CS400 File Header Information ==--
// Name: Cade Heinberg
// Email: cheinberg@wisc.edu
// Team: BG
// TA:  bcochran2@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: 5 classes included

/* 
 * Functions as a list of generic types for array in the HashTableMap
 * @author Cade
 */
public class LinkedList<KeyType, ValueType> {

  private LinkedNode<KeyType, ValueType> head;
  private int size;

  /* 
   * Creates a new instance of a LinkedList with the head LinkedNode as a parameter
   * 
   */
  public LinkedList(LinkedNode<KeyType, ValueType> head) {
    this.head = head;
    size = 1;
  }
  
  /* 
   * Gets the head LinkedNode of the LinkedList
   * 
   * returns LinkedNode, the head
   */
  public LinkedNode<KeyType, ValueType> getHead() {
    return head;
  }
  
  /*
   * Places LinkedNode at the front of the LinkedList Calls remove method to try and remove match if
   * existing
   * 
   * returns boolean true if the key is able to be added
   */
  public boolean addNode(LinkedNode<KeyType, ValueType> node) {
    if (this.isEmpty()) {
      head = node;
      size = 1;
      return true;
    }
    //key is already in list, return false. Can't add.
    if(this.containsNode(node.getOriginalKey())) {
      return false;
    }
    node.setNextNode(head);
    head = node;
    size++;
    return true;
  }

  /*
   * Loops through LinkedList to find LinkedNode with watching key Removes the matching node and
   * decrements the size of the LinkedList
   * 
   * return ValueType value of the LinkedNode
   */
  public ValueType removeNode(KeyType originalKey) {
    LinkedNode<KeyType, ValueType> current = head;
    LinkedNode<KeyType, ValueType> previous = null;
    if (current == null) {
      return null;
    }
    while (current != null) {
      if (current.getOriginalKey().equals(originalKey)) {
        if (size == 1) {
          head = null;
          size--;
          return current.getValue();
        }
        if (current.getNextNode() != null) {
          if (previous != null) {
            previous.setNextNode(current.getNextNode());
          } else {
            head = current.getNextNode();
          }
          current.setNextNode(null);
          size--;
          return current.getValue();
        } else {
          if (previous == null) {
            head = null;
          }
          previous.setNextNode(null);
          size--;
          return current.getValue();
        }
      }
      previous = current;
      current = current.getNextNode();
    }
    return null;
  }
  
  /* 
   * Gets the value of the LinkedNode with the key value equal 
   * to the key being searched for
   * 
   * returns ValueType value of the matching key pair
   */
  public ValueType getNode(KeyType originalKey) throws java.util.NoSuchElementException {
    LinkedNode<KeyType, ValueType> current = head;
    while (true) {
      if (current == null) {
        throw new java.util.NoSuchElementException("Element not in table");
      }
      if (originalKey.equals(current.getOriginalKey())) {
        return current.getValue();
      } else {
        current = current.getNextNode();
      }
    }
  }

  /* 
   * Does the LinkedList contain a node with a matching KeyType
   * 
   * returns boolean true if yes
   */
  public boolean containsNode(KeyType key) {
    LinkedNode<KeyType, ValueType> current = head;
    while (true) {
      if (current == null) {
        return false;
      }
      if (key.equals(current.getOriginalKey())) {
        return true;
      } else {
        current = current.getNextNode();
      }
    }
  }
  
  /*
   * Is the size of the list 0
   * 
   * return boolean true if yes, being that it is empty
   */
  private boolean isEmpty() {
    return size == 0;
  }


}
