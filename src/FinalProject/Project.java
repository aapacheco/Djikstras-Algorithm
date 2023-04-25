package FinalProject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.*;

public class Project {
	
	
	//prints out the current path 
	private static void printPathMap(LinkedHashMap<String, Integer> pathMap) {
		System.out.println("PathMap = ");
		Set<Map.Entry<String, Integer>> st = pathMap.entrySet();
		for(Map.Entry<String,Integer> me:st) {
			String k = me.getKey();
			Integer d = me.getValue();
			System.out.println(k + " ");
		}
		System.out.println();
	}
	
	//composes the shortest path
	private static boolean getNext(LinkedHashMap<String,Integer> pathMap, String shortestPathStr, String x,
			String end, directDistanceNode[] adjacent, int index, int algorithm) {
		//printPathMap(pathMap); 
		if(x.equals(end)) {
			return true;
		}
		boolean print = false;
		if(shortestPathStr.equals("")) {
			shortestPathStr = "Shortest Path: ";
		} else {
			shortestPathStr += "-->";
			print = true;
		}
		String nextNode = adjacent[index].getName();
		shortestPathStr += nextNode;
		pathMap.put(nextNode, 0);
		
		if(print) {
			System.out.println("\tCurrent Node = " + x);
			adjacency.printAdjacentNodes(x, adjacent, algorithm);
			System.out.println("\t" + nextNode + " is selected");
			if(nextNode.equals("Z")) {
				System.out.println("\t"+nextNode + " is the destination. Stop.");
			}
			System.out.println("\t" + shortestPathStr);
			System.out.println("\tShortest Path Length: " + adjacency.getTotalDistance(x, pathMap));
			System.out.println();
		}
		directDistanceNode[] adj = null;
		if(algorithm == 1) {
			adj = adjacency.getAdjacentNodes(nextNode);
		} else {
			adj = adjacency.getAdjacentNodesWeighted(nextNode);
		}
		if(adj.length != 0) {
			for(int i = 0; i < adj.length; i++) {
				String k = adj[i].getName();
				if(pathMap.containsKey(k)) {
					continue;
				}
				if(getNext(pathMap,shortestPathStr,nextNode,end,adj,i,algorithm)) {
					return true;
				}
			}
		}
		pathMap.remove(nextNode);
		System.out.println("\tDead End - Node Removal Process Initiated On Node: " + nextNode);
		System.out.println("\tNode Removal Process Complete.");
		System.out.println("\tReverting back to - " + x + "\n");
		return false;
		}
	
	
	//prints out if 'finding the shortest path' was successful
	private static void findPath(String start, String end, int algorithm) {
		System.out.println("Algorithm " + algorithm + " has been selected.\n");
		String shortestPathStr = "";
		LinkedHashMap<String, Integer> pathMap = new LinkedHashMap<>();
		
		directDistanceNode ddn = directDistance.get(start);
		directDistanceNode[] adj = {ddn};
		pathMap.put(start, 0);
		boolean found = false;
		
		if(getNext(pathMap, shortestPathStr, start, end, adj, 0, algorithm)) {
			System.out.println("Path to " + end + " Computed Successfully. - Exiting.");
			found = true;
		} else {
			System.out.println("Path to " + end + " Computing Failed. - Exiting.");
		}
	}
	
	
	//initializes the two text files for reading
	public static void init() {
		adjacency.init("graph_input.txt");
		directDistance.init("direct_distance.txt");
	}
	
	
	
	public static void main(String[] args) {
		String[] nodesToTest = {"G"};
		init();
		String endNode = "Z";
		for (String x: nodesToTest) {
			findPath(x, endNode, 1);
			findPath(x, endNode, 2);
		}
	}

}
