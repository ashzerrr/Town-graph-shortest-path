
package Pj6;


/** A class that holds Town data
 * 
 * @author Ashton kabou
 *
 */
public class Town implements Comparable<Town>{
	private String name;
	
	/** Constructor that gives the town a name 
	 *
	 * @param name The name of the town
	 */
	public Town(String name) {
		this.name = name;
	}
	
	/** Copy constructor that copies the name of another town 
	 *
	 * @param templateTown the town to copy from
	 */
	public Town(Town templateTown) {
		name = templateTown.getName();
	}
	
	/** Checks to see if the towns are the same, by checking the name
	 * 
	 * @return true if the names equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		return ((Town) obj).getName().equals(name);
	}
	
	/** Gets the name of the town
	 * 
	 * @return The name of the town
	 */
	public String getName() {
		return name;
	}
	
	/** The hashcode of the town
	 * 
	 * @return The hashcode of the name of the town
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/** Gets the name of the town
	 *  
	 *  @return The name of the town
	 */
	public String toString() {
		return getName();
	}

	/** Compares the two towns
	 * 
	 * @return 0 if they are the same, any other number otherwise. 
	 */
	@Override
	public int compareTo(Town town) {
		return name.compareTo(town.getName());
	}
}
