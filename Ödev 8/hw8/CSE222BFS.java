package hw8;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSE222BFS {
    private CSE222Graph graph;
    private Location start;
    private Location end;
    private long duration;

    public CSE222BFS(CSE222Graph graph, String[] start, String[] end) {
        this.graph = graph;
        /** we assign the coordinates */
        this.start = new Location(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
        this.end = new Location(Integer.parseInt(end[0]), Integer.parseInt(end[1]));
    }

    public List<Location> findPath() {
    	
    	 duration=0;
         long startTime = System.nanoTime();
    	
    	
        HashMap<Location, Location> prev = new HashMap<>();
        Queue<Location> queue = new LinkedList<>();
        Set<Location> visited = new HashSet<>();

        queue.add(start);
        visited.add(start); /** mark start as visited immediately */
        
        /** applying bfs algorithm */
        while (!queue.isEmpty()) {
            Location current = queue.poll();

            if (current.equals(end)) 
                break;

            for (Location neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    prev.put(neighbor, current);
                    visited.add(neighbor); // mark neighbor as visited immediately
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
            FileWriter writer = new FileWriter("maxx_pathBFS.txt");
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
        System.out.println("BFS: duration: "+duration);
        System.out.println("BFS Path: "+pathCounter);
        return path;
        
    }

}
