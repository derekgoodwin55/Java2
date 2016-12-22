
public interface Identifiable extends Comparable<Identifiable> {
	/**
	 * Returns the ID of the current object
	 * @return The ID of the current object
	 */
	public String getID();
	
	/**
	 * Sets the ID of the current object
	 * @param id The ID to replace the object's current ID
	 */
	public void setID(String id);
	
	/**
	* This method should return less than 0 if this object's ID comes lexicographically
	* before i's ID, return greater than 0 if this object's ID comes lexicographically
	* after i's ID, and return 0 if they are equal.
	*
	* "cat" is before "cats"
	* "cat" is before "dog"
	* "Dog" is before "cat"
	* "3rivers" is before "Dog"
	* 
	* String's compareTo() method provides this functionality and should greatly 
	* simplify implementing this method.
	*
	* @param i The user or group to compare with
	*
	* @return The result of comparing this object's ID with i's ID
	*/
	public int compareTo(Identifiable i);
}
