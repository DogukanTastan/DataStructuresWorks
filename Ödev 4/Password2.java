package homework4;
import java.util.Stack;

/**
* This class was created to password2 operations.
* @author Dogukan Tastan
* @version 1.0
* @since 2023-04-11
*/
public class Password2  {
	
	public boolean isExactDivision(int password2, int[] denominations) {
		/** Integer value checking */
        if (password2 < 10 || password2 > 10000) {
            System.out.println("The integer equivalent of password2 should be between 10 and 10,000.");
            return false;
        }
        
        boolean result = isExactDivision(password2, denominations, 0, 0);
        
        if (!result) {
            System.out.println("The password2 is invalid. It is not compatible with the denominations.");
        }
        return result;
    }
	
	
    private boolean isExactDivision(int password2, int[] denominations, int currentSum, int index) {
        if (currentSum == password2) {
            return true;
        }

        if (index == denominations.length || currentSum > password2) {
            return false;
        }

        boolean includeDenomination = isExactDivision(password2, denominations, currentSum + denominations[index], index);
        boolean excludeDenomination = isExactDivision(password2, denominations, currentSum, index + 1);

        return includeDenomination || excludeDenomination;
    }
}
