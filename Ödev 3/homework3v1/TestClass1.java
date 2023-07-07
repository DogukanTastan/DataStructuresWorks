package homework3v1;
import java.util.ArrayList; // import the ArrayList class

/**
* This class was made to perform test case 1
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/
public class TestClass1 {
	
	public static void main(String[] args) {
		
		ArrayList<Account> AllAccounts = new ArrayList<Account>();
		
		Account gizemsungu = new Account(1,"gizemsungu","13.01.1993","Istanbul");
		Account sibelgulmez = new Account(2,"sibelgulmez","10.03.1995","Istanbul");
		Account gokhankaya = new Account(3,"gokhankaya","05.02.1980","Istanbul");
		
		AllAccounts.add(gizemsungu);
		AllAccounts.add(sibelgulmez);
		AllAccounts.add(gokhankaya);
		
		sibelgulmez.login(AllAccounts);
		Post post1 =new Post(1,2,"I like Java.");
		sibelgulmez.addPost(post1);
		Post post2 =new Post(2,2,"Java the coffee...");
		sibelgulmez.addPost(post2);
		
		sibelgulmez.follow(gizemsungu);
		sibelgulmez.follow(gokhankaya);
		
		sibelgulmez.logout();

		gokhankaya.login(AllAccounts);
		gokhankaya.viewProfile(sibelgulmez);
		gokhankaya.viewPosts(sibelgulmez);
		
		Like like1 = new Like(gokhankaya);
		post1.addLike(like1);
		
		Comment comment1 = new Comment(gokhankaya,"me too!");
		post1.addComment(comment1);
		
		gokhankaya.follow(sibelgulmez);
		gokhankaya.follow(gizemsungu);
		
		Message message1 =new Message(1,gokhankaya,gizemsungu,"This homework is too easy!");
		gokhankaya.logout();
		
		gizemsungu.login(AllAccounts);
		gizemsungu.checkMessageBox();
		gizemsungu.viewInbox();
		gizemsungu.viewProfile(sibelgulmez);
		gizemsungu.viewPosts(sibelgulmez);
		gizemsungu.viewInteractions(sibelgulmez);
		Like like2 = new Like(gizemsungu);
		post1.addLike(like2);
		post2.addLike(like2);
		gizemsungu.viewInteractions(sibelgulmez);
		
		
	}
}
