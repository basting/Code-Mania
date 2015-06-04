
/**
 * The Class PartyRoom_72094.
 * 
 * @author Bastin Gomez H
 * 
 */
public class PartyRoom_72094 {
	
	/** The persons. */
	private Person_72094 [][] persons;
	
	/** The row. */
	private int row;
	
	/** The column. */
	private int column;
	
	/**
	 * Gets the rows.
	 * 
	 * @return the rows
	 */
	public int getRows(){
		return row;
	}
	
	/**
	 * Gets the columns.
	 * 
	 * @return the columns
	 */
	public int getColumns(){
		return column;
	}
	
	/**
	 * Instantiates a new party room_72094.
	 * 
	 * @param row the row
	 * @param column the column
	 */
	public PartyRoom_72094(int row,int column) {
		this.row = row;
		this.column = column;
		persons = new Person_72094[row][column];
	}
	
	/**
	 * Sets the person at.
	 * 
	 * @param person the person
	 * @param x the x
	 * @param y the y
	 */
	public void setPersonAt(Person_72094 person, int x,int y){
		persons[x][y] = person;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				Person_72094 currPerson = persons[i][j];
				if(currPerson == null){
					builder.append("(.)");
				}else{
					if(currPerson.isMarked()){
						builder.append("["+currPerson+"]");	
					}else{
						builder.append("("+currPerson+")");
					}
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * Gets the persons.
	 * 
	 * @return the persons
	 */
	public Person_72094[][] getPersons() {
		return persons;
	}
	
}
