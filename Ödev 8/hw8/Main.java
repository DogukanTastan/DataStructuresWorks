package hw8;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		String InputFile = "map02.txt"; // Dikkatli ol verilen dosya kaynak kodla aynı konumda olmalı !
		CSE222Map Map = new CSE222Map (InputFile);
		String[] startCoordinates=Map.getStartLocations();
	    String[] endCoordinates=Map.getEndLocations();
	   
		CSE222Graph Graph = new CSE222Graph(Map);
		
	        
		CSE222Dijkstra Dijkstra = new CSE222Dijkstra (Graph,startCoordinates,endCoordinates);
		List dijPath=Dijkstra.findPath();
		
		CSE222BFS BFS = new CSE222BFS (Graph,startCoordinates,endCoordinates);
		List BFSPath = BFS.findPath();
		
		try {
			Map.convertToPNG();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Green Dijkstra and Blue BFS
		Map.drawLine(dijPath,InputFile+"_Result.png",0); // 0 for Dijkstra, 1 for BFS for different colors
		Map.drawLine(BFSPath,InputFile+"_Result.png",1); // 0 for Dijkstra ,1 for BFS for different colors
		
	}

}
