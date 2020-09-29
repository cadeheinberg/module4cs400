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
 * Front end interface that the user interacts with
 * 
 * @author Cade Heinberg
 *
 */
public class FrontEnd {

  private static final String idPrompt = "Type 'quit' to exit or enter a " + Student.getLengthofid() + " digit student ID: ";
  private static final String idLengthError = "ID entered must be " + Student.getLengthofid() + " digits, Type 'quit' to exit or please re-try:";

  private static final String optionsPrompt = "Enter letter of option below: ";
  private static final String[] options = new String[] {"(c) Charge Account", "(b) Check Balance",
    "(d) Deposit Funds", "(x) Exit Student Account", "(r) Return to this menu at anytime"};
  private static final String optionsError = "Invalid option, try c, b, d, x or r:";

  private static final String chargePrompt = "Enter amount to charge: ";

  private static final String balancePrompt = "Student balance is: ";

  private static final String depositPrompt = "Enter amount to be deposisted: ";
  private static final String dollarError = "Not a valid amount, please re-try: ";

  private static final String exitPrompt = "Closing account...";
  
  /**
   * Prompts user if they would like to use the current student file or generate
   * a new one with random students
   * 
   * @param scnr
   */
  public static void promptForNewStudentTextFile(Scanner scnr) {
    System.out.println("Would you like to use the current students or generate new ones?");
    System.out.println("Enter letter of option: ");
    System.out.println("(g) Generate New");
    System.out.println("(c) Use Current");
    String input = "";
    do{
      input = getStringInput(scnr);
    }while(input == null || !(input.equals("g") || input.equals("c")));
    if(input.equals("g")) {
      try {
        MakeStudents.writeToFile();
      } catch (IOException e) {
        System.out.println("Error writing new students to file");
        return;
      }
    }else {
      try {
        File file = new File("studentData.txt");
        Scanner test = new Scanner(file); // ability to read from file
        test.close();
      }catch(java.io.FileNotFoundException e) {
        try {
          System.out.println("File not found, generating new");
          MakeStudents.writeToFile();
        } catch (IOException e2) {
          System.out.println("Error writing new students to file");
          return;
        }
        return;
      }
    }
  }
  
  /**
   * Prompts user for id and checks if it is valid length
   * 
   * @param scnr
   * @returns String of id. Null if not valid input
   */
  public static String doIdPrompt(Scanner scnr) {
    String id = null;
    id = getStringInput(scnr);
    if(id == null) {
      return null;
    }
    if(id.equals("quit")) {
      return "quit";
    }
    if (id.trim().length() != Student.getLengthofid()) {
      System.out.println(idLengthError);
      return null;
    }
    return id;
  }

  /**
   * Prompts user of account options to choose from
   * Can use "r" to get to this menu at any time
   * 
   * @param scnr, stud
   */
  public static void doOptionsPrompt(Scanner scnr, Student stud) {
    int chosen = 0;
    boolean returnToOptions = false;
    String input = null;
    tellOptionsPrompt(stud);
    do {
      input = getStringInput(scnr);
      if (checkReturn(input)) {
        returnToOptions = true;
        break;
      }
    } while ((chosen = directOptions(scnr, input, stud)) == 3);
    if (returnToOptions) {
      doOptionsPrompt(scnr, stud);
    }
    if (chosen == 2) {
      StartProgram.getNewStudent(scnr);
    }
  }

  /**
   * Directs options that user input in account menu
   * 
   * @param scnr, input, stud
   * @returns 1 if command is done on student, 2 if command is to exit, 3 is the input is invalid
   */
  public static int directOptions(Scanner scnr, String input, Student stud) {
    switch (input) {
      case "c":
        doCharge(scnr, stud);
        return 1;
      case "b":
        tellBalance(stud);
        doOptionsPrompt(scnr, stud);
        return 1;
      case "d":
        doDepositFunds(scnr, stud);
        return 1;
      case "x":
        stud.saveStudent();
        tellExitStudent();
        return 2;
      default:
        System.out.println(optionsError);
        return 3;
    }
  }

