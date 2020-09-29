// --== CS400 File Header Information ==--
// Name: Cade Heinberg
// Email: cheinberg@wisc.edu
// Team: BG
// TA:  bcochran2@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: 5 classes included

public interface MapADT<KeyType, ValueType> {
  
  public boolean put(KeyType key, ValueType value);

  public ValueType get(KeyType key) throws java.util.NoSuchElementException;

  public int size();

  public boolean containsKey(KeyType key);

  public ValueType remove(KeyType key);

  public void clear();
  
}
