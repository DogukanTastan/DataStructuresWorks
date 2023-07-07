package homework1;

/**
* Interaction class is abstract and created for like and comment classes.
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/
public abstract class Interaction {
	
	protected int interactionId;
	protected int accountId;
	protected int postId;
	
	public int getInteractionId() {
		return interactionId;
	}
	public void setInteractionId(int interactionId) {
		this.interactionId = interactionId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
}
