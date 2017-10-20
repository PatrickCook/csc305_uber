package pcook01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Testing suite for Uber
 * Reads a text file and runs simulations of each ride
 * @author Patrick Cook
 *
 */
public class UberApp {
	public static ArrayList<Passenger> passengers = new ArrayList<>();
	
	public static void main(String[] args) {
		initializeUber();
		
		for (Iterator<Passenger> i = passengers.iterator(); i.hasNext(); ) {
			Passenger pass = (Passenger)i.next();
			Uber.requestRide(pass, getRandLocation());
		}
		
		Uber.outputUberHistory();
	}
	
	/**
	 * Responsible for reading input file and adding users
	 */
	public static void initializeUber() {
	    String fileName = "src/pcook01/input.txt";
	    String line = null;
	    String pieces[];

	    try {
	        FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        /* Read each line of the text file */
	        while((line = bufferedReader.readLine()) != null) {
	            pieces = line.split(" ");
	            
	            /* Ensure enough info is provided */
	            if (pieces.length != 4) {
	            	continue;
	            }
	            
	            /* Extract user information */
	            String first = pieces[1];
            	String last = pieces[2];
            	double initialBal = Double.parseDouble(pieces[3]);
            	
            	/* Create either a driver or passenger */
	            if (pieces[0].equals("D")) 
	            {
	            	Uber.addUberDriver(new Driver(first, last, initialBal, getRandLocation()));
	            	
	            } 
	            else if (pieces[0].equals("P")) 
	            {
	            	Passenger newPas = new Passenger(first, last, initialBal, getRandLocation());
	            	Uber.addUberUser(newPas);
	            	passengers.add(newPas);
	            } 
	            else 
	            {
	            	System.out.println("Invalid user type provided.");
	            }
	        }   
	        
	        bufferedReader.close();    
	        
	    } catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + fileName + "'");                
	    } catch(IOException ex) {
	        System.out.println("Error reading file '" + fileName + "'");                  
	    }
	}
	
	/**
	 * Helper function to return random location for requesting a ride
	 * @return - Random location within the UberMap bounds
	 */
	public static Location getRandLocation() {
		int xCord = (int) (Math.random() * UberMap.MAPSIZE_X);
		int yCord = (int) (Math.random() * UberMap.MAPSIZE_Y);
		
		return new Location(xCord, yCord);
	}
}

