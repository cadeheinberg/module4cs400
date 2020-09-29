// --== CS400 File Header Information ==--
// Name: Elan Graupe
// Email: graupe@wisc.edu
// Team: BG
// Role: Backend Developer
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * A class to create and manipulate student objects
 * 
 * @author Elan Graupe
 *
 */
public class Student {

  private static final int lengthOfID = 9;

  private String customerName;
  private String customerId; // The id that the student will be looked up with
  private double balance;
  private boolean isResident; // determines if student is eligible for resident discount

  private static double studentDiscount = .8;

  private static HashTableMap<String, Student> studentTable; // Hash table to store all customers

  /**
   * Constructor to create a new student object.
   * 
   * Note: Student object is not automatically added to hash table. Must be added if you want the
   * student to show up in searches
   * 
   * @param name       - The name of the student as a String
   * @param bal        - The starting balance in the student's account as a double
   * @param isResident - boolean value for whether or not the student is eligible for the student
   *                   discount
   * @param id         - The students's Student ID
   */
  public Student(String name, double bal, boolean isResident, String id) {
    setName(name);
    setBalance(bal);
    setResident(isResident);
    setCustomerId(id);
  }

  /**
   * Saves all student information to a file
   */
  public void saveStudent() {
    try {
      MakeStudents.writeStudentToFile(this);
    }
    catch(Exception e) {
      System.out.println("Error writing to file");
    }
  }

  /**
   * Method to add a charge to the student's account
   * 
   * Note: The student discount automatically be applied if the student is eligible
   * 
   * @param amount - The sub-total to be charged to the student's account
   * @return the total amount of money deducted
   * @throws InsufficientFundsException if there is not enough money in the student's account to
   *                                    make the purchase
   */
  public double deductMoney(double amount) {

    if (isResident) { // Apply the resident discount if the customer is a resident
      amount *= studentDiscount;
    }

    if (amount > balance) { // Throw an InsufficentFundsException if the purcahse cannot be made
      throw new InsufficientFundsException("Student does not have sufficent funds in account");
    }
    this.balance -= (double)amount;

    return amount;
  }

  /**
   * Method to add money to the student's account
   * 
   * @param amount - The amount of money to add to the account
   */
  public void addMoney(double amount) {
    this.balance += (double)amount;
  }

  /**
   * Search for a student in the hash table with their student ID
   * 
   * @param id - The student id of the student
   * @return The student object associated with the student's account
   */
  public static Student getStudent(String id) {
    Student stud;
    try {
      stud = studentTable.get(id);
    } catch (java.util.NoSuchElementException e) { // Print an messange and return null if
                                                   // studentTable.get() threw an exception
      System.out.println("Can't find student with that ID, please re-try: ");
      // student not in table
      return null;
    }
    return stud;
  }

  /**
   * Get the hash table of students
   * 
   * Used for testing and debugging
   * 
   * @return a hash table of student objects and their student ID keys
   */
  public static HashTableMap<String, Student> getStudentTable() {
    return studentTable;
  }

  /**
   * Set the hash table of students
   * 
   * Used for loading the students into the hash table initially
   * 
   * @param table - A hash table of students
   */
  public static void setStudentTable(HashTableMap<String, Student> table) {
    studentTable = table;
  }

  /**
   * Get the name of a student
   * 
   * @return the name of a student
   */
  public String getName() {
    return customerName;
  }

  /**
   * Set the name of a student
   * 
   * @param name - The name of the student
   */
  public void setName(String name) {
    this.customerName = name;
  }

  /**
   * Get the balance remaining in a student's account
   * 
   * @return The remaining balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Set the balance in the students account. Replaces the student's current balance
   * 
   * @param balance - The balance that will replace the student's existing balance
   */
  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Gets the resident status of a student
   * 
   * @return The resident status of a student
   */
  public boolean isResident() {
    return isResident;
  }

  /**
   * Sets the resident status of a student
   * 
   * @param resident - The resident status of the student
   */
  public void setResident(boolean resident) {
    this.isResident = resident;
  }

  /**
   * Get the Student ID of a student
   * 
   * @return The student ID of the student
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * Sets the customer ID of a student
   * 
   * Note: This does not change the key of the student in the hash table
   * 
   * @param customerId - The student ID of the student
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  /**
   * The length that all student ID's should be
   * 
   * @return - the length
   */
  public static int getLengthofid() {
    return lengthOfID;
  }

  /**
   * Get the resident discount (a double between zero and one)
   * 
   * @return - The resident discount
   */
  public static double getResidentDiscount() {
    return 1 - studentDiscount;
  }

  /**
   * Set the resident discount (a double between zero and one)
   * 
   * @param newStudentDiscount - The new resident discount
   */
  public static void setResidentDiscount(double newStudentDiscount) {
    studentDiscount = 1 - newStudentDiscount;
  }

  /**
   * Puts all student data into a string
   * 
   * @return a string
   */
  @Override
  public String toString() {
    return "" + customerId + " " + customerName + " " + balance + " " + isResident;
  }

}
