package hw8;
import java.util.*;

/** inner class for keep the location data */
class Location {
    int y;
    int x;

    Location(int y, int x) {
        this.y = y;
        this.x = x;
    }
    Location(String[] a) {
        this.y = Integer.parseInt(a[0]);
        this.x = Integer.parseInt(a[1]);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        Location c = (Location) obj;
        return this.x == c.x && this.y == c.y;
    }

    /**  allows different Location objects to have different hash codes */
    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}


public class CSE222Graph {
	
    private HashMap<Location, List<Location>> adjList;
    protected CSE222Map cse222map;
    private int[][] map;
    
    CSE222Graph(CSE222Map mapObject) {
    	
    	cse222map=mapObject;
    	
        adjList = new HashMap<>();
        map = mapObject.getMap();
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        for (int y=0;y<map.length; y++) {
            for (int x=0;x< map.length; x++) {
                if (map[y][x]==0) {
                    Location node = new Location(y, x);
                    List<Location> neighbors = new ArrayList<>();
                    for (int[] direction : directions) {
                        int newY=y+direction[0];
                        int newX =x+direction[1];
                        if (newY >= 0 && newY < map.length && newX >= 0 && newX < map.length && map[newY][newX] == 0) {
                            neighbors.add(new Location(newY, newX));
                        }
                    }
                    adjList.put(node, neighbors);
                }
            }
        }
    }

    public List<Location> getNeighbors(Location node) {
        return adjList.get(node);
    }
    public int[][] getMap(){
    	return map;
    }
}