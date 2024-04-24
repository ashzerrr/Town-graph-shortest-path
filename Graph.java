package Pj6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a graph data structure containing towns and roads, along with methods for manipulating them.
 * It also includes functionality for path finding algorithms.
 * Author: Ashton Kabou
 */
public class Graph implements GraphInterface<Town, Road> {

    private Set<Town> towns;
    private Set<Road> roads;
    private Set<Town> visited;
    private Set<Town> unvisited;
    private Map<Town, Town> previous;
    private Map<Town, Integer> distance;

    /**
     * Constructs a graph with empty sets for towns, roads, visited and unvisited nodes,
     * as well as maps for distances and previous nodes.
     */
    public Graph() {
        towns = new HashSet<>();
        roads = new HashSet<>();
        visited = new HashSet<>();
        unvisited = new HashSet<>();
        distance = new HashMap<>();
        previous = new HashMap<>();
    }

    /**
     * Retrieves the edge connecting two towns.
     *
     * @param sourceVertex      The first town of the edge
     * @param destinationVertex The second town of the edge
     * @return An edge (Road) connecting two towns
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        for (Road road : roads)
            if (road.contains(sourceVertex) && road.contains(destinationVertex))
                return road;

        return null;
    }

    /**
     * Adds an edge between two towns.
     *
     * @param sourceVertex      The first town of the edge
     * @param destinationVertex The second town of the edge
     * @param weight            The weight (in miles) of the edge (Road)
     * @param description       The name of the road
     * @return The edge added to the graph
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
        roads.add(newRoad);
        return newRoad;
    }

    /**
     * Adds a town (vertex) to the graph.
     *
     * @param v The town to add to the graph
     * @return True if the town was added, false if it was not added
     */
    @Override
    public boolean addVertex(Town v) {
        for (Town town : towns) {
            if (town.equals(v))
                return false;
        }
        towns.add(v);
        return true;
    }

    /**
     * Checks if an edge (Road) exists on the graph.
     *
     * @param sourceVertex      The first town of the Road
     * @param destinationVertex The second town of the Road
     * @return True if a road exists with both towns, false if not
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        for (Road road : roads) {
            if (road.contains(sourceVertex) && road.contains(destinationVertex))
                return true;
        }
        return false;
    }

    /**
     * Checks if a town is on the graph.
     *
     * @param v The town to check
     * @return True if the town is on the graph, false if not
     */
    @Override
    public boolean containsVertex(Town v) {
        for (Town town : towns)
            if (town.equals(v))
                return true;
        return false;
    }

    /**
     * Returns a copy of a set of roads.
     *
     * @return A set of roads that are in the graph
     */
    @Override
    public Set<Road> edgeSet() {
        Set<Road> copyOfRoads = new HashSet<>();

        for (Road road : roads)
            copyOfRoads.add(road);

        return copyOfRoads;
    }

    /**
     * Gets a set of the roads connected to a town.
     *
     * @param vertex The town that you want to see roads connected to
     * @return A set of Roads that the town is a part of
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        Set<Road> containsTown = new HashSet<>();
        for (Road road : roads) {
            if (road.contains(vertex))
                containsTown.add(road);
        }

        return containsTown;
    }

    /**
     * Removes a road between two towns.
     *
     * @param sourceVertex      The first town in the road you want removed
     * @param destinationVertex The second town in the road you want removed
     * @param weight            The weight of the road you want removed
     * @param description       The name of the road you want removed
     * @return The removed road
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road roadRemoved = null;
        for (Road road : roads) {
            if (road.contains(sourceVertex) && road.contains(destinationVertex))
                if (road.getWeight() == weight && road.getName().equals(description)) {
                    roadRemoved = road;
                    roads.remove(road);
                    break;
                }
        }

        return roadRemoved;
    }

    /**
     * Removes all roads connected to a town.
     *
     * @param v All roads connecting from or to town will be removed from the graph
     */
    private void removeConnectedRoads(Town v) {
        Set<Road> connectedRoads = edgesOf(v);

        for (Road road : connectedRoads) {
            removeEdge(road.getSource(), road.getDestination(), road.getWeight(), road.getName());
        }
    }

    /**
     * Removes a town from the graph.
     *
     * @param v Removes a town from the graph
     * @return True if the town was removed successfully
     */
    @Override
    public boolean removeVertex(Town v) {
        towns.remove(v);
        removeConnectedRoads(v);
        return true;
    }

    /**
     * Gets a set of towns on the graph.
     *
     * @return a copy of the set containing all towns on the graph
     */
    @Override
    public Set<Town> vertexSet() {
        Set<Town> copyOfTowns = new HashSet<>();

        for (Town town : towns)
            copyOfTowns.add(town);

        return copyOfTowns;
    }

  


	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> path = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);
		
		Town previousTown = destinationVertex;
		
		while(previousTown != null){
			Town currentTown = previousTown;
			previousTown = previous.get(previousTown);
			Road roadPath = getEdge(currentTown, previousTown);
			if (previousTown != null)
				path.add(previousTown.getName() + " via " + roadPath.getName() + " to " + currentTown.getName() + " " + roadPath.getWeight() + " mi");
		}
		
		Collections.reverse(path);
		return path;
	}

	/** Creates data structures containing information of the shortest path from the source vertex
	 * 
	 * @param sourceVertex The source of the shortestPath
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		initializeDijkstra(sourceVertex);
	
		while (!unvisited.isEmpty()) {
			Town nearestTown = getNearestUnvisitedTown();
			unvisited.remove(nearestTown);
	
			unvisitedNeighborsOfTown(nearestTown).forEach(neighbor -> {
				int netWeight = distance.get(nearestTown) + getEdge(nearestTown, neighbor).getWeight();
	
				if (netWeight < distance.get(neighbor)) {
					distance.put(neighbor, netWeight);
					previous.put(neighbor, nearestTown);
				}
			});
		}
	}
	
	private void initializeDijkstra(Town sourceVertex) {
		towns.forEach(town -> {
			distance.put(town, Integer.MAX_VALUE);
			previous.put(town, null);
			unvisited.add(town);
		});
	
		distance.put(sourceVertex, 0);
	}
	
	
	/** Helper method of dijkstraShortestPath that gets the unvisited neighbors of a town
	 * 
	 * @param town The town to get unvisited neighbors from
	 * @return A set of towns that are neighbors of town and also unvisited by the dijkstra algorithm
	 */
	private Set<Town> unvisitedNeighborsOfTown(Town town) {
    	return edgesOf(town).stream()
        	.map(road -> getNeighbor(town, road))
        	.filter(neighbor -> unvisited.contains(neighbor) && !visited.contains(neighbor))
        	.collect(Collectors.toSet());
	}

	/**
	 * Represents a town in a graph.
	 * @return the neighbor of town along road
	 */
	private Town getNeighbor(Town town, Road road) {
    	return road.getSource() == town ? road.getDestination() : road.getSource();
	}

	/**
	 * Represents a town in the graph.
	 * @return the shortest path from sourceVertex to destinationVertex
	 */
	private Town getNearestUnvisitedTown() {
		return unvisited.stream()
			.min(Comparator.comparingInt(town -> distance.get(town)))
			.orElse(null);
	}
}
