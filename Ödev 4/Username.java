package homework4;
import java.util.Stack;

/**
* This class was created to username operations.
* @author Dogukan Tastan
* @version 1.0
* @since 2023-04-11
*/
public class Username {

	/** function which checks if it contains only letters, and the minimum length is 1. */
    public boolean checkIfValidUsername(String username) {
        if (username == null || username.length() == 0) {
        	System.out.println("The username is invalid.It should have at least 1 character.");
            return false;
        }
        
        /** call helper */
        return checkIfValidUsernameHelper(username, 0);
    }
    private boolean checkIfValidUsernameHelper(String username, int index) {
    	
        if (index == username.length()) {
            return true;
        }

        char currentChar = username.charAt(index);
        if (!Character.isLetter(currentChar)) {
            System.out.println("The username is invalid.It should have letters only. ");
            return false;
        }

        return checkIfValidUsernameHelper(username, index + 1);
    }
}
