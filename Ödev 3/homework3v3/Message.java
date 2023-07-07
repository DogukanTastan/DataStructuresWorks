package homework3v3;

/**
* The reason why this class inherits from account is that it calls the addMessage function when a message object is created.
* @author Dogukan Tastan
* @version 1.0
* @since 2023-03-16
*/
class Message extends Account{

	 private int messageId;
	 private Account sender;
	 private Account receiver;
	 private String content;
	 private boolean flag=false;
	 private boolean flag2=false;
	 
	 	/**
	     * message class constructor takes 4 parameter
	     */
	 Message(int messageId,Account sender,Account receiver,String content){
		 
			for(int i=0;i< receiver.blockedUsers.size(); i++) {

				if(receiver.blockedUsers.get(i).getUsername().equals(sender.getUsername()) && receiver.blockedUsers.getBool(i)== false) {
					flag=true;
					break;
				}	
			}
			
			if(flag) {
				System.out.println("This user has blocked you ! Cannot send messages");
			}
			else {
				for(int i=0;i<receiver.followers.size();i++) {
					
					if(receiver.followers.get(i).equals(sender.getUsername())) {
						
						 this.messageId=messageId;
						 this.sender=sender;
						 this.receiver=receiver;
						 this.content=content;
						 
						 // The message object created here is added to the array in the account class with this keyword
						 
						 sender.addOutgoing_messages(this);
						 receiver.addIncoming_messages(this);

						 history.add("Sending a message to "+ receiver.getUsername());
						 System.out.println("Sending a message to "+ receiver.getUsername());
						 flag2=true;
						 break;
					}	
				}
				if(!flag2)
					System.out.println("Follow before you can send a message !!");
			} 
	 }
	 
	 // Setter Getters
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public Account getSenderName() {
		return sender;
	}
	public void setSenderName(Account sender) {
		this.sender = sender;
	}
	public Account getReceiverName() {
		return receiver;
	}
	public void setReceiverName(Account receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
