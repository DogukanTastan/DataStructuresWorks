package homework1;

/**
* This class contains comment objects and inherits from the Interactions class
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/
public class Comment extends Interaction{

	private String content;
	private String commentingPerson;
	
	/**
     * Comment constructor take 2 parameter
     */
	Comment(Account obj,String content){
		this.content=content;
		this.commentingPerson=obj.getUsername();
		super.accountId=obj.getAccountId();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentingPerson() {
		return commentingPerson;
	}
	public void setCommentingPerson(String commentingPerson) {
		this.commentingPerson = commentingPerson;
	}
}
