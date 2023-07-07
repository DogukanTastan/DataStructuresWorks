package homework1;

/**
* Class that creates post objects
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/
public class Post {
	
	private int postId;
	private int accountId;
	private String content;
	private Like [] likes= new Like[100];
	private int like_size=0;
	private Comment[] comments= new Comment[100]; // Comment ID
	private int comment_size=0;
	
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
		
		obj.setAccountId(accountId);
		obj.setPostId(postId);
		likes[like_size]=obj;
		like_size++;
		System.out.println("Liking a post");
	}
	
	/**
     * addComment method adding comment object to comment array in the post object
     */
	public void addComment(Comment obj) {
		
		System.out.println(obj.getCommentingPerson()+" Comments Post ID:" + postId);
		obj.setAccountId(accountId);
		obj.setPostId(postId);
		comments[comment_size]=obj;
		comment_size++;
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

	public Comment[] getComments() {
		return comments;
	}

	public void setComments(Comment[] comments) {
		this.comments = comments;
	}
	
	public Like[] getLikes() {
		return likes;
	}

	public void setLikes(Like[] likes) {
		this.likes = likes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getLike_size() {
		return like_size;
	}

	public void setLike_size(int like_size) {
		this.like_size = like_size;
	}

	public int getComment_size() {
		return comment_size;
	}

	public void setComment_size(int comment_size) {
		this.comment_size = comment_size;
	}
}
