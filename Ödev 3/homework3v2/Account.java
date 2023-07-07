package homework3v2;
import java.util.LinkedList;

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
	private LinkedList <Post> posts = new LinkedList<>();
	/** Objects of the message type are stored here, but to avoid confusion, they are divided into incoming and outgoing messages.*/
	private LinkedList <Message> incoming_messages = new LinkedList<>();
	/** Objects of the message type are stored here, but to avoid confusion, they are divided into incoming and outgoing messages.*/
	private LinkedList <Message> outgoing_messages = new LinkedList<>();
	/** The names of the followed users name are kept here. Since other information is not required, only the name is kept.*/
	private LinkedList <String> following = new LinkedList<>();
	/** The names of the followers name are kept here. Since other information is not required, only the name is kept.*/
	protected LinkedList <String> followers = new LinkedList<>();
	/** Objects of blocked users are kept here.  */
	protected LinkedList <Account> blockedUsers = new LinkedList<>();
	/** This variable determines whether the user is logged in or not.  */
	protected LinkedList <String> history = new LinkedList<>();
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
	
	public void login(LinkedList <Account> allAccounts) {
		boolean flag=false;
		
		for(int i =0;i<allAccounts.size();i++) {
			
			if(allAccounts.get(i).loginStatus) {
				if(allAccounts.get(i).username.equals(username)) {
					System.out.println("You're already logged in!! Username: "+ username);
					flag=true;
					break;
				}
				else {
					System.out.println("Another user is logged in !!! Active user: "+ allAccounts.get(i).username);
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
			
			posts.add(obj);
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
			
			for (int i=0; i<obj.posts.size();i++) 
				System.out.println("(PostID: "+  obj.posts.get(i).getPostId()+") "+ obj.getUsername()+": "+  obj.posts.get(i).getContent());
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
		for(int i=0;i<obj.posts.size();i++) {
			System.out.println("---------------------------");
			System.out.println("(PostID: "+ obj.posts.get(i).getPostId()+"): "+   obj.posts.get(i).getContent()+"\n");
			
			System.out.println("The post has " +  obj.posts.get(i).likes.size()+" like(s)...");
			for(int k=0;k< obj.posts.get(i).likes.size();k++) {
				System.out.println("The post was liked by the following account(s): "+  obj.posts.get(i).likes.get(k).getLikedBy()
						+" ,");		
			}
			System.out.println("The post has " +  obj.posts.get(i).comments.size()+" comment(s)...");
			for(int k=0;k< obj.posts.get(i).comments.size();k++) {
				System.out.println("Comment "+ (k+1) +"'"+  obj.posts.get(i).comments.get(k).getCommentingPerson()+ "'said '"
						+ obj.posts.get(i).comments.get(k).getContent()+"' " );
			}
		}
	}
	
	/**
     * Information about the profile is printed here. Controls are also performed.
     */
	public void viewProfile(Account obj){
		
		viewProfileInfo=obj.getUsername();
		boolean flag=false;
		for(int i=0;i<obj.blockedUsers.size();i++) {
			
			if(obj.blockedUsers.get(i).getUsername().equals(username)) {
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
			
			System.out.println(obj.getUsername()+ " is following "+ obj.following.size()+" account(s) and has "+
			obj.followers.size() + " follower(s).");
			
			System.out.print(obj.getUsername()+ " is following: ");
			for (int i=0; i<obj.following.size();i++) 
				System.out.print(obj.following.get(i) + " ,");
			
			System.out.println("\n"+ obj.getUsername()+ " has "+ obj.posts.size() + " posts.\n");
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
			history.add("'"+username+"' "+"Following " + obj.getUsername());
			System.out.println("'"+username+"' "+"Following " + obj.getUsername());
		}
		else {
			System.out.println("User: '"+ username+"' cannot follow "+obj.getUsername()+" must log in first !!");
		}
	}
	
	public void unfollow(Account obj) {
		boolean flag=true;
		
		if(loginStatus) {
		
			for(int i=0;i<following.size();i++) {
				if(following.get(i).equals( obj.getUsername() )) {
					System.out.println(username+" Unfollow " + obj.getUsername()+"\n");
					history.add(username+" Unfollow " + obj.getUsername()+"\n");
					following.remove(i);
					flag=false;
				}
			}
			if(flag)
				System.out.println("Must first follow for this operation !! ");
			
		}
		else {
			System.out.println("User: '"+ username+"' cannot follow "+obj.getUsername()+" must log in first !!");
		}
	}
	
	
	
	
	/**
     * Message box size info printed
     */
	
	public void checkMessageBox() {
		
		System.out.println("Checking outbox...\nThere is/are "+ outgoing_messages.size()+" message(s) in the outbox.\n");
		System.out.println("Checking inbox...\nThere is/are "+ incoming_messages.size() +" message(s) in the inbox.");
	}
	
	/**
     * View messagebox oeprations is done here
     */
	
	public void viewInbox() {
		
		System.out.println("\nViewing inbox...");
		
		for(int i=0; i<incoming_messages.size();i++) {
			System.out.println("Message ID: "+ incoming_messages.get(i).getMessageId()+"\nFrom: "+ incoming_messages.get(i).getSenderName().getUsername() +
				"\nTo: " + incoming_messages.get(i).getReceiverName().getUsername()+"\nMessage: "+ incoming_messages.get(i).getContent() +"\n");	
		}
	}
	
	/**
     * Block user operations is done here
     */
	public void block(Account obj) {
		
		blockedUsers.add(obj);
		System.out.println(username +" blocked user: " + obj.getUsername());
		
		for(int i=0;i<following.size();i++) {
			if(following.get(i).equals( obj.getUsername() )) {
				System.out.println(username+" Unfollow  " + obj.getUsername()+" Because Blocked\n");
				history.add(username+" Unfollow  " + obj.getUsername()+" Because Blocked\n");
				following.remove(i);
			}		
		}
	}
	
	public void unBlock(Account obj) {
		
		for(int i=0;i< blockedUsers.size();i++) {
			if(blockedUsers.get(i).getUsername().equals(obj.getUsername())) {
				blockedUsers.remove(i);
				unfollow(obj);
				System.out.println(username +" unblocked user: " + obj.getUsername());
			}
		}
	}
	
	public void printHistory() {
		
		System.out.println("........................ "+username + " History ........................");
		
		for(int i=0;i< history.size();i++) {
			System.out.println(history.get(i));
		}
	}
	
	/**
     * Adding to the follower array is done here.
     */
	public void addFollower(String name) {
		
		followers.add(name);
	}
	
	public void addHistory(String hist) {
		history.add(hist);
	}
	
	/**
     * Adding to the following array is done here.
     */
	public void addFollowing(String name) {
		
		following.add(name);
	}
	
	/**
     * Adding to the addIncoming_messages array is done here.
     */
	public void addIncoming_messages(Message obj) {
		
		incoming_messages.add(obj);
	}
	
	/**
     * Adding to the addOutgoing_messages array is done here.
     */
	public void addOutgoing_messages(Message obj) {
		
		outgoing_messages.add(obj);
	}
	
	
	// Getter Setters
	
	public LinkedList<Account> getBlockedUsers() {  
		return blockedUsers;
	}
	public void setBlockedUsers(LinkedList<Account> blockedUsers) {
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

	public  LinkedList <Post> getPosts() {
		return posts;
	}

	public void setPosts( LinkedList <Post> posts) {
		this.posts = posts;
	}
	
	public LinkedList<String> getFollowing() {
		return following;
	}

	public void setFollowing(LinkedList<String> following) {
		this.following = following;
	}
	
	public LinkedList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(LinkedList<String> followers) {
		this.followers = followers;
	}
	
	public  LinkedList<Message> getOutgoing_messages() {
		return outgoing_messages;
	}
	public void setOutgoing_messages( LinkedList<Message> outgoing_messages) {
		this.outgoing_messages = outgoing_messages;
	}
	public LinkedList<Message> getIncoming_messages() {
		return incoming_messages;
	}
	public void setIncoming_messages(LinkedList<Message> incoming_messages) {
		this.incoming_messages = incoming_messages;
	}
}