package hw6;

/**
.....* The class is main class
.....* @author Dogukan Tastan - 1901042627
.....* @version 1.0
.....* @since 2023-05-09
.....*/
public class Main {
    public static void main(String[] args) {
       
        String input = "Buzzing bees buzz.";
        
        System.out.println("Original String: " + input);
        String cleanedInput = input.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        System.out.println("Preprocessed String: " + cleanedInput);
        
        try {
        	myMap mapObject = new myMap(cleanedInput);
        }
        catch(IllegalArgumentException e) {
        	System.out.println("Invalid Input !!");
        }
    }
}
