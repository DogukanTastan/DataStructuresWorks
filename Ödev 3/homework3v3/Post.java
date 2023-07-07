package homework3v3;
/**
* Class that creates post objects
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/
public class Post{
	
	private int postId;
	private int accountId;
	private String content;
	protected LDLinkedList <Like> likes = new LDLinkedList<>();
	protected LDLinkedList <Comment> comments = new LDLinkedList<>();
	
	/**
     * post constructor take 3 paremeter 
     */
	Post(int postId,int accountId,String content){
	
		this.postId=postId;
		this.accountId=accountId;
		this.content= content;
	}
	
	/**
     * addLike method adding like object to like array in the post object
     */
	
	public void addLike(Like obj) {
		
		obj.user.addHistory("Liking a post: "+ postId);
		obj.setAccountId(accountId);
		obj.setPostId(postId);
		likes.add(obj);
		System.out.println("Liking a post");
	}
	public void unLike(Account obj) {
		
		for(int i=0;i<likes.size();i++) {
			if(likes.get(i).getLikedBy().equals( obj.getUsername() )) {
				likes.remove(i);
				obj.addHistory("Unlike a post: " + postId);
				System.out.println("Unlike a post");
			}
		}
	}
	
	/**
     * addComment method adding comment object to comment array in the post object
     */
	public void addComment(Comment obj) {
		
		obj.user.addHistory(obj.getCommentingPerson()+" Comments Post ID:" + postId);
		System.out.println(obj.getCommentingPerson()+" Comments Post ID:" + postId);
		obj.setAccountId(accountId);
		obj.setPostId(postId);
		comments.add(obj);
	}
	
	public void unComment(Account obj) {
		
		for(int i=0;i<comments.size();i++) {
			if(comments.get(i).getCommentingPerson().equals( obj.getUsername() )) {
				comments.remove(i);
				obj.addHistory(obj.getUsername() + " Uncomment Post: "+ postId+"\n");
				System.out.println(obj.getUsername() + " Uncomment Post: "+ postId+"\n");
			}
		}	
	}

	 // Setter Getters
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public LDLinkedList<Comment> getComments() {
		return comments;
	}

	public void setComments(LDLinkedList<Comment> comments) {
		this.comments = comments;
	}
	
	public  LDLinkedList<Like> getLikes() {
		return likes;
	}

	public void setLikes( LDLinkedList<Like> likes) {
		this.likes = likes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
