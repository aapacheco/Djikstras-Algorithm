package FinalProject;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class directDistance {
	public directDistance() {
		
	}
	
	//creates a new LinkedHashMap dd to store each key and node adjacencies read from the text file
	private static final LinkedHashMap<String, directDistanceNode> dd = new LinkedHashMap<>();
	
	//calls the directDistanceNode print function on the directDistanceNode d
	public static void print() {
		Set<Map.Entry<String, directDistanceNode>> st = dd.entrySet();
		for(Map.Entry<String, directDistanceNode> me:st) {
			directDistanceNode d = me.getValue();
			d.print();
		}
	}
	
	//gets the node "in"
	public static directDistanceNode get(String in) {
		return dd.get(in);
	}

	//reads the text file and stores the weights along with each adjacency into the LinkedHashMap "dd"
	public static void init(String file) {
		try {
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			Scanner scan = new Scanner(in);
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] tokens = line.split("\\s+");
				String k = tokens[0];
				Integer i = Integer.parseInt(tokens[1]);
				directDistanceNode d = new directDistanceNode(k,i);
				dd.put(k, d);
			}
			f.close();
			scan.close();
			print();
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File Not Found - Exiting.");
			System.out.println(fnfe);
		}
		catch(IOException ioe) {
			System.out.println("System Error Reading File - Exiting.");
		}
		catch(NumberFormatException nfe) {
			System.out.println("Bad Number: " + nfe + " - Exiting.");
		}
		catch(IllegalArgumentException iae) {
			System.out.println("Illegal Argument: " + iae + " - Exiting.");
		}
	}
}
