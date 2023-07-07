package hw5;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.*;  
import javax.swing.tree.DefaultMutableTreeNode; 
	/**
.....* Jtree veri yapısındaki her bir parçayı ifade eden class
.....* @author Doğukan Taştan
.....* @version 1.0
.....* @since 2023-05-02
.....*/
public class Jtree {
	private DefaultMutableTreeNode root=new DefaultMutableTreeNode("Root"); 
	/** @param f Frame element showing the output to the screen */
	 JFrame f=new JFrame(); 
	 
	Jtree(String[][] result){
		fileArrayToTree(result);
	}
	
	/** Function that adds the array read from the mainden file to the tree */
    public void fileArrayToTree(String[][] pathArray) {
        for (String[] location : pathArray) {
            DefaultMutableTreeNode parentNode = root;
            for (String itemName : location) {
                DefaultMutableTreeNode childNode = findNode(parentNode, itemName);
                if (childNode == null) {
                    childNode = addNode(parentNode, itemName);
                }
                parentNode = childNode;
            }
        } 
      }
	  
    /** creates a new DefaultMutableTreeNode and adds it to the parentNode */
    public DefaultMutableTreeNode addNode(DefaultMutableTreeNode parentNode, String nodeName) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);
        parentNode.add(newNode);
        return newNode;
    }
    /** Search function used to provide hierarchical structure when adding to Tree */
    private DefaultMutableTreeNode findNode(DefaultMutableTreeNode parentNode, String nodeName) {
        for (int i = 0; i < parentNode.getChildCount(); i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);
            if (childNode.toString().equals(nodeName)) {
                return childNode;
            }
        }
        return null;
    }
    /** Our function that performs move and delete operations */
    public void move(String[] src,String[] dest) {
    	 DefaultMutableTreeNode srcNode = root;
         for (String itemName : src) {
             DefaultMutableTreeNode childNode = findNode(srcNode, itemName); /** find source location */
             if (childNode == null) {
                 System.out.print("Cannot move ");
                 for(int i=0;i<src.length;i++)
                	 System.out.print(src[i]+(i!=src.length-1 ? "->":""));
                 System.out.print(" because it doesn't exist in the tree .");
                 return;		
             }
             srcNode = childNode;
         }
         DefaultMutableTreeNode destNode = root;
         for (String itemName : dest) {
             DefaultMutableTreeNode childNode = findNode(destNode, itemName);  /** find destination location */
             if (childNode == null) {
                 childNode = addNode(destNode, itemName);
             }
             destNode = childNode;
         }
         /** so far we have found the file to copy and where to copy it */
         
         /** @param list We kept this list of names in the section to be copied */
         LinkedList<String[]> list = new LinkedList<String[]>(); 
         
         /** @param stack Stack used for additional Dfs search operation */
         Stack<DefaultMutableTreeNode> stack = new Stack<>();
         stack.add(srcNode);
         /**This value is initially 2 because we want to include the folder 1 above in our copy operation*/
         int initialLevel=2; 
         
         while (!stack.isEmpty()) {
             DefaultMutableTreeNode currentNode = stack.pop();

             String[] currentpath= new String[currentNode.getLevel()-initialLevel+1]; // to determine how many times I have to go back 
             DefaultMutableTreeNode iter = currentNode;
             int i = currentNode.getLevel()-initialLevel;
             
             while(iter.getParent() != null && i > -1){ // adding the name of the files to be copied to the list 
                 currentpath[i] = iter.toString();
                 iter = (DefaultMutableTreeNode) iter.getParent();
                 i--;
             }
             list.add(currentpath);
             for (int k = 0; k < currentNode.getChildCount(); k++) { // It's part of the dfs algorithm we use, where we go through all the files. 
            	 stack.push((DefaultMutableTreeNode) currentNode.getChildAt(k));
             }
         }
         for (Object location : list.toArray()) {			// Adds to the destination by looking at the names in the Linkedlist.
	            DefaultMutableTreeNode parentNode = destNode;
	            for (String itemName : (String[])location) {
	                DefaultMutableTreeNode childNode = findNode(parentNode, itemName);
	                if (childNode == null) {
	                    childNode = addNode(parentNode, itemName);
	                }
	                else {
	                	DefaultMutableTreeNode iter = childNode;
	                	 while(iter.getParent() != null ){ // adding the name of the files to be copied to the list 
	                         System.out.print(iter.toString()+"->"); 
	                         iter = (DefaultMutableTreeNode) iter.getParent(); 
	                     }
	                	System.out.println("has been overwritten.");
	                }             
	                parentNode = childNode;
	            }
	        } 
         
         DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) srcNode.getParent();
         srcNode.removeFromParent();
         while(parentNode.getChildCount() == 0) {
        	 DefaultMutableTreeNode temp=(DefaultMutableTreeNode) parentNode.getParent();
        	 parentNode.removeFromParent();
        	 parentNode=temp; 
         }      	
         /*for(String[] x : list)
        	 System.out.println(Arrays.toString(x));*/
    }
    
	  public void writeTree() {
		  /** If there is an open frame, we close it before printing a new one. */
	    //f.dispose(); 
	    JTree jt=new JTree(root);
       
        /** The process to make the jtree structure appear on the screen with each folder opened, not closed */
        for (int i = 0; i < jt.getRowCount(); i++) 
            jt.expandRow(i);
        
        /** jtree settings */
        f.add(jt);  
        f.setSize(300,500);  
        f.setVisible(true); 
        f.setAlwaysOnTop(true);
	  }
    public void exit(){
        f.dispose();
    }  

    public void bfsSearch(String target) {
    	 /** While doing bfs search, we print what we travel by throwing it to Queue */
        Queue<DefaultMutableTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int step = 0;

        while (!queue.isEmpty()) {
        	/** remove the existing node from the queue */
            DefaultMutableTreeNode currentNode = queue.poll();
            step++;

            System.out.println("Step " + step + ": Checking " + currentNode.getUserObject().toString());

            if (currentNode.getUserObject().toString().equals(target)) {
                System.out.println("Found " + target + " in step " + step);
                return;
            }
            /** we add the children of the existing node to the queue */
            for (int i = 0; i < currentNode.getChildCount(); i++) {
                queue.add((DefaultMutableTreeNode) currentNode.getChildAt(i));
            }
        }
        System.out.println(target + " not found in the tree.");
    }
    
    public void dfsSearch(String target) {
    	/** The stack we use for dfs search */
        Stack<DefaultMutableTreeNode> stack = new Stack<>();
        stack.push(root);
        int step = 0;

        while (!stack.isEmpty()) {
            DefaultMutableTreeNode currentNode = stack.pop();
            step++;

            System.out.println("Step " + step + ": Checking " + currentNode.getUserObject().toString());

            if (currentNode.getUserObject().toString().equals(target)) {
                System.out.println("Found " + target + " in step " + step);
                return;
            }

            for (int i = 0; i <=currentNode.getChildCount() - 1 ; i++) {
                stack.push((DefaultMutableTreeNode) currentNode.getChildAt(i));
            }
        }
        System.out.println(target + " not found in the tree.");
    }

    /** In the recuirsive structure I split it into 2 functions to be able to use it by taking only String  */
    public void postOrderTraversalSearch(String target) {
        int[] step = {0};
        if (postOrderTraversal(root, target, step)) {
            System.out.println("Found " + target + " in step " + step[0]);
        } else {
            System.out.println(target + " not found in the tree.");
        }
    }
    private boolean postOrderTraversal(DefaultMutableTreeNode currentNode, String target, int[] step) {
        for (int i = 0; i < currentNode.getChildCount(); i++) {
            if (postOrderTraversal((DefaultMutableTreeNode) currentNode.getChildAt(i), target, step)) {
                return true;
            }
        }
        step[0]++;
        System.out.println("Step " + step[0] + ": Checking " + currentNode.getUserObject().toString());
        return currentNode.getUserObject().toString().equals(target);
    }
}