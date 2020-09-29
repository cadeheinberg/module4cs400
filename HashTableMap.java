// --== CS400 File Header Information ==--
// Name: Cade Heinberg
// Email: cheinberg@wisc.edu
// Team: BG
// TA:  bcochran2@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: 5 classes included

/* 
 * Stores key pairs of generic data types using a LinkedList array
 * @author Cade
 */
@SuppressWarnings("unchecked")
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private LinkedList<KeyType, ValueType>[] list;
  private int size;
  private int capacity;
  /*
   * Creates a new instance of HashTableMap with capacity 10
   */
  public HashTableMap() {
    this.capacity = 10;
    this.size = 0;
    list = new LinkedList[10];
  }
  /*
   * Creates a new instance of HashTableMap with capacity parameter
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    list = new LinkedList[capacity];
  }
  
  /*
   * Puts a new key and value into the HashTableMap
   * Does not replace duplicate key
   * 
   * returns boolean false if key is already in the table
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    int placeAt = getHashIndex(key);
    if (list[placeAt] == null) {
      // create new list
      list[placeAt] = new LinkedList<KeyType, ValueType>(new LinkedNode<KeyType, ValueType>(key, value));
    }else {
      if(!list[placeAt].addNode(new LinkedNode<KeyType, ValueType>(key, value))) {
        return false;
      }
    }
    this.size++;
    if (timeToGrow()) {
      growTable();
    }
    return true;
  }

  /*
   * Gets a value from the HashTableMap using a key
   * 
   * returns ValueType of the value stored at the hashed key index
   */
  @Override
  public ValueType get(KeyType key) throws java.util.NoSuchElementException {
    int placeAt = getHashIndex(key);
    if (list[placeAt] == null) {
      throw new java.util.NoSuchElementException("Element not in table");
    } 
    return list[placeAt].getNode(key);
  }

  /*
   * Gets the size of the HashTableMap
   * 
   * returns int size, the amount of elements in the LinkedList array "list" that are not null
   */
  @Override
  public int size() {
    return size;
  }

  /*
   * Determines if a key is in the HashTableMap or not
   * 
   * returns boolean true if the key is in the table
   */
  @Override
  public boolean containsKey(KeyType key) {
    int placeAt = getHashIndex(key);
    if (list[placeAt] == null) {
      return false;
    } 
    return list[placeAt].containsNode(key);
  }

  /*
   * Removes a specific key pair from the HashTableMap
   * 
   * returns ValueType of the key pair that was removed, null if table doesn't contain key pair
   */
  @Override
  public ValueType remove(KeyType key) {
    int placeAt = getHashIndex(key);
    if (list[placeAt] == null) {
      return null;
    }
    ValueType removed = list[placeAt].removeNode(key);
    if(removed == null) {
      return null;
    }
    this.size--;
    return removed;
  }

  /*
   * Gets the index of the LinkedList array "list" where the key pair should correspond to
   * 
   * returns int, the index of the array
   */
  private int getHashIndex(KeyType key) {
    return Math.abs(key.hashCode()) % capacity;
  }
  
  /*
   * Check if the size of the HashTableMap is almost at its capacity
   * 
   * returns boolean true if the size if 80% or more of the capacity
   */
  private boolean timeToGrow() {
    if((((size + 2) / capacity) > .8)) {
      return true;
    }
    return false;
  }
  
  /*
   * Doubles the capacity of the HashTableMap
   * Creates a new LinkedList array twice the capacity as the current one
   * Copies the references to each non null element from the current array to the larger one
   * Does this by rehashing the key pairs with the larger capacity
   * 
   */
  private void growTable() {
    int oldCapacity = this.capacity;
    LinkedList<KeyType, ValueType>[] biggerList = new LinkedList[this.capacity = this.capacity * 2];
    for(int i = 0; i < oldCapacity; i++) {
      if(list[i] == null) {
        continue;
      }
      if(list[i].getHead() == null) {
        continue;
      }
     biggerList[this.getHashIndex(list[i].getHead().getOriginalKey())] = list[i];
    }
    this.list = biggerList;
  }
  
  /* 
   * Clears the HashTableMap of all elements
   */
  @Override
  public void clear() {
    list = new LinkedList[capacity];
    this.size = 0;
  }
  
  /* 
   * Gets the capacity of the HashTableMap
   * 
   * returns int, the capacity
   */
  public int getCapacity(){
    return capacity;
  }
  
  /* 
   * Gets the size of the HashTableMap
   * 
   * returns int, the size
   */
  public int getSize(){
    return size;
  }
    
  
}
