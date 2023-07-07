package homework3v2;
import java.util.LinkedList;


public class TestClass4 {

	public static void main(String[] args) {
		
		LinkedList <Account> AllAccounts = new LinkedList<>();
		
		Account gizemsungu = new Account(1,"gizemsungu","13.01.1993","Istanbul");
		Account sibelgulmez = new Account(2,"sibelgulmez","10.03.1995","Istanbul");
		Account gokhankaya = new Account(3,"gokhankaya","05.02.1980","Istanbul");
		Account dogukantastan = new Account(4,"dogukantastan","07.08.2000","Kocaeli");
		Account emreyilmaz = new Account(5,"emreyilmaz","05.02.2000","Trabzon");
		Account mustafayilmaz = new Account(6,"mustafayilmaz","01.09.1972","Ankara");
		Account ahmetyilmaz = new Account(7,"ahmetyilmaz","04.03.1965","Mersin");
		Account mehmetyilmaz = new Account(8,"mehmetyilmaz","25.06.1988","Malatya");
		Account miraytastan = new Account(9,"miraytastan","05.12.1985","Tunceli");
		Account temeltastan = new Account(10,"temeltastan","05.05.1977","Edirne");
		
		AllAccounts.add(gizemsungu);
		AllAccounts.add(sibelgulmez);
		AllAccounts.add(gokhankaya);
		AllAccounts.add(dogukantastan);
		AllAccounts.add(emreyilmaz);
		AllAccounts.add(mustafayilmaz);
		AllAccounts.add(ahmetyilmaz);
		AllAccounts.add(mehmetyilmaz);
		AllAccounts.add(miraytastan);
		AllAccounts.add(temeltastan);
		
		
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
		
		//---------------------------------- Test SCENARIO 4
		System.out.println("\n--------Test SCENARIO 4--------\n**********************************\n");
		post2.unLike(gizemsungu);
		gizemsungu.viewInteractions(sibelgulmez);  // unlike
		
		gizemsungu.block(sibelgulmez);
		gizemsungu.logout();
		sibelgulmez.login(AllAccounts);
		sibelgulmez.viewProfile(gizemsungu);
		Message message3 =new Message(3,sibelgulmez,gizemsungu,"hello gizem"); // block-unblock
		sibelgulmez.logout();
		gizemsungu.login(AllAccounts);
		gizemsungu.unBlock(sibelgulmez);
		gizemsungu.logout();
		sibelgulmez.login(AllAccounts);
		sibelgulmez.viewProfile(gizemsungu);
		sibelgulmez.logout();
		
		dogukantastan.login(AllAccounts);
		dogukantastan.follow(temeltastan);
		dogukantastan.follow(sibelgulmez);
		dogukantastan.follow(gizemsungu);  // follow-unfollow
		dogukantastan.follow(mustafayilmaz);
		dogukantastan.follow(emreyilmaz);
		dogukantastan.viewProfile(dogukantastan);
		dogukantastan.unfollow(temeltastan);	
		dogukantastan.viewProfile(dogukantastan);
		
		dogukantastan.unfollow(mustafayilmaz);
		dogukantastan.unfollow(gizemsungu);
		dogukantastan.viewProfile(dogukantastan);
		dogukantastan.logout();
		
		ahmetyilmaz.login(AllAccounts);
				
		Post post3 =new Post(3,7,"I like CSE 222.");		
		ahmetyilmaz.addPost(post3);		
		ahmetyilmaz.viewProfile(ahmetyilmaz);	
		ahmetyilmaz.viewInteractions(ahmetyilmaz);		
		Comment comment2 = new Comment(dogukantastan,"me too !");  // comment-uncomment
		Comment comment3 = new Comment(gizemsungu,"Hello  !");
		post3.addComment(comment2);
		post3.addComment(comment3);
		ahmetyilmaz.viewProfile(ahmetyilmaz);	
		ahmetyilmaz.viewInteractions(ahmetyilmaz);
		post3.unComment(gizemsungu);
		ahmetyilmaz.viewProfile(ahmetyilmaz);	
		ahmetyilmaz.viewInteractions(ahmetyilmaz);
		
		gizemsungu.printHistory();  // print history
		
		
	}
}
