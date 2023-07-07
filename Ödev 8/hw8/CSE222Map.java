package hw8;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

public class CSE222Map {

	String[] startLocations;
    String[] endLocations;
    int[][] map;
    private BufferedImage image;
    private int scale;  // the size of each cell in pixels
    String fileName;
    
    /** It reads the file and converts it to a graph. */
    public CSE222Map(String inputFile) {
    	
    	fileName=inputFile;
    	
        List<String> lines = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        startLocations = lines.get(0).split(",");
        endLocations = lines.get(1).split(",");
        
        /** Initializes the map variable as a 2D int array with the number of lines lines.size() - 2 
         * (since the first two lines are start and end positions). */
        map = new int[lines.size()-2][];

        for (int i = 2; i < lines.size(); i++) {
            String[] row = lines.get(i).split(",");
            map[i-2] = new int[row.length];
            for (int j = 0; j < row.length; j++) {
                int value = Integer.parseInt(row[j]);
                map[i-2][j] = value == -1 ? 1 : value;
            }
        }
    }
    
	 public  void convertToPNG() throws IOException {
		 
		String filename=fileName+"_Result.png";
        int width = map[0].length;
        int height = map.length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR); /** I chose this one (TYPE_INT_BGR) to make the colors look better.*/
        WritableRaster raster = (WritableRaster) image.getData();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int value = map[y][x] == 0 ? 255 : 0;  // Map 0s to white (255) and 1s to black (0)
                raster.setSample(x, y, 0, value);
            }
        }

        image.setData(raster);
        ImageIO.write(image, "PNG", new File(filename));
	 }
	 
	 public void drawLine(List<Location> path, String imagePath,int val) {
	    BufferedImage image;
	    try {
	        image = ImageIO.read(new File(imagePath));
	    } 
	    catch (IOException e1) {
	        e1.printStackTrace();
	        return;
	    }

	    Graphics2D graphics = image.createGraphics();
	    
	    if(val==0)
	    	graphics.setColor(Color.GREEN);  //Dijkstra
	    else
	    	graphics.setColor(Color.BLUE); // bfs
	    
	    graphics.setStroke(new BasicStroke(3)); // set line thickness

	    for (int i = 0; i < path.size() - 1; i++) {
	        Location point1 = path.get(i);
	        Location point2 = path.get(i + 1);

	        // draw line from point1 to point2
	        graphics.drawLine(point1.x, point1.y, point2.x, point2.y);
	    }

	    graphics.dispose();

	    try {
	        // Overwrite the original image
	        ImageIO.write(image, "PNG", new File(imagePath));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    public String[] getStartLocations() {
        return startLocations;
    }

    public String[] getEndLocations() {
        return endLocations;
    }

    public int[][] getMap() {
        return map;
    }
}
