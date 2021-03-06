import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.LargeMapImageUtils;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.GeoRSSReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.ui.BarScaleUI;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import de.fhpotsdam.unfolding.data.Feature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import de.fhpotsdam.unfolding.marker.SimplePointMarker;

import java.util.Scanner; // Import the Scanner class to read text files
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Hello Unfolding World.
 * 
 * Download the distribution with examples for many more examples and features.
 * 
 * Este pentru crearea hartii, configurarea ei si crearea marker-ilor
 * 
 */


public class CopyOfHelloUnfoldingWorld extends PApplet {

	
	UnfoldingMap map;
	
	/**
	 * 
	 * Configurarea hartii
	 * 
	 * 
	 */
	public void setup() {
		 
		// setting map to fullscreen
		
		Dimension size2= Toolkit.getDefaultToolkit().getScreenSize();
		 // width will store the width of the screen
        int width = (int)size2.getWidth();
        
        // height will store the height of the screen
        int height = (int)size2.getHeight();
		
		
		size(width, height, OPENGL);
		
		//--------------------------------------------------------
		
		
		
		
		
		
		
		map = new UnfoldingMap(this,new Microsoft.RoadProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		//map.zoomAndPanTo(10, new Location(52.5f, 13.4f));
		map.zoomAndPanTo(15, new Location(47.165119371787895, 27.58693137042947)); // Iasi
	  	//Location berlinLocation = new Location(47.15417869034799,27.59602546691894);
		
		     
		// Create point markers for locations
	//	SimplePointMarker berlinMarker = new SimplePointMarker(berlinLocation);
	//	SimplePointMarker dublinMarker = new SimplePointMarker(dublinLocation);
		     
		// Add markers to the map
		//map.addMarkers(berlinMarker, dublinMarker);
		
		
//------------------------------------------------
//Count file lines
		
		
		BufferedReader counter;
		int lines = 0;
		try {
			counter = new BufferedReader(new FileReader(
					"in.txt"));
			
			while (counter.readLine() != null) 
			{
				lines++;
			}
			counter.close();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("In.txt has "+lines+" lines");
		
//----------------------------------------------	
//verify if in.txt is ok -> case text in file
		
		BufferedReader br2;
		String line;
		int lineCounter=0;
		
		try {
			br2 = new BufferedReader(new FileReader(
					"in.txt"));
			while ((line = br2.readLine()) != null) {
				lineCounter++;
				if (line.matches(".*[a-z].*")) 
					{ 
				    	System.out.println("Found text at line "+lineCounter);
					}
				
				}
			
			br2.close();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
//----------------------------------------------
//verify if in.txt is ok -> case odd , must be even
		
		
		if ((lines % 2) != 0)
		{
			System.out.println("odd(impar) number of lines");
		}

	
		
		
//----------------------------------------------
// creating markers
		
		

		double p1,p2;
		int marker_i=0;
		SimplePointMarker markerArray[]=new SimplePointMarker[100];  // array with objects
		try {
		      File myObj = new File("in.txt");
		      Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		    	  
		    	  String data = myReader.nextLine();
		    	  p1=Double.parseDouble(data); 
		        String data2 = myReader.nextLine();
		        p2=Double.parseDouble(data2);
		        
		       Location loc = new Location(p1,p2); 
		        SimplePointMarker locmarker = new SimplePointMarker(loc);
		        
		        markerArray[marker_i]=locmarker;             // array with objects
		        marker_i++;
		       // map.addMarkers(locmarker);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
//----------------------------------------------
		
// Markers on map                               //modify colors if alot of markers exists !!! (max value is 255)
	int marker_color_changer1=147;
	int marker_color_changer2=0;	
	int marker_color_changer3=50;
	for(int i=0;i<marker_i;i++)
	{
		markerArray[i].setColor(color(255,0, 0, 20));
		markerArray[i].setStrokeColor(color(marker_color_changer1,marker_color_changer2, marker_color_changer3)); //different colors
		markerArray[i].setStrokeWeight(5);
		marker_color_changer1=marker_color_changer1-30;
		marker_color_changer2=marker_color_changer2+30;
		marker_color_changer3=marker_color_changer3+40;
		map.addMarkers(markerArray[i]);
	}
		
		
		
		
//----------------------------------------------		
		
// Markers Legend
	
	
		
	
	
	
//----------------------------------------------		
		MapUtils.createDefaultEventDispatcher(this, map);
	}

// button sreenshot -----------------------------------------------------	
	
	/**
	 * 
	 * Afisarea butoanelor
	 * 
	 */
	public void drawbuttons(){
		
		 fill(81, 166, 31);
		    rect(15, 10, 210, 25);
		    fill(255, 255, 255);
		    textSize(16);
		    text("PRESS S FOR SCREENSHOT", 19, 29);
	}
	/**
	 * 
	 * Afisarea legendei
	 * 
	 */
	public void drawLegend(){
		
		 fill(255, 255, 255);  
		    rect(15, 40, 280, 200);   // increase value 70 if more markers exists
		   
		    fill(137, 0, 50);
		    textSize(16);
		    text("@ ", 19, 59);
		    fill(1, 1, 1);
		    text("- Automatica si Calculatoare",35,59);
		    
		    fill(117,30, 90);
		    text("@ ", 19, 79);
		    fill(1, 1, 1);
		    text("- Mecanica",35,79);
		    
		    fill(87, 60, 130);
		    text("@ ", 19, 99);
		    fill(1, 1, 1);
		    text("- IEEA",35,99);
		    
		    fill(57, 90, 160);
		    text("@ ", 19, 119);
		    fill(1, 1, 1);
		    text("- Primaria Municipiului Iasi",35,119);
		    
		    
		    fill(27, 120, 200);
		    text("@ ", 19, 139);
		    fill(1, 1, 1);
		    text("- Facultatea de Informatica",35,139);
		    
		    fill(0, 150, 240);
		    text("@ ", 19, 159);
		    fill(1, 1, 1);
		    text("- Palatul Culturii",35,159);
		    
		    fill(0, 180, 255);
		    text("@ ", 19, 179);
		    fill(1, 1, 1);
		    text("- Aeroportul International Iasi",35,179);
		    
		    fill(0, 210, 255);
		    text("@ ", 19, 199);
		    fill(1, 1, 1);
		    text("- Iulius Mall",35,199);
		    
	}

	/**
	 * 
	 * Pentru screenshot si salvarea acestuia in directorul ales
	 * @param event apasarea butonului
	 * 
	 */
	
	public void keyPressed(KeyEvent event)
	{
		char ch = event.getKeyChar();   //character 
		
		if (ch == 's' || ch =='S' ) {
		try {
            Thread.sleep(120);
            Robot r = new Robot();
  
            // It saves screenshot to desired path
            String path = "C://Users//Andrei//Desktop//Shot.jpg";
  
            // Used to get ScreenSize and capture image
            Rectangle capture = 
            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "jpg", new File(path));
            System.out.println("Screenshot saved");
           
        }
        catch (AWTException | IOException | InterruptedException ex) {
            System.out.println(ex);
        }
		
		}
		
		
	}
	
	
// --------------------------------------------------------------------	
	
	
	/**
	 * 
	 * Functia "main"
	 * 
	 */
	public void draw() {
		
		//background(0);
		map.draw();
		drawbuttons();
		drawLegend();
			
		
	}
		
	
	
	
	

}