  /**
   * Prompts and charges the student account amount of money input
   * 
   * @param scnr, stud
   */
  private static void doCharge(Scanner scnr, Student stud) {
    boolean returnToOptions = false;
    double toCharge = -1;
    System.out.println(tellUserAccount(stud) + chargePrompt);
    do {
      String toChargeInput = getStringInput(scnr);
      if (checkReturn(toChargeInput)) {
        returnToOptions = true;
        break;
      }
      toCharge = getValidDollarAmount(toChargeInput);
    } while (toCharge < 0);
    if (returnToOptions) {
      doOptionsPrompt(scnr, stud);
      return;
    }
    double realCharge = 0;
    try {
      realCharge = stud.deductMoney(toCharge); // charge
    }catch(InsufficientFundsException e) {
      System.out.println(e.getMessage());
      tellBalance(stud);
      doOptionsPrompt(scnr, stud);
      return;
    }
    realCharge = getValidDollarAmount("" + realCharge);
    System.out.println(tellUserAccount(stud) + "Resident: " + stud.isResident());
    System.out.println(tellUserAccount(stud) + "Charged: $" + realCharge);
    tellBalance(stud);
    doOptionsPrompt(scnr, stud);
  }

  /**
   * Prompts and deposits amount of money input into student account
   * 
   * @param scnr, stud
   */
  private static void doDepositFunds(Scanner scnr, Student stud) {
    double toAdd = -1;
    System.out.println(tellUserAccount(stud) + depositPrompt);
    boolean returnToOptions = false;
    do {
      String toChargeInput = getStringInput(scnr);
      if (checkReturn(toChargeInput)) {
        returnToOptions = true;
        break;
      }
      toAdd = getValidDollarAmount(toChargeInput);
    } while (toAdd < 0);
    if (returnToOptions) {
      doOptionsPrompt(scnr, stud);
      return;
    }
    stud.addMoney(toAdd);
    System.out.println(tellUserAccount(stud) + "Deposited of: $" + toAdd);
    tellBalance(stud);
    doOptionsPrompt(scnr, stud);
  }
  
  /**
   * Gets user input when prompted
   * 
   * @param scnr
   * @returns the next String the user types in. Null if nothing put
   */
  private static String getStringInput(Scanner scnr) {
    if (!scnr.hasNext()) {
      return null;
    }
    String input = scnr.next();
    System.out.println();
    return input;
  }
  
  /**
   * Checks if the user wants to return to account menu
   * 
   * @param checkKeys
   * @returns true if user typed return command
   */
  private static boolean checkReturn(String checkKeys) {
    if (checkKeys.equals("r")) {
      return true;
    }
    return false;
  }

  /**
   * Converts input into a dollar amount
   * 
   * @param input
   * @return a dollar amount truncated 2 decimals max. -1 if the input can't be converted
   */
  private static double getValidDollarAmount(String input) {
    double dollarAmount;
    try {
      dollarAmount = Double.parseDouble(input);
    } catch (NumberFormatException e) {
      System.out.println(dollarError);
      return -1;
    }
    if (dollarAmount < 0) {
      System.out.println(dollarError);
      return -1;
    }
    if (!input.contains(".")) {
      return dollarAmount;
    }
    String[] cuts = input.replace('.', ',').split(",");
    if (cuts.length < 2) {
      return dollarAmount;
    }
    if (cuts[1].length() < 3) {
      return dollarAmount;
    }
    cuts[1] = cuts[1].substring(0, 2);
    String roundedAmount = cuts[0] + "." + cuts[1];
    dollarAmount = Double.parseDouble(roundedAmount);
    return dollarAmount;
  }
  
  /**
   * Puts the name of the student in account format
   * 
   * @param stud
   * @returns students name as account format
   */
  private static String tellUserAccount(Student stud) {
    return "[" + stud.getName() + "\'s Account] ";
  }

  /**
   * Prints out prompt for getting id
   * 
   */
  public static void tellIdPrompt() {
    System.out.println(idPrompt);
  }
  
  /**
   * Prints out the menu for the options prompt
   * 
   * @param stud
   */
  public static void tellOptionsPrompt(Student stud) {
    System.out.println();
    System.out.println(tellUserAccount(stud) + optionsPrompt);
    for (String option : options) {
      System.out.println(option);
    }
  }
  
  /**
   * Prints out the students balance
   * 
   * @param stud
   */
  private static void tellBalance(Student stud) {
    System.out.println(tellUserAccount(stud) + balancePrompt + "$" + getValidDollarAmount("" + stud.getBalance()));
  }
  
  /**
   * Prints out the message of closing the student account
   * 
   */
  private static void tellExitStudent() {
    System.out.println(exitPrompt);
  }


}
