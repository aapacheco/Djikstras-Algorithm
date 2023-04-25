package FinalProject;

public class directDistanceNode implements Comparable<directDistanceNode> {
	private final String name;
	private final Integer distance;
	
	
	//creates a new directDistanceNode with the specified name (key) and distance(weight)
	public directDistanceNode(String name, Integer distance) {
		this.name = name;
		this.distance = distance;
	}
	
	
	//returns the distance
	public Integer getDistance() {return distance;}
	
	//returns the name
	public String getName() {return name;}
	
	
	//necessary to ensure that the built in sorting function for arrays can be used
	@Override
	public int compareTo(directDistanceNode a) {
		return (this.distance-a.distance);
	}
	
	
	//print function used for printing the name and distance of an entry
	public void print() {
		System.out.println("\t" + name + ":dd(" + name + ") = " + distance);
	}
	
	
	//used for running Algorithm 2, printing with the distance (weight)
	public void printWithWeight(String currentNode) {
		int adjDistance = adjacency.getDistance(currentNode, name);
		int newd = distance - adjDistance;
		int sum = distance;
		System.out.println("\t" + name + ":w(" + currentNode + "," + name + ") + dd(" +
	name + ") = " + adjDistance + "+" + newd + "=" + sum);
	}

}
