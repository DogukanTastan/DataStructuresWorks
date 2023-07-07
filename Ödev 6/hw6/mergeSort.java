package hw6;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class mergeSort {
	private myMap originalMap;
	private myMap sortedMap;
	private String[] aux;
	
	/** constructor */
	mergeSort(myMap map){
		originalMap=map;
		sortedMap=new myMap();
		
		/** we add key values to aux */
		aux = originalMap.map.keySet().toArray(new String[0]);
		
		/** We sort the aux array containing these keys with the merge sort algorithm. 
		 * During the sorting process, we compare according to the count field of Info objects.
		 */
		mergeSrt(aux, originalMap.map, 0, aux.length - 1);
		
		/** Using sorted keys, we fill the sortedStructure */
        for (String key : aux) {
            sortedMap.map.put(key, originalMap.map.get(key));
        }
	}
	
	/**This function is the main function that implements the merge sort algorithm on the array. Divide the array into two parts 
	 * and applies merge sort again on both parts. Finally, it merges both parts with the merge() function.
	 * @param keys key information from aux
	 * @param map original map
	 * @param left started index
	 * @param right last index length -1 
	 */
    public static void mergeSrt(String[] keys, LinkedHashMap<String, info> map, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSrt(keys, map, left, middle);
            mergeSrt(keys, map, middle + 1, right);
            merge(keys, map, left, middle, right);
        }
    }

    /** The merge function takes two ordered sub-arrays (left and right) and compares them to create a single ordered array */
    public static void merge(String[] keys, LinkedHashMap<String, info> map, int left, int middle, int right) {
    	
    	/**By defining variables i and j, we determine the starting indexes of the left and right subarrays. 
    	 * i represents the left subarray and j represents the right subarray.*/
        int i = left, j = middle + 1;

        while (i <= middle && j <= right) {
        	/** If the count of the element in the left subarray is the count of the element in the right subarray is less than or equal to i, 
        	 * we increment i by 1 because the element in the left subarray is already in the correct order.
        	 */
            if (map.get(keys[i]).getCount() <= map.get(keys[j]).getCount()) {
                i++;
            } else {
            	/**If the count value of the element in the left subarray is greater than the count value of the element in the 
            	 * right subarray, we move the element in the right subarray to the appropriate position:
            	 */
                String tempKey = keys[j];
                int tempIndex = j;

                while (tempIndex > i) {
                    keys[tempIndex] = keys[tempIndex - 1];
                    tempIndex--;
                }

                keys[i] = tempKey;
                /** When the process of placing the elements in the correct position is complete, We continue processing the next elements
                 *in the left and right subarrays by incrementing i, middle and j by 1 each.
                 */
                i++;
                middle++;
                j++;
            }
        }
    }
    /** print function use map entry*/
    public void print() {
		System.out.println("\nThe sorted map:");
		for (Entry<String, info> entry : sortedMap.map.entrySet()) 
            System.out.println("Letter: " + entry.getKey() + ", Count: " + entry.getValue().getCount()+" Words: " + entry.getValue().words);
	}
}
