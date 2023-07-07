package hw5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
.....* Main class
.....* @author Doğukan Taştan
.....* @version 1.0
.....* @since 2023-05-02
.....*/

public class Main {
    public static void main(String[] args) {
    	/** variables used for file read operations and receiving input from the user */
        String filePath = "tree.txt";
        String[][] result = parseFile(filePath);

        Scanner input = new Scanner(System.in);
        String src[];
        String dest[]=new String[1];
        String target;
        int choice;
        boolean exit = false;

        Jtree tr = new Jtree(result);
        while(!exit) {
        	System.out.print("1-Write Tree\n2-Run BFS Search\n3-Run DFS Search\n4-Run Traversal Algorithm.\n5-Run Move Operation\n6-Exit\n-> ");
            choice = input.nextInt();
            input.nextLine();
        	if(choice==1) 
        		tr.writeTree();
        	else if(choice==2) {
        		System.out.print("Enter the name to search for: \n-> ");
        		target = input.nextLine();
        		tr.bfsSearch(target);
        	}
        	else if(choice==3) {
        		System.out.print("Enter the name to search for: \n-> ");
        		target = input.nextLine();
        		tr.dfsSearch(target);
        	}
        	else if(choice==4) {
        		System.out.print("Enter the name to search for: \n-> ");
        		target = input.nextLine();
        		tr.postOrderTraversalSearch(target);
        	}
        	else if(choice==5) {
        		System.out.print("Enter the source:(for example:2022,CSE321,LECTURE1) \n-> ");
        		target = input.nextLine();
        		src=target.split(",");
        		System.out.print("Enter the destination:(for example:2022) \n-> ");
        		dest[0]=input.nextLine();		
        		tr.move(src,dest);
        		tr.writeTree();
        	}
        	else if(choice==6) {
                exit=true;
                tr.exit();
            }
        	else 
        		System.out.println("Invalid selection, try again!");
        } 
    }

    public static String[][] parseFile(String filePath) 
    {
        String[][] result = new String[0][];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                result = extendArray(result, rowIndex + 1);
                result[rowIndex] = row;
                rowIndex++;
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
    /** Since we are using a dynamically growing array, we expanded it with this function */
    public static String[][] extendArray(String[][] original, int newSize) {
        String[][] extendedArray = new String[newSize][];
        System.arraycopy(original, 0, extendedArray, 0, original.length);
        return extendedArray;
    }
}
