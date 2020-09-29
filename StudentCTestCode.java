// --== CS400 File Header Information ==--
// Name: Cameron Berhow
// Email: cberhow@wisc.edu
// Team: BG
// Role: Back End Developer
// TA: bcochran2@wisc.edu
// Lecturer: Gary Dahl
// Test Code
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentCTestCode {
	
	public static boolean testCreateStudent() {
		StudentC s = new StudentC("867530999", (float)15.60, false, "Tester");
	    if(!(s.getStudent("867530999").equals(s)))
	    	return false;
	    if(!(s.getBalance()==((float)15.60)))
	    	return false;
	    if(!(s.getResidency()==false))
	    	return false;
	    if(!(s.getID().equals("867530999")))
			return false;
	    if(!(s.getName().equals("Tester")))
	    	return false;
	    return true;
	}
	
	public static boolean testAddMoney() {
		StudentC s = new StudentC("867530999", (float)15.60, false, "Tester");
		if(!(s.addMoney(50)==(float)65.60))
			return false;
		return true;
	}
	  
	public static boolean testdeductmoney() {
		StudentC s = new StudentC("867530999", (float)15.60, false, "Tester");
		try {
			s.deductMoney(69);
			return false;
		}catch(Exception e1) {
			try {
				if(!(s.deductMoney((float) 5.6)==(float)10)) {
					System.out.println("Here");
						return false;
				}
			} catch (Exception e2) {
				return false;
			}
			return true;					
		}
	  }
	  
	public static boolean testdoIdPrompt() {

	  //You have to pass in a Scanner obj with the String of the id as input
	  Scanner scnr = new Scanner(System.in);
	  
	   FrontEnd f = new FrontEnd();
	     int idlength= Integer.parseInt(f.doIdPrompt(scnr));
	     if((""+idlength).length()!=9)
	     { System.out.print("Test failed");
	       return false; 
	     }
	       System.out.print("Test passed");
	 return true;

	}

	public static void runTests() {
		boolean testCreateStudent=testCreateStudent();
		boolean testAddMoney=testAddMoney();
		boolean testdeductmoney=testdeductmoney();
		boolean testdoIdPrompt=testdoIdPrompt();
		System.out.println("testCreateStudent resulted in "+testCreateStudent);
		System.out.println("testAddMoney resulted in "+testAddMoney);
		System.out.println("testdeductmoney resulted in "+testdeductmoney);
		System.out.println("testdoIdPrompt resulted in "+testdoIdPrompt);
		if(testCreateStudent&&testAddMoney&&testdeductmoney&&testdoIdPrompt)
			System.out.println("All tests successful!");
		else
			System.out.println("One or more tests unsuccesful");
	}
}
