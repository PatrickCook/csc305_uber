package pcook01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class UberApp {
	public static ArrayList<Passenger> passengers = new ArrayList<>();
	
	public static void main(String[] args) {
		initializeUber();
		
		for (Iterator<Passenger> i = passengers.iterator(); i.hasNext(); ) {
			Passenger pass = (Passenger)i.next();
			pass.requestRide(getRandLocation());
		}
		
		//Uber.outputUberHistory();
	}
	
	public static void initializeUber() {
	    String fileName = "src/pcook01/input.txt";
	    String line = null;
	    String pieces[];

	    try {
	        FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	            pieces = line.split(" ");
	            
	            if (pieces.length != 6) {
	            	continue;
	            }
	            
	            String first = pieces[1];
            	String last = pieces[2];
            	double initialBal = Double.parseDouble(pieces[3]);
            	int xCord = Integer.parseInt(pieces[4]);
            	int yCord = Integer.parseInt(pieces[5]);
            	
	            if (pieces[0].equals("D")) 
	            {
	            	Uber.addUberDriver(new Driver(first, last, initialBal, new Location(xCord, yCord)));
	            	
	            } 
	            else if (pieces[0].equals("P")) 
	            {
	            	Passenger newPas = new Passenger(first, last, initialBal, new Location(xCord, yCord));
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
	
	public static Location getRandLocation() {
		int xCord = (int) (Math.random() * UberMap.MAPSIZE_X);
		int yCord = (int) (Math.random() * UberMap.MAPSIZE_Y);
		
		return new Location(xCord, yCord);
	}
}

