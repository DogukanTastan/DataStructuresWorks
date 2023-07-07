package hw8;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSE222Dijkstra {
    private CSE222Graph graph;
    private Location start;
    private Location end;
    private long duration;

    public CSE222Dijkstra(CSE222Graph graph, String[] start, String[] end) {
        this.graph = graph;
        /** we assign the coordinates */
        this.start=new Location(start);
        this.end=new Location(end);
    }

    public List<Location> findPath() {
    	
    	 duration=0;
         long startTime = System.nanoTime();
    	
    	 if (graph.getMap()[start.y][start.x] == 1 || graph.getMap()[end.y][end.x] == 1) {
    	        System.out.println("No suitable path.");
    	        return Collections.emptyList();
    	    }
    	
        HashMap<Location, Location> prev = new HashMap<>();
        HashMap<Location, Integer> distances = new HashMap<>();
        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<Location> visited = new HashSet<>();

        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Location current = queue.poll();
            visited.add(current);

            if (current.equals(end))
                break;

            for (Location neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    int distance = distances.get(current) + 1;  // Assumes distance between each Location is 1

                    if (!distances.containsKey(neighbor) || distance < distances.get(neighbor)) {
                        distances.put(neighbor, distance);
                        prev.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Reconstruct the path from end to start
        LinkedList<Location> path = new LinkedList<>();
        Location step = end;

        if (prev.containsKey(step) || step.equals(start)) {
            while (step != null) {
                path.addFirst(step);
                step = prev.get(step);
            }
        }
        int pathCounter=0;
        // Write path to file
        try {
            FileWriter writer = new FileWriter("maxx_pathDji.txt");
            for (Location coord : path) {
                writer.write(coord.y + "," + coord.x + "\n");
                pathCounter++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Dijkstra: duration: "+duration);
        System.out.println("Dijkstra Path: "+pathCounter);
        return path;
    }

}
