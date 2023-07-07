package homework4;

public class TestClass {

	public static void main(String[] args) {
		
		  int[] denominations = {4, 17, 29};
		
		Username username = new Username();
		Password1 pass1 = new Password1();
		Password2 pass2 = new Password2();
		if(username.checkIfValidUsername("dogukan") 
				&& pass1.containsUserNameSpirit("dogukan", "cc22a")
				&& pass1.isBalancedPassword("abcd(cb)a") 
				&& pass1.isPalindromePossible("aba")
				&& pass2.isExactDivision(35,denominations)) {
			
			System.out.println("The username and passwords are valid. The door is opening, please wait..");
		}
	}
}
