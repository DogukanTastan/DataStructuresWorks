package hw7;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class SelectionSort {
    private myMap originalMap;
    private myMap sortedMap;
    private String[] aux;
    private long duration;

    /** constructor */
    SelectionSort(myMap map){
        originalMap = map;
        sortedMap = new myMap();

        /** we add key values to aux */
        aux = originalMap.map.keySet().toArray(new String[0]);

        /** We sort the aux array containing these keys with the selection sort algorithm. 
         * During the sorting process, we compare according to the count field of Info objects.
         */
        duration=0;
        long startTime = System.nanoTime();
        //for(int i=0;i<10000;i++)
        selectionSrt(aux, originalMap.map);
        long endTime = System.nanoTime();
        duration = endTime - startTime;
       // duration=duration/10000;
       
        /** Using sorted keys, we fill the sortedStructure */
        for (String key : aux) {
            sortedMap.map.put(key, originalMap.map.get(key));
        }
    }

    /**This function is the main function that implements the selection sort algorithm on the array.
     * @param keys key information from aux
     * @param map original map
     */
    public static void selectionSrt(String[] keys, LinkedHashMap<String, info> map) {
        int n = keys.length;
        for (int i=0; i<n-1; i++) {
            int minIndex = i;
            for (int j=i+1;j<n; j++) {
                if (map.get(keys[j]).getCount() < map.get(keys[minIndex]).getCount()) {
                    minIndex = j;
                }
            }
            String temp = keys[minIndex];
            keys[minIndex] = keys[i];
            keys[i] = temp;
        }
    }

    /** print function use map entry*/
    public void print() {
        System.out.println("\nThe Selection sort map:");
        for (Entry<String, info> entry : sortedMap.map.entrySet()) 
            System.out.println("Letter: " + entry.getKey() + ", Count: " + entry.getValue().getCount()+" Words: " + entry.getValue().words);
        System.out.println("Selection sort duration: "+duration);
    }
}
