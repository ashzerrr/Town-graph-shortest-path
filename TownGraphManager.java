
package Pj6;




import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * 
 * @author Ashton Kabou
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{
	
	private Graph townGraph;
	
	/** Constructor that creates a new graph to work with */
	public TownGraphManager() {
		townGraph = new Graph();
	}

	
	/**
	 * Adds a road between two towns in the graph.
	 * 
	 * @param sourceTownName      the name of the source town
	 * @param destinationTownName the name of the destination town
	 * @param roadWeight          the weight of the road
	 * @param roadName            the name of the road
	 * @return true if the road was successfully added, false otherwise
	 */
	@Override
	public boolean addRoad(String sourceTownName, String destinationTownName, int roadWeight, String roadName) {
		Town sourceTown = getTown(sourceTownName);
		Town destinationTown = getTown(destinationTownName);
	
		if (sourceTown == null || destinationTown == null)
			return false;
	
		if (townGraph.containsEdge(sourceTown, destinationTown))
			return false;
	
		townGraph.addEdge(sourceTown, destinationTown, roadWeight, roadName);
	
		return true;
	}


	/**
	 * Returns the name of the road connecting the given source town and destination town.
	 *
	 * @param sourceTownName      the name of the source town
	 * @param destinationTownName the name of the destination town
	 * @return the name of the road connecting the source and destination towns, or an empty string if no road exists
	 */
	@Override
	public String getRoad(String sourceTownName, String destinationTownName) {
		String roadName = "";
		Town sourceTown = getTown(sourceTownName);
		Town destinationTown = getTown(destinationTownName);
	
		for (Road road : townGraph.edgeSet())
			if (road.contains(sourceTown) && road.contains(destinationTown))
				roadName = road.getName();
	
		return roadName;
	}
	
	
	/**
	 * Returns the road object that connects the given source town and destination town.
	 *
	 * @param sourceTownName      the name of the source town
	 * @param destinationTownName the name of the destination town
	 * @return the road object that connects the source and destination towns, or null if no road exists
	 */
	public Road getRoadR(String sourceTownName, String destinationTownName) {
		Town sourceTown = getTown(sourceTownName);
		Town destinationTown = getTown(destinationTownName);
	
		return townGraph.edgeSet().stream()
			.filter(road -> road.contains(sourceTown) && road.contains(destinationTown))
			.findFirst()
			.orElse(null);
	}

	
	/**
	 * Adds a town to the graph
	 * 
	 * @param town The town to add to the graph
	 * 
	 * @return True if the town was added, false if not
	 */
	@Override
	public boolean addTown(String town) {
		return townGraph.addVertex(new Town(town));
	}

	/** Gets a town based off of its name
	 * 
	 * @param townName the name of the town to get
	 * 
	 * @return The town with the name requested
	 */
	@Override
	public Town getTown(String townName) {
		Town gottenTown = null;
		for (Town town : townGraph.vertexSet())
			if (town.getName().equals(townName))
				gottenTown = town;
		return gottenTown;
	}

	/** Checks if the graph contains a certain town
	 * 
	 * @param name The name of the town
	 * 
	 * @return True if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String name) {
		if (getTown(name) == null)
			return false;
		else
			return true;
	}

	/** Checks if a road connection exists between two towns
	 * 
	 * @param town1 The first town of the road
	 * @param town2 The second town of the road
	 * 
	 * @return True if the road exists, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return townGraph.containsEdge(getTown(town1), getTown(town2));
	}

	/** Gets a list of roads in a sorted order
	 * 
	 * @return A list of the roads in sorted order
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roadNames = new ArrayList<>();
		for (Road road: townGraph.edgeSet())
			roadNames.add(road.getName());
		
		roadNames.sort(String.CASE_INSENSITIVE_ORDER);
		return roadNames;
	}

	/** Deletes a road connection between two towns
	 * 
	 * @param town1 The first town of the road
	 * @param town2 The second town of the road
	 * 
	 * @return True if the road was deleted, false otherwise
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Road roadGotten = getRoadR(town1, town2);
		townGraph.removeEdge(getTown(town1), getTown(town2), roadGotten.getWeight(), roadGotten.getName());
		return true;
	}

	/** Removes a town from the graph
	 * 
	 * @param name The name of the town
	 * 
	 * @return True if the town was removed, false if not
	 */
	@Override
	public boolean deleteTown(String name) {
		return townGraph.removeVertex(getTown(name));
	}

	/** Gets a list of all towns in sorted order
	 * 
	 * @return A list of all towns in sorted order
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> townNames = new ArrayList<>();
		for (Town town : townGraph.vertexSet())
			townNames.add(town.getName());
		
		townNames.sort(String.CASE_INSENSITIVE_ORDER);
		return townNames;
	}

	/** Gets the shortest path from town 1 and town2
	 * 
	 * @param town1 The source town
	 * @param town2 The destination town
	 * 
	 * @return a list of the instructions on how to get from town1 to town2
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return townGraph.shortestPath(getTown(town1), getTown(town2));
	}

	/** Populates a graph from a file
	 * 
	 * @param selectedFile The file to read from
	 * @throws FileNotFoundException If the file doesn't exist
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException{
		List<String> inList = new ArrayList<>();
		
		if (!selectedFile.exists())
			throw new FileNotFoundException();
		
		Scanner inFile = new Scanner(selectedFile);
		
		while (inFile.hasNextLine()) {
			inList.add(inFile.nextLine());
		}
		
		for (String line : inList) {
			String[] currentLine = line.split(";");
			int commaIndex = currentLine[0].indexOf(",");
			String roadName = currentLine[0].substring(0,commaIndex);
			String weight = currentLine[0].substring(commaIndex+1,currentLine[0].length());
			String source = currentLine[1];
			String destination = currentLine[2];
			
			addTown(source);
			addTown(destination);
			
			addRoad(source, destination, Integer.parseInt(weight), roadName);
		}
		
		inFile.close();
	}

}  

