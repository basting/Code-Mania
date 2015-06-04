
/**
 * 
 * The Class Person_72094.
 * 
 * @author Bastin Gomez H
 */
public class Person_72094 {
	
	/** The group. */
	private String group; // Group A, B, C ...
	
	/** The marked. */
	private boolean marked;
	
	/**
	 * Instantiates a new person_72094.
	 * 
	 * @param group the group
	 */
	public Person_72094(String group){
		this.group = group;
	}
	
	/**
	 * Mark.
	 */
	public void mark(){
		marked = true;
	}
	
	/**
	 * Un mark.
	 */
	public void unMark(){
		marked = false;
	}
	
	/**
	 * Checks if is marked.
	 * 
	 * @return true, if is marked
	 */
	public boolean isMarked(){
		return marked;
	}

	/**
	 * Gets the group.
	 * 
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Sets the group.
	 * 
	 * @param group the new group
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return group;
	}
}
