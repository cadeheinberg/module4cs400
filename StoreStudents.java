// --== CS400 File Header Information ==--
// Name: Elias Verdun
// Email: esverdun@wisc.edu
// Team: BG
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Adds students to hash table map by key: student id and value: student object
 * 
 * @author eliverdun
 *
 */
public class StoreStudents {

  /**
   * Adds students to hash table map by for loop
   * 
   * @param args
   * @throws FileNotFoundException
   */
  public static void setUpClass() throws FileNotFoundException {
    File file = new File("studentData.txt");
    Scanner scnr = new Scanner(file); // ability to read from file
    Student.setStudentTable(new HashTableMap<String, Student>(200)); // new HashTableMap object to insert students

    for (int i = 0; i < 100; i++) { // scanning each line for student info to add to table
      String studId = scnr.next();
      String name = scnr.next();
      double bal = scnr.nextDouble();
      boolean resident = scnr.nextBoolean();

      Student student = new Student(name, bal, resident, studId); // student object containing name,
      Student.getStudentTable().put(studId, student);

    }
    scnr.close();

  }

}
