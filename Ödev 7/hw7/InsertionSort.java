package hw7;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class InsertionSort {
    private myMap originalMap;
    private myMap sortedMap;
    private String[] aux;
    private long duration;

    /** constructor */
    InsertionSort(myMap map){
        originalMap = map;
        sortedMap = new myMap();

        /** we add key values to aux */
        aux = originalMap.map.keySet().toArray(new String[0]);

        /** We sort the aux array containing these keys with the insertion sort algorithm. 
         * During the sorting process, we compare according to the count field of Info objects.
         */
        duration=0;
        long startTime = System.nanoTime();
       // for(int i=0;i<10000;i++)
        insertionSrt(aux, originalMap.map);
        long endTime = System.nanoTime();
        duration = endTime - startTime;
        //duration=duration/10000;
        
        /** Using sorted keys, we fill the sortedStructure */
        for (String key : aux) {
            sortedMap.map.put(key, originalMap.map.get(key));
        }
    }

    /**This function is the main function that implements the insertion sort algorithm on the array.
     * @param keys key information from aux
     * @param map original map
     */
    public static void insertionSrt(String[] keys, LinkedHashMap<String, info> map) {
        int n = keys.length;
        for (int i = 1; i < n; ++i) {
            String key = keys[i];
            int j = i - 1;

            while (j >= 0 && map.get(keys[j]).getCount() > map.get(key).getCount()) {
                keys[j + 1] = keys[j];
                j = j - 1;
            }
            keys[j + 1] = key;
        }
    }

    /** print function use map entry*/
    public void print() {
        System.out.println("\nThe Insertion sort map:");
        for (Entry<String, info> entry : sortedMap.map.entrySet()) 
            System.out.println("Letter: " + entry.getKey() + ", Count: " + entry.getValue().getCount()+" Words: " + entry.getValue().words);
        System.out.println("Insertion sort duration: "+duration);
    }
}
