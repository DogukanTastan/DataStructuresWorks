package homework3v3;

/**
* This class contains likes objects and inherits from the Interactions class
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/
public class Like extends Interaction{
	
	private String likedBy;
	protected Account user;
	
	/**
     * like constructor take 1 paremeter account object and send super class interaction
     */
	Like(Account obj){
		
		user=obj;
		likedBy=obj.getUsername();
		super.accountId=obj.getAccountId();
	}
	
	public String getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(String likedBy) {
		this.likedBy = likedBy;
	}
}
