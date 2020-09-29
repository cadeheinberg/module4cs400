// --== CS400 File Header Information ==--
// Name: Elias Verdun
// Email: esverdun@wisc.edu
// Team: BG
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class creates a .txt file with 100 students stored with randomly selected ids, names,
 * account balances, and resident statuses
 * 
 * @author eliverdun
 *
 */
public class MakeStudents {
  private static String[] studentNames = new String[] {"Rodger", "Mary", "Andrew", "Sally", "Mike",
      "Erica", "Jackson", "Sarah", "Cole", "Jane", "Sophie", "Lauren", "John"}; // names to be
  // randomly selected
  private static long[] studentID = {908166000, 908133000, 907888000}; // started id values for
  // simplicity
  private double balance; // random student account balance
  private boolean[] resident = {true, false}; // random selection of if student has resident hall
  // status

  /**
   * No argument constructor
   */
  public MakeStudents() {

  }

  /**
   * This method goes through for loop to create different students to be later added to hash table
   * 
   * @throws IOException if file not found
   */
  public static void writeToFile() throws IOException {
    File studentData = new File("studentData.txt"); // creates new file
    studentData.createNewFile();
    FileWriter writer = new FileWriter(studentData); // ability to write to file
    Random rand = new Random(); // allows for random generator of different inputs
    for (int i = 0; i < 100; i++) { // 100 different students in file
      String name = studentNames[rand.nextInt(studentNames.length - 1)];
      long id = studentID[rand.nextInt(2)] + Math.abs(rand.nextInt(10000)); // 10-digit id
      String studId = Long.toString(id);
      boolean resident = rand.nextBoolean();
      double balance = rand.nextDouble() * 15; // arbitrary value to create greater variaton in
      // balances
      double dec = Math.round(balance * 100.00) / 100.00; // removes addition decimal places
      writer.write(studId + " " + name + " " + " " + dec + " " + resident + "\n"); // writes all
      // information to
      // line and then
      // creates new line
    }
    writer.close();
  }


  /**
   * Saves a student's new data to the file after changes have been made
   * 
   * @author Elan Graupe
   * 
   * @param student - The student to save
   * @throws IOException
   */
  public static void writeStudentToFile(Student student) throws IOException {
    File studentData = new File("studentData.txt"); // creates new file
    studentData.createNewFile();
    Scanner in = new Scanner(studentData);

    String fileText = "";
    while (in.hasNextLine()) { // Look through every line of the file
      String nextLine = in.nextLine();
      if (nextLine.contains(student.getCustomerId())) { // If a line contains the student id,
                                                        // replace that line with the new student
                                                        // information
        nextLine = student.toString();
      }
      nextLine += "\n";
      fileText += nextLine;
    }

    FileWriter writer = new FileWriter(studentData);
    writer.write(fileText); // Save the new string to the file
    writer.close();
  }

  /**
   * Calls writeToFile() method to create .txt file of students
   * 
   * @param args
   * @throws IOException if file error
   */
  public static void doOnStartUp() throws IOException {
    writeToFile();
  }

}
