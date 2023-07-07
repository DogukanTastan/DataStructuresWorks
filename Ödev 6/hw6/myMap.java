package hw6;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class myMap {
	protected LinkedHashMap <String, info> map;
	private String str;
	
	myMap(String input){
		map=new LinkedHashMap<>();
		add(input);
	}
	myMap(){
		map=new LinkedHashMap<>();
	}
	
	public void add(String input) {
		
		String[] words = input.split(" ");
		int wordCounter=0;
		for(int i=0;i<input.length();i++) {
			
			if(input.charAt(i)==' ') {
				wordCounter++;
			}
			else {
				str="";
				str+=input.charAt(i);
				
				try {
					if(this.map != null && map.containsKey(str)) {
						map.get(str).words.add(words[wordCounter]);
						map.get(str).setCount(1);
					}
					else {
						info tempinfo= new info(words[wordCounter]);
						map.put(str,tempinfo);
					}
				}
				catch(NullPointerException e) {
					return;
				}
			}
		}
		print();
		mergeSort merge=new mergeSort(this);
		merge.print();
	}
	public void print() {
		System.out.println("\nThe original (unsorted) map:");
		for (Entry<String, info> entry : map.entrySet()) 
            System.out.println("Letter: " + entry.getKey() + ", Count: " + entry.getValue().getCount()+" Words: " + entry.getValue().words);
	}
}
