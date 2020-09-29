// --== CS400 File Header Information ==--
// Name: Elan Graupe
// Email: graupe@wisc.edu
// Team: BG
// Role: Backend Developer
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * Defines an exception that is thrown if a student has insufficient funds to make a purchase
 * 
 * @author Elan Graupe
 *
 */
public class InsufficientFundsException extends RuntimeException {

  private String message;

  public InsufficientFundsException() {
    setMessage("");
  }

  public InsufficientFundsException(String msg) {
    setMessage(msg);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
