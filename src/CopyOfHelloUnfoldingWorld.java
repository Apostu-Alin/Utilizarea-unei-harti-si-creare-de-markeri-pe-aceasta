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

/**
 * Hello Unfolding World.
 * 
 * Download the distribution with examples for many more examples and features.
 */


//Comentariu de onoare



public class CopyOfHelloUnfoldingWorld extends PApplet {

	
	UnfoldingMap map;
	
	
	public void setup() {
		size(1200, 600, OPENGL);
		
		map = new UnfoldingMap(this,new Microsoft.RoadProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		//map.zoomAndPanTo(10, new Location(52.5f, 13.4f));
		map.zoomAndPanTo(15, new Location(47.1615341, 27.5836142)); // Iasi
	  
		//Location berlinLocation = new Location(47.15417869034799,27.59602546691894);
		
		     
		// Create point markers for locations
	//	SimplePointMarker berlinMarker = new SimplePointMarker(berlinLocation);
	//	SimplePointMarker dublinMarker = new SimplePointMarker(dublinLocation);
		     
		// Add markers to the map
		//map.addMarkers(berlinMarker, dublinMarker);
	
	//----------------------------------------------
	// Markers on map
			
			

			double p1,p2;
			
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
			        map.addMarkers(locmarker);
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
	//----------------------------------------------
	
	
	}	


	public void draw() {
		
		//background(0);
		map.draw();
		
		
		
		
	}

}
