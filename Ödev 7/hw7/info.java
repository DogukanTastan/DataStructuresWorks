package hw7;
import java.util.ArrayList;

public class info {
	private int count;
	protected ArrayList<String> words= new ArrayList<>();
	
	/** constructor */
	info(String word){
		count=1;
		words.add(word);
	}
	
	/** getter setter */
	public int getCount() {
		return count;
	}
	public void setCount(int set) {
		count+= set;
	}
}