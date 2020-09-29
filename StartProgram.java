// --== CS400 File Header Information ==--
// Name: Cade Heinberg
// Email: cheinberg@wisc.edu
// Team: BG
// Role: Front End Developer
// TA:  bcochran2@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: I made FrontEnd and StartProgram

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Run this class to start the program
 * Calls different methods to guide user
 * 
 * @author Cade Heinberg
 *
 */
public class StartProgram {

  /**
   * Makes sure program has resources it needs to function
   * 
   * @return false if not able tofunction
   */
  public static boolean onStart() {
    if (!loadStudents()) {
      return false;
    }
    if (!printOutExamples()) {
      return false;
    }
    return true;
  }

  /**
   * Will call method to prompt if user wants to generate new students
   * Checks if students can be put into table
   * 
   */
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    FrontEnd.promptForNewStudentTextFile(scnr);
    if (!onStart()) {
      return;
    }
    getNewStudent(scnr);
  }

  /**
   * Get a new student from input using FrontEnd class
   * 
   * @args scnr
   */
  public static void getNewStudent(Scanner scnr) {
    String id = null;
    Student stud = null;
    FrontEnd.tellIdPrompt();
    do {
      id = null;
      while (id == null) {
        id = FrontEnd.doIdPrompt(scnr);
        if(id != null && id.equals("quit")) {
          return;
        }
      }
    } while ((stud = Student.getStudent(id)) == null);
    FrontEnd.doOptionsPrompt(scnr, stud);
  }

  /**
   * Attempts to load students into HashMap
   * Gives a report of amount of students
   * 
   * @return false if students can be loaded
   */
  public static boolean loadStudents() {
    System.out.println(" [Loading Students From File]");
    try {
      StoreStudents.setUpClass();
    } catch (java.io.FileNotFoundException e) {
      System.out.println("Student data file error");
      return false;
    }
    System.out.println("   Student data file loaded");
    System.out.println("   " + Student.getStudentTable().getSize() + " students in table");
    System.out.println();
    return true;
  }

  /**
   * Prints out 3 example students that can be used
   * to perform functions on
   * 
   * @return false if students cant be found to use as examples
   */
  public static boolean printOutExamples() {
    System.out.println(" [List of Random IDs]");
    try {
      printRandomStudentFromTextFile();
      printRandomStudentFromTextFile();
      printRandomStudentFromTextFile();
      System.out.println();
    } catch (java.io.IOException e) {
      System.out.println("One or more of the examples not found in table");
      return false;
    }
    return true;
  }
  
  /**
   * Picks a random line from the studentData.txt file and prints it out
   * 
   * @throws IOException if the file cannot be found
   */
  public static void printRandomStudentFromTextFile() throws IOException {
    File studentData = new File("studentData.txt"); 
    Scanner in = new Scanner(studentData);
    java.util.Random rand = new java.util.Random();
    int stop = rand.nextInt(100);
    for (int i = 0; i < 100; i++) { // scanning each line for student info to add to table
      String studId = in.next();
      String name = in.next();
      double bal = in.nextDouble();
      boolean resident = in.nextBoolean();
      if (i == stop) {
        System.out.println("   " + studId + " - " + name + ", Resident: " + resident + ", $"
          + bal);
        break;
      }
    }
    in.close();
    return;

  }



}
