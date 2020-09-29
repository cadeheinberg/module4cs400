// --== CS400 File Header Information ==--
// Name: Surya Santhan Thenarasu
// Email: thenarasu@wisc.edu
// Team: BG
// Role: Test Engineer
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
// test file
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentTestCode {
  
  public static boolean createHashTableTest() {
    try {
      Student.setStudentTable(new HashTableMap<String, Student>());
      HashTableMap table = Student.getStudentTable();
      
      table.put("15", new Student("student 1", 10.7, false, "15"));
      table.put("16", new Student("student 2", 11.13, false, "16"));
      table.put("17", new Student("student 3", 7.04, false, "17"));
      table.put("40", new Student("student 4", 3.30, false, "40"));
      table.put("84", new Student("student 5", 11.8, false, "84"));
      
      table.put("21", new Student("student 5", 8.49, false, "21"));
      table.put("32", new Student("student 6", 7.53, false, "32"));
      table.put("56", new Student("student 7", 2, false, "56"));
      table.put("71", new Student("student 8", 8.93, false, "71"));
      table.put("98", new Student("student 9", 6.5, false, "98"));
      
      table.put("12", new Student("student 10", 0, false, "12"));
      
      
      
    }catch(Exception e) {
      e.printStackTrace();
      return false;
      
    }
    return true;
  }

  public static boolean testaddmoney() {

    Student s = null;
    s = Student.getStudent("15");
    s.addMoney(2);
    if (Double.compare(s.getBalance(), 12.7) != 0) {
      System.out.print("Test failed 1");

      return false;
    }

    s = Student.getStudent("16");
    s.addMoney(2);
    if (Double.compare(s.getBalance(), 13.13) != 0) {
      System.out.print("Test failed 2");

      return false;
    }

    s = Student.getStudent("17");
    s.addMoney(2);
    if (Double.compare(s.getBalance(), 9.04) != 0) {
      System.out.print("Test failed 3 " + s.getBalance());
      return false;
    }
    s = Student.getStudent("40");
    s.addMoney(2);
    if (Double.compare(s.getBalance(), 5.30) != 0) {
      System.out.print("Test failed 4");
      return false;
    }
    s = Student.getStudent("84");
    s.addMoney(2);
    if (Double.compare(s.getBalance(), 13.8) != 0) {
      System.out.print("Test failed 5");
      return false;
    }

    //System.out.print("Test passed");
    return true;
  }



  public static boolean testdeductmoney() {

    Student s = null;
    s = Student.getStudent("21");
    s.deductMoney(2);
    if (s.getBalance() != 6.49) {
      System.out.print("Test failed 1");
      return false;
    }

    s = Student.getStudent("32");
    s.deductMoney(2);
    if (s.getBalance() != 5.53) {
      System.out.print("Test failed 2");

      return false;
    }
    s = Student.getStudent("56");
    s.deductMoney(2);
    if (s.getBalance() != 0) {
      System.out.print("Test failed 3");
      return false;
    }
    s = Student.getStudent("71");
    s.deductMoney(2);
    if (s.getBalance() != 6.93)

    {
      System.out.print("Test failed 4");

      return false;
    }
    s = Student.getStudent("98");
    s.deductMoney(2);
    if (s.getBalance() != 4.5) {
      System.out.print("Test failed 5");
      return false;
    }

    //System.out.print("Test passed");
    return true;
  }



  public static boolean testgetStudent() {

    Student s = null;
    try {
      s = Student.getStudent("12");
    } catch (java.util.NoSuchElementException e) {
      System.out.println("Can't find student with that ID, please re-try: ");
      System.out.print("Test failed");// student not in table
      return false;
    }
    //System.out.print("Test passed");
    return true;


  }

  public static boolean testdoIdPrompt() {

    // You have to pass in a Scanner obj with the String of the id as input
    Scanner scnr = new Scanner(System.in);

    FrontEnd f = new FrontEnd();
    int idlength = Integer.parseInt(f.doIdPrompt(scnr));
    if (idlength != 9) {
      System.out.print("Test failed");
      return false;
    }
    //System.out.print("Test passed");
    return true;

  }



  public static void runTests() {
    System.out.println("   Create hash table test: " + createHashTableTest());
    System.out.println("   Add money test: " + testaddmoney());
    System.out.println("   Deduct money test: " + testdeductmoney());
    System.out.println("   Get student test: " + testgetStudent());
    System.out.println("   Get student with ID test: " + testdoIdPrompt());

  }
  
  public static void main(String[] args) {
    runTests();
  }

}
