
package Pj6;


/** A road connecting two towns
 * 
 * @author Ashton Kabou
 *
 */
public class Road implements Comparable<Road>{
	
	private Town source;
	private Town destination;
	private int weight;
	private String name;
	
	/** Constructor that sets basic data about it
	 * 
	 * @param source The source town
	 * @param destination The destination town
	 * @param degrees The weight of the road
	 * @param name The name of the road
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = degrees;
		this.name = name;
	}
	
	/** Constructor that sets a default weight of 1
	 * 
	 * @param source The source town
	 * @param destination The destination town
	 * @param name The name of the town
	 */
	public Road(Town source, Town destination, String name) {
		this(source, destination, 1, name);
	}
	
	/** Checks if the road contains a town
	 * 
	 * @param town The town to check if it exists on the road
	 * @return True of the road contains the town, false if not
	 */
	public boolean contains(Town town) {
		if (town == null)
			return false;
		
		if (source.equals(town)) {
			return true;
		} else if (destination.equals(town)) {
			return true;
		}
		
		return false;
	}

	/** Checks if two roads are equal by checking its source and destination
	 * 
	 * @return True if the roads are equal, false if not
	 */
	@Override
	public boolean equals(Object r) {
		Road newR = (Road) r;
		if (newR.destination == destination && newR.source == source) {
			return true;
		} else if(newR.destination == source && newR.source == destination) {
			return true;
		}
		
		return false;
	}
	
	/** Gets the destination of the road
	 * 
	 * @return The destination of the road
	 */
	public Town getDestination() {
		return destination;
	}
	
	/** Gets the name of the road
	 * 
	 * @return The name of the road
	 */
	public String getName() {
		return name;
	}
	
	/** Gets the source of the road
	 * 
	 * @return The source of the road
	 */
	public Town getSource() {
		return source;
	}
	
	/** Gets the weight of the road
	 * 
	 * @return The weight of the road
	 */
	public int getWeight() {
		return weight;
	}
	
	/** Describes the road
	 * 
	 * @return A description of the road
	 */
	@Override
	public String toString() {
		return "A road connecting " + source + " to " + destination + " by " + weight + " miles."; 
	}

	/** Compares the weight of two roads
	 * 
	 * @param o The other road to compare to.
	 * 
	 * @return A positive number if the weight of the current road is bigger than the other road, negative otherwise
	 */
	@Override
	public int compareTo(Road o) {
		return weight - o.getWeight();
	}
}
