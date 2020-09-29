// --== CS400 File Header Information ==--
// Name: Cade Heinberg
// Email: cheinberg@wisc.edu
// Team: BG
// TA:  bcochran2@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: 5 classes included

/* 
 * Functions as each individual node for the a LinkedList
 * @author Cade
 */
public class LinkedNode<KeyType, ValueType> {
  
  private ValueType value;
  private KeyType originalKey;
  private LinkedNode<KeyType, ValueType> nextNode;
  
  /* 
   * Creates a LinkedNode to store a key pair
   * 
   */
  public LinkedNode(KeyType originalKey, ValueType value) {
    this.value = value;
    this.originalKey = originalKey;
  }
  
  /* 
   * Sets the nextNode of the LinkedNode for linear movement
   * 
   */
  public void setNextNode(LinkedNode<KeyType, ValueType> nextNode) {
    this.nextNode = nextNode;
  }
  
  /* 
   * Gets the nextNode of the LinkedNode
   * 
   * returns LinkedNode, the nextNode of the LinkedNode instance
   */
  public LinkedNode<KeyType, ValueType> getNextNode() {
    return nextNode;
  }
  
  /* 
   * Gets the value that is stored in the LinkedNode
   * 
   * returns ValueType value of the LinkedNode key pair
   */
  public ValueType getValue() {
    return value;
  }

  /* 
   * Gets the originalKey that is stored in the LinkedNode
   * 
   * returns KeyType key of the LinkedNode key pair
   */
  public KeyType getOriginalKey() {
    return originalKey;
  }

}
