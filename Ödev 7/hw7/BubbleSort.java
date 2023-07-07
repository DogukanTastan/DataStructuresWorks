package hw7;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class BubbleSort {
    private myMap originalMap;
    private myMap sortedMap;
    private String[] aux;
    private long duration;

    /** constructor */
    BubbleSort(myMap map){
        originalMap = map;
        sortedMap = new myMap();

        /** we add key values to aux */
        aux = originalMap.map.keySet().toArray(new String[0]);

        /** We sort the aux array containing these keys with the bubble sort algorithm. 
         * During the sorting process, we compare according to the count field of Info objects.
         */
        duration=0;
        long startTime = System.nanoTime();
       // for(int i=0;i<10000;i++)
        bubbleSrt(aux, originalMap.map);
        long endTime = System.nanoTime();
        duration = endTime - startTime;
       // duration=duration/10000;

        /** Using sorted keys, we fill the sortedStructure */
        for (String key : aux) {
            sortedMap.map.put(key, originalMap.map.get(key));
        }
    }
    
    /**This function is the main function that implements the bubble sort algorithm on the array.
     * @param keys key information from aux
     * @param map original map
     */
    public static void bubbleSrt(String[] keys, LinkedHashMap<String, info> map) {
        int n = keys.length;
        int nextSwap = n;

        while(nextSwap!=0)
        {
            int lastSwap = nextSwap;
            nextSwap = 0;
            for (int i = 1; i < lastSwap; i++) {
                if (map.get(keys[i - 1]).getCount() > map.get(keys[i]).getCount()) {
                    String temp = keys[i -1]; // swap operation
                    keys[i-1] = keys[i];
                    keys[i] = temp;
                    nextSwap = i;
                }
            }
        }
    }

    /** print function use map entry*/
    public void print() {
        System.out.println("\nThe Bubble Sort map:");
        for (Entry<String, info> entry : sortedMap.map.entrySet()) 
            System.out.println("Letter: " + entry.getKey() + ", Count: " + entry.getValue().getCount()+" Words: " + entry.getValue().words);
        System.out.println("Bubble sort duration: "+duration);
    }
}
