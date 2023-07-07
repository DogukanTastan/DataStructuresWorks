package homework1;
import java.util.Scanner;

/**
* This class was created to create account objects and perform basic operations on them.
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/

class Account {
	
	private int accountId;
	private String username;
	private String birthdate;
	private String location;
	
	/** This is where objects of the post type are stored.  */
	private Post[] posts= new Post[100]; // Post ID
	private int post_size=0;
	/** Objects of the message type are stored here, but to avoid confusion, they are divided into incoming and outgoing messages.*/
	private Message[] incoming_messages= new Message[100]; // Message ID
	protected int incoming_messages_size=0;
	/** Objects of the message type are stored here, but to avoid confusion, they are divided into incoming and outgoing messages.*/
	private Message[] outgoing_messages= new Message[100]; // Message ID
	protected int outgoing_messages_size=0;
	/** The names of the followed users name are kept here. Since other information is not required, only the name is kept.*/
	private String[] following= new String[100]; // User ID
	private int following_size=0;
	/** The names of the followers name are kept here. Since other information is not required, only the name is kept.*/
	private String[] followers= new String[100]; // User ID
	private int followers_size=0;
	/** Objects of blocked users are kept here.  */
	private Account[] blockedUsers= new Account[100]; // User ID
	private int blockedUsers_size=0;
	
	/** This variable determines whether the user is logged in or not.  */
	boolean loginStatus=false;
	/** This variable is used to prevent the function that calls posts without seeing the profile from running.  */
	private String viewProfileInfo;
	
	 /**
     * account constructor takes 4 variables and makes the necessary assignments.
     * @param Id contains the user Id.
     * @param username contains the user name.
     * @param birthdate contains date of birth data.
     * @param location contains the user's location information.
     */
	Account(int Id,String username,String birthdate,String location){
		this.accountId=Id;
		this.username=username;
		this.birthdate=birthdate;
		this.location=location;	
		System.out.println("An account with username "+username+" has been created. ");
	}
	
	/**
     * Empty constructor
     */
	Account (){
		
	}
	
	/**
     * Login related operations are done here.
     * @param allAccounts The information of other objects is also needed to perform control operations.
     */
	
	public void login(Account[] allAccounts) {
		boolean flag=false;
		
		for(int i =0;i<allAccounts.length;i++) {
			
			if(allAccounts[i].loginStatus) {
				if(allAccounts[i].username.equals(username)) {
					System.out.println("You're already logged in!! Username: "+ username);
					flag=true;
					break;
				}
				else {
					System.out.println("Another user is logged in !!! Active user: "+ allAccounts[i].username);
					flag=true;
					break;
				}
			}
		}
		
		if(!flag) {
			
			if(loginStatus) {
				System.out.println("\nYou are already logged in !! (username: "+username+" )");
			}
			else {
				loginStatus=true;
				System.out.println("\nLogging into an account (username: "+ username +")");
			}
			
		}
	}
	
	/**
     * The incoming post object is stored here by joining the array and operations are performed
     */
	public void addPost(Post obj) {
		if(loginStatus) {
			
			posts[post_size]=obj;
			post_size++;
			System.out.println("Sharing posts…");
			
		}
		else {
			System.out.println("User '"+username+"' user cannot share posts,must log in first !!");
		}
	}
	
	/**
     * We display the user's current posts from here
     */
	public void viewPosts(Account obj) {
		
		if(viewProfileInfo.equals(obj.getUsername())){
			System.out.println("Viewing "+ obj.getUsername()+ "' posts..");
			
			for (int i=0; i<obj.getPost_size();i++) 
				System.out.println("(PostID: "+ obj.getPosts()[i].getPostId()+") "+ obj.getUsername()+": "+ obj.getPosts()[i].getContent());
		}
		else {
			System.out.println("Go to the profile first!!");
		}
	}
	
	/**
     * All interactions related to the post are printed on the screen here.
     */
	public void viewInteractions(Account obj) {
		
		System.out.println("\nViewing "+ obj.getUsername()+"'s posts' interactions...\n");
		for(int i=0;i<obj.getPost_size();i++) {
			System.out.println("---------------------------");
			System.out.println("(PostID: "+ obj.getPosts()[i].getPostId()+"): "+  obj.getPosts()[i].getContent()+"\n");
			
			System.out.println("The post has " + obj.getPosts()[i].getLike_size()+" like(s)...");
			for(int k=0;k<obj.getPosts()[i].getLike_size();k++) {
				System.out.println("The post was liked by the following account(s): "+ obj.getPosts()[i].getLikes()[k].getLikedBy()
						+" ,");		
			}
			System.out.println("The post has " + obj.getPosts()[i].getComment_size()+" comment(s)...");
			for(int k=0;k<obj.getPosts()[i].getComment_size();k++) {
				System.out.println("Comment "+ (i+1) +"'"+ obj.getPosts()[i].getComments()[k].getCommentingPerson()+ "'said '"
						+obj.getPosts()[i].getComments()[k].getContent()+"' " );
			}
		}
	}
	
	/**
     * Information about the profile is printed here. Controls are also performed.
     */
	public void viewProfile(Account obj){
		
		viewProfileInfo=obj.getUsername();
		boolean flag=false;
		for(int i=0;i<obj.getBlockedUsers_size();i++) {
			
			if(obj.getBlockedUsers()[i].getUsername().equals(username)) {
				flag=true;
				break;
			}	
		}
		
		if(flag) {
			System.out.println("This user has blocked you ! Cannot View Profile");
		}
		else {
			System.out.println("\nViewing "+ obj.getUsername() +" profile");
			System.out.println("User ID: "+ obj.getAccountId());
			System.out.println("Username: "+obj.getUsername());
			System.out.println("Location: "+obj.getLocation());
			System.out.println("Birth Date: " + obj.getBirthdate()+"\n");
			
			System.out.println(obj.getUsername()+ " is following "+ obj.getFollowing_size()+" account(s) and has "+
			obj.getFollowers_size() + " follower(s).");
			
			System.out.print(obj.getUsername()+ " is following: ");
			for (int i=0; i<obj.getFollowing_size();i++) 
				System.out.print(obj.getFollowing()[i] + " ,");
			
			System.out.println("\n"+ obj.getUsername()+ " has "+ obj.getPost_size() + " posts.\n");
		}
	}
	
	/**
     * The exit process and related controls are carried out here
     */
	public void logout() {
		
		if(!loginStatus) {
			System.out.println("User '"+ username+"' can't log out, must log in first !!");
		}
		else {
			
			loginStatus=false;
			System.out.println("Logging out from account (username: "+ username +")");
		}
	}
	
	/**
     * Follow operations is done here
     */
	public void follow(Account obj) {
		
		if(loginStatus) {
			addFollowing(obj.getUsername());
			obj.addFollower(username);
			System.out.println("'"+username+"' "+"Following " + obj.getUsername());
		}
		else {
			System.out.println("User: '"+ username+"' cannot follow "+obj.getUsername()+" must log in first !!");
		}
	}
	
	/**
     * Message box size info printed
     */
	
	public void checkMessageBox() {
		
		System.out.println("Checking outbox...\nThere is/are "+ outgoing_messages_size +" message(s) in the outbox.\n");
		System.out.println("Checking inbox...\nThere is/are "+ incoming_messages_size +" message(s) in the inbox.");
	}
	
	/**
     * View messagebox oeprations is done here
     */
	
	public void viewInbox() {
		
		System.out.println("\nViewing inbox...");
		
		for(int i=0; i<incoming_messages_size;i++) {
			System.out.println("Message ID: "+ incoming_messages[i].getMessageId()+"\nFrom: "+ incoming_messages[i].getSenderName().getUsername() +
				"\nTo: " + incoming_messages[i].getReceiverName().getUsername()+"\nMessage: "+ incoming_messages[i].getContent() +"\n");	
		}
	}
	
	/**
     * Block user operations is done here
     */
	public void block(Account obj) {
		
		blockedUsers[blockedUsers_size]=obj;
		blockedUsers_size++;
		System.out.println(username +" blocked user: " + obj.getUsername());
	}
	
	/**
     * Adding to the follower array is done here.
     */
	public void addFollower(String name) {
		
		followers[followers_size]=name;
		followers_size++;
	}
	
	/**
     * Adding to the following array is done here.
     */
	public void addFollowing(String name) {
		
		following[following_size]=name;
		following_size++;
	}
	
	/**
     * Adding to the addIncoming_messages array is done here.
     */
	public void addIncoming_messages(Message obj) {
		
		incoming_messages[incoming_messages_size]=obj;
		incoming_messages_size++;
	}
	
	/**
     * Adding to the addOutgoing_messages array is done here.
     */
	public void addOutgoing_messages(Message obj) {
		
		outgoing_messages[outgoing_messages_size]=obj;
		outgoing_messages_size++;
	}
	
	
	// Getter Setters
	
	public Account[] getBlockedUsers() {  
		return blockedUsers;
	}
	public void setBlockedUsers(Account[] blockedUsers) {
		this.blockedUsers = blockedUsers;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Post[] getPosts() {
		return posts;
	}

	public void setPosts(Post[] posts) {
		this.posts = posts;
	}
	
	public String[] getFollowing() {
		return following;
	}

	public void setFollowing(String[] following) {
		this.following = following;
	}
	
	public String[] getFollowers() {
		return followers;
	}

	public void setFollowers(String[] followers) {
		this.followers = followers;
	}
	
	public Message[] getOutgoing_messages() {
		return outgoing_messages;
	}
	public void setOutgoing_messages(Message[] outgoing_messages) {
		this.outgoing_messages = outgoing_messages;
	}
	public int getOutgoing_messages_size() {
		return outgoing_messages_size;
	}
	public void setOutgoing_messages_size(int outgoing_messages_size) {
		this.outgoing_messages_size = outgoing_messages_size;
	}
	public Message[] getIncoming_messages() {
		return incoming_messages;
	}
	public void setIncoming_messages(Message[] incoming_messages) {
		this.incoming_messages = incoming_messages;
	}
	public int getIncoming_messages_size() {
		return incoming_messages_size;
	}
	public void setIncoming_messages_size(int incoming_messages_size) {
		this.incoming_messages_size = incoming_messages_size;
	}

	public int getPost_size() {
		return post_size;
	}

	public void setPost_size(int post_size) {
		this.post_size = post_size;
	}

	public int getFollowing_size() {
		return following_size;
	}

	public void setFollowing_size(int following_size) {
		this.following_size = following_size;
	}

	public int getFollowers_size() {
		return followers_size;
	}

	public void setFollowers_size(int followers_size) {
		this.followers_size = followers_size;
	}

	public int getBlockedUsers_size() {
		return blockedUsers_size;
	}

	public void setBlockedUsers_size(int blockedUsers_size) {
		this.blockedUsers_size = blockedUsers_size;
	}
}