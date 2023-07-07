package hw7;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class QuickSort {
    private myMap originalMap;
    private myMap sortedMap;
    private String[] aux;
    private long duration;

    /** constructor */
    QuickSort(myMap map){
        originalMap = map;
        sortedMap = new myMap();

        /** we add key values to aux */
        aux = originalMap.map.keySet().toArray(new String[0]);

        /** We sort the aux array containing these keys with the quick sort algorithm. 
         * During the sorting process, we compare according to the count field of Info objects.
         */
        duration=0;
        long startTime = System.nanoTime();
        // for(int i=0;i<10000;i++)
        quickSrt(aux, originalMap.map, 0, aux.length - 1);
        long endTime = System.nanoTime();
        duration = endTime - startTime;
        //duration=duration/10000;

        /** Using sorted keys, we fill the sortedStructure */
        for (String key : aux) {
            sortedMap.map.put(key, originalMap.map.get(key));
        }
    }
    
    /**This function is the main function that implements the quick sort algorithm on the array.
     * @param keys key information from aux
     * @param map original map
     * @param low starting index
     * @param high ending index
     */
    public static void quickSrt(String[] keys, LinkedHashMap<String, info> map, int beginindex, int endindex) {
        if (beginindex >= endindex) 
           return;
        
        String pivot = keys[endindex];
        int j= beginindex-1;
        
        for(int i= beginindex;i<endindex;i++) {
        	if(map.get(keys[i]).getCount() <= map.get(pivot).getCount()) {
        		j++;
        		 String temp = keys[j];
                 keys[j] = keys[i];
                 keys[i] = temp;
        	}
        }
        j++;
        String temp = keys[endindex];
        keys[endindex] = keys[j];
        keys[j] = temp;
        quickSrt(keys,map,beginindex,j-1);
        quickSrt(keys,map,j+1,endindex);
    }

    /** print function use map entry*/
    public void print() {
        System.out.println("\nThe Quick sort map:");
        for (Entry<String, info> entry : sortedMap.map.entrySet()) 
            System.out.println("Letter: " + entry.getKey() + ", Count: " + entry.getValue().getCount()+" Words: " + entry.getValue().words);
        System.out.println("Quick sort duration: "+duration);
    }
}
