// --== CS400 File Header Information ==--
// Name: Cameron Berhow
// Email: cberhow@wisc.edu
// Team: BG
// Role: Back End Developer
// TA: bcochran2@wisc.edu
// Lecturer: Gary Dahl
public class StudentC {
	
	private static HashTableMap<String, StudentC> ht;
	private static double discountVal=.8;
	private static final int LENGTH_OF_ID=9;
	
	private String ID;
	private float balance;
	private boolean resident;
	private String name;
	
	/**
	 * Creates student object and adds it to the hash table
	 * @param studentID
	 * @param balance
	 * @param resident
	 * @param name
	 */
	public StudentC(String studentID, float balance, boolean resident, String name) {
		this.ID=studentID;
		this.balance=balance;
		this.resident=resident;
		this.name=name;
		ht.put(ID, this);
	}
	/**
	 * Gets student with certain ID, or null if it doesn't exist.  If it doesn't exist,
	 * a null is returned and a message is printed
	 * @param studentID
	 * @return StudentC
	 */
	public static StudentC getStudent(String studentID) {
		try{
			return ht.get(studentID);
		}catch(Exception e) {
			System.out.println("ERROR: Student with ID "+studentID+" not found.");
			return null;
		}
	}
	
	/**
	 * Adds money to the student account and returns the new balance
	 * @param amount
	 * @return float
	 */
	public float addMoney(float amount) {
		return balance+=amount;
	}
	
	/**
	 * Removes money from the student account, or throws an error if there are
	 * insufficient funds
	 * Returns new balance
	 * @param amount
	 * @return float
	 * @throws Exception
	 */
	public float deductMoney(float amount) throws Exception{
		amount=applyResidentDiscount(amount);
		if(amount>balance)
			throw new Exception("Insufficient funds!");
		return balance-=amount;
	}
	
	/**
	 * Applies the resident discount to a given amount, then returns the new amount
	 * @param amount
	 * @return float
	 */
	private float applyResidentDiscount(float amount) {
		if(resident)
			return (float) (amount*(1-discountVal));
		else
			return amount;
	}
	
	/**
	 * Gets current balance
	 * @return float
	 */
	public float getBalance() {
		return balance;
	}
	
	/**
	 * Sets balance
	 * @param balance
	 */
	public void setBalance(float balance) {
		this.balance=balance;
	}
	
	/**
	 * Returns if resident discount should be applied
	 * @return boolean
	 */
	public boolean getResidency() {
		return resident;
	}
	
	/**
	 * Sets if resident discount should be applied
	 * @param resident
	 */
	public void setResidency(boolean resident) {
		this.resident=resident;
	}
	
	/**
	 * Returns the hash table
	 * @return HashTableMap<String, StudentC>
	 */
	public static HashTableMap<String, StudentC> getTable() {
		return ht;
	}
	
	/**
	 * Sets the hash table from a template
	 * @param table
	 */
	public static void setTable(HashTableMap<String, StudentC> table) {
		ht=table;
	}
	
	/**
	   * Saves all student information to a file, or throws an error
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
	 * Gets the student's name
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the student's name
	 * @param name
	 */
	public void setName(String name) {
		this.name=name;
	}
	
	/**
	 * Gets the student's ID
	 * @return String
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * Sets the student's ID
	 * @param ID
	 */
	public void setID(String ID) {
		this.ID=ID;
	}
	
	/**
	 * Gets the length of the ID
	 * @return int
	 */
	public static int getIDLength() {
		return LENGTH_OF_ID;
	}
}
