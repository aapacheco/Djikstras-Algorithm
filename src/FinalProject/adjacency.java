package FinalProject;

import java.util.*;
import java.io.*;
import net.datastructures.ArrayList;


public class adjacency {
	
	//converts the index "in" to a node key stored within String x[]
	public static String intToStr(int in) {
		String[] x = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","Z",};
		if(in < x.length & in >= 0) {
			return x[in];
		}
		return null;
	}

private static final LinkedHashMap<String, LinkedHashMap<String, Integer>> myMatrix = new LinkedHashMap<>();

	//finds the shortest path to the destination node by using the directDistance of each node to the destination node
	public static directDistanceNode[] getAdjacentNodes(String x) {
		LinkedHashMap<String, Integer> adj = myMatrix.get(x);
		int numElements = adj.size();
		directDistanceNode[] ddArray = new directDistanceNode[numElements];
		Set<Map.Entry<String,Integer>> set = adj.entrySet();
		int i = 0;
		for(Map.Entry<String,Integer> vals : set) {
			String key = vals.getKey();
			directDistanceNode ddn = directDistance.get(key);
			ddArray[i++] = ddn;
		}
		Arrays.sort(ddArray);
		return ddArray;
	}
	
	//finds the shortest path to the destination node by using the weighted mappings of the adjacencies between the nodes
	public static directDistanceNode[] getAdjacentNodesWeighted(String x) {
		LinkedHashMap<String,Integer> adj = myMatrix.get(x);
		int numElements = adj.size();
		directDistanceNode[] ddArray = new directDistanceNode[numElements];
		
		Set<Map.Entry<String,Integer>> set = adj.entrySet();
		int i = 0;
		for(Map.Entry<String,Integer> vals :set) {
			String key = vals.getKey();
			directDistanceNode dd = directDistance.get(key);
			Integer val = getDistance(x,key);
			val += dd.getDistance();
			directDistanceNode ddn = new directDistanceNode(key,val);
			ddArray[i++] = ddn;
		}
		Arrays.sort(ddArray);
		return ddArray;
	}
	
	//returns the distance between a and b
	public static Integer getDistance(String a, String b) {
		LinkedHashMap<String,Integer> hash = myMatrix.get(a);
		Integer val = hash.get(b);
		if(val == null)
			return 0;
		return val;
	}
	
	
	//returns the total distance of the from START to the destination
	public static String getTotalDistance(String start, LinkedHashMap<String,Integer> pathMap) {
		Set<Map.Entry<String, Integer>> st;
		st = pathMap.entrySet();
		boolean first = true;
		String str = "";
		Integer sum = 0;
		String prev = start;
		int n = 0;
		for(Map.Entry<String, Integer> me:st) {
			String k = me.getKey();
			Integer i = getDistance(prev, k);
			if(!first) {
				if(!str.equals("")) {
					str += "+";
				}
				str += i;
				sum += i;
				n++;
			} else {
				first = false;
			}
			prev = k;
		}
		if(n>1) {
			str += "=";
			str += sum;
		}
		return str;
		
	}
	
	//initializes a HashMap with null values as the weights (distances) between each token (key)
	private static void initHashMap(String[] tokens) {
		for(String token:tokens) {
			myMatrix.put(token, null);
		}
	}
	
	//adds a node with a key, value entry to the LinkedHashMap
	private static void addToHashMap(String[] tokens) {
		String node = tokens[0];
		LinkedHashMap<String,Integer> hash = myMatrix.get(node);
		if(hash == null) {
			hash = new LinkedHashMap<>();
			myMatrix.put(node, hash);
		}
		for(int i = 1; i<tokens.length; i++) {
			Integer val = Integer.parseInt(tokens[i]);
			String key = intToStr(i-1);
			if(val != 0)
				hash.put(key, val);
			}
		}
	
	
	//prints out the key:value pairs for each entry in the LinkedHashMap
	public static void print() {
		Set<Map.Entry<String, LinkedHashMap<String, Integer>>> st = myMatrix.entrySet();
		for(Map.Entry<String, LinkedHashMap<String,Integer>> me:st) {
			LinkedHashMap<String,Integer> val = me.getValue();
			String key = me.getKey();
			System.out.println(key + ":");
			System.out.println("\t");
			if(val != null) {
				Set<Map.Entry<String,Integer>> set2 = val.entrySet();
				for(Map.Entry<String,Integer> vals : set2) {
					System.out.print(vals.getKey() + ":" + vals.getValue() + ": ");
					}
				}
			System.out.println();
			}
		System.out.println();
		}
	
	//prints out only the adjacent nodes for the selected entry of the LinkedHashMap
	public static void printAdjacentNodes(String currentNode, directDistanceNode[] adj, int algorithm){
		System.out.print("\tAdjacent Nodes: ");
		for(directDistanceNode y:adj) 
			System.out.print(y.getName() + ", ");
		System.out.println();
		if(algorithm == 1 )
			for(directDistanceNode y: adj)
				y.print();
		else
			for(directDistanceNode y:adj)
				y.printWithWeight(currentNode);
	}
	
	
	//reads the text file and stores the weights along with each adjacency into the LinkedHashMap "myMatrix"
	public static void init (String filename) {
		try {
			FileReader f = new FileReader(filename);
			BufferedReader in = new BufferedReader(f);
			Scanner scan = new Scanner(in);
			boolean lineOne = true;
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] tokens = line.split("\\s+");
				if(lineOne) {
					initHashMap(tokens);
					lineOne = false;
					continue;
				}
				addToHashMap(tokens);
			}
			scan.close();
			f.close();
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
