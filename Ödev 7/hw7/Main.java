package hw7;

/**
.....* The class is main class
.....* @author Dogukan Tastan - 1901042627
.....* @version 1.0
.....* @since 2023-05-09
.....*/
public class Main {
	
	public static String cleanfunc(String input) {
		 System.out.println("Original String: " + input);
	     String cleanedInput = input.replaceAll("[^a-zA-Z ]", "").toLowerCase();
	     System.out.println("Preprocessed String: " + cleanedInput);
	     return cleanedInput;
	}
    public static void main(String[] args) {

        try {
        	
        	System.out.println("\n---Best Case: ---\n");
         	String bestcase = cleanfunc("a");
             myMap mapObject1 = new myMap(bestcase);
             mapObject1.runSort();
             
             System.out.println("\n---Average Case: ---\n");
             String averagecase = cleanfunc("abbc");
             myMap mapObject2 = new myMap(averagecase);
             mapObject2.runSort();
             
             System.out.println("\n---Worst Case: ---\n");
             String worstcase = cleanfunc("aaaabbbccd");
             myMap mapObject3 = new myMap(worstcase);
             mapObject3.runSort();
        	
        }
        catch(IllegalArgumentException e) {
        	System.out.println("Invalid Input Caught !!");
        }
    }
}
