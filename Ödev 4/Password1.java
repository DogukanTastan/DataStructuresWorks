package homework4;
import java.util.Stack;


/**
* This class was created to password1 operations.
* @author Dogukan Tastan
* @version 1.0
* @since 2023-04-11
*/
public class Password1 {
	
	/**
     * Checks if a character from the username is in the password
     */
    public boolean containsUserNameSpirit(String username, String password1) {
    	
    	 /**
         * @param stack contains the username char by char.
         */
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < username.length(); i++) {
        	/** push the stack char by char */
            stack.push(username.charAt(i));
        }

        while (!stack.isEmpty()) {
            char currentChar = stack.pop();
            /** contains is string method */
            if (password1.contains(Character.toString(currentChar))) {
                return true;
            }
        }
        
        /** print error message */
        System.out.println("The password1 is invalid.It should have at least 1 character from the username");
        return false;
    }
    //**********************************************************************************************
    
    /**
     * Checks whether the parentheses are evenly distributed.
     */
    public boolean isBalancedPassword(String password1) {
    	
    	/**
         * @param stack contains the password1 char by char.
         */
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < password1.length(); i++) {
            char currentChar = password1.charAt(i);

            if (isOpenParenthesis(currentChar)) {
                stack.push(currentChar);
            } 
            else if (isCloseParenthesis(currentChar)) {
            	
                if (stack.isEmpty() || !isMatchingParenthesis(stack.pop(), currentChar)) {
                    System.out.println("Unbalanced parentheses found.");
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("Password1 is invalid. İt should be balanced");
            return false;
        }
        return true;
    }

    private boolean isOpenParenthesis(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private boolean isCloseParenthesis(char c) {
        return c == ')' || c == '}' || c == ']';
    }

    private boolean isMatchingParenthesis(char open, char close) {
        return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    }

    //**********************************************************************************************
    public boolean isPalindromePossible(String password1) {
    	
        return isPalindromePossible(password1, 0, extractLetters(password1));
    }
    
    /**  extracts other elements since only letters will be processed */
    private String extractLetters(String input) {
    	
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                letters.append(c);
            }
        }
        return letters.toString();
    }
    
    /** overriding function, This function will be called when extractLetters returns only a string of letters */
    private boolean isPalindromePossible(String password1, int oddCount, String letters) {
    	
        if (letters.length() == 0) {
          if( oddCount <= 1)
        	  return true;
          else {
        	  System.out.println("The password1 is invalid. It should be possible to obtain a palindrome from the password1.");
        	  return false;
          }
        }
        char firstChar = letters.charAt(0);
        
        /** I'm checking for a match for the rest. */
        String remainingLetters = letters.replace(Character.toString(firstChar), "");

        int firstCharCount = letters.length() - remainingLetters.length();

        /** counter increases if there is no match */
        if (firstCharCount % 2 == 1) {            
     
            oddCount++;
        }
        return isPalindromePossible(password1, oddCount, remainingLetters);
    }
}
