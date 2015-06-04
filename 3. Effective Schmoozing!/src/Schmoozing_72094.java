import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * class Schmoozing_72094.
 * 
 * @author Bastin Gomez H
 */
public class Schmoozing_72094 {
	
	/** The no of groups per room. */
	private int[] noOfGroupsPerRoom;
	
	/** The curr item. */
	private int currItem;
	
	/** The people in current group. */
	private int peopleInCurrentGroup;
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args)   {
		if(args.length != 2){
			System.out.println("Wrong parameters");
			System.out.println("Usage: java Schmoozing_72094 <inputfilename> <outputfilename>");			
		}else{
			try {
				new Schmoozing_72094().perform(args[0],args[1]);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Perform.
	 * 
	 * @param inputfile the inputfile
	 * @param outputfile the outputfile
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void perform(String inputfile,String outputfile) throws IOException{
		FileReader fr = new FileReader(inputfile);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<PartyRoom_72094> partyRooms = new ArrayList<PartyRoom_72094>();
		boolean exception = false;
		String line = null;
		while(true){
			line = br.readLine();
			if(line == null){
				System.out.println("Invalid input .. exiting..");
				exception = true;
				break;
			}
			line = line.trim();
			if("0".equals(line)) // If 0, then break out & process each of the list
				break;
			if("".equals(line))  // If blank, then loop again - go to next line
				continue;
			if(line.contains(" ")){ // A line with a space
				int x=0;
				int y=0;
				String[] xy = line.split(" ");
				if(xy.length != 2){
					System.out.println("Invalid input..exiting");
					exception = true;
					break;
				}
				try{
					x = Integer.parseInt(xy[0]);  // No: of rows 
					y = Integer.parseInt(xy[1]);  // No: of columns
				}catch(NumberFormatException e){
					System.out.println("Invalid input... exiting");
					exception = true;
					break;
				}
				if(x >= 50  || x<=1 || y >= 50  || y<=1 ){ // validation. No of rows & columns 1 < r < 50 and 1 < c < 50
					System.out.println("Invalid Input.... 1 < r < 50 and 1 < c < 50..exiting");
					exception = true;
					break;
				}
				PartyRoom_72094 partyRoom = new PartyRoom_72094(x,y);
				for(int i=0;i<x;i++){
					line = br.readLine();
					if(line == null){
						System.out.println("Invalid input .. exiting...");
						exception = true;
						break;
					}
					line = line.trim();
					if(line.length() != y){
						System.out.println("Invalid Input.. ");
						exception = true;
						break;
					}
					for(int j=0;j<y;j++){
						char c = line.charAt(j);
						if(c != '.'){
							Person_72094 person = new Person_72094(Character.toString(c));
							partyRoom.setPersonAt(person, i, j);	
						}						
					}
				}
				if(exception){
					break;
				}
				partyRooms.add(partyRoom);
			}
		}
		if(exception){
			return;
		}
		int noOfRooms = partyRooms.size();
		noOfGroupsPerRoom = new int[noOfRooms];
		for(int i=0;i<noOfRooms;i++){
			PartyRoom_72094 currPartyRoom = partyRooms.get(i);
			findAListGroupsInCurrentRoom(currPartyRoom);
			moveToNextRoom();
		}
		int len = noOfGroupsPerRoom.length;
		if(len > 0){
			FileWriter fw = new FileWriter(outputfile);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<len;i++){
				bw.write(i+1+" "+noOfGroupsPerRoom[i]);
				bw.newLine();
			}
			bw.close();
		}
	}

	/**
	 * Find A-list groups in current room.
	 * 
	 * @param currPartyRoom the curr party room
	 */
	private void findAListGroupsInCurrentRoom(PartyRoom_72094 currPartyRoom) {
		Person_72094[][] persons = currPartyRoom.getPersons();
		int x = currPartyRoom.getRows();
		int y = currPartyRoom.getColumns();
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				peopleInCurrentGroup = 0;
				markAndFindNeighbours(persons,x,y,i,j,true);				
			}
		}
	}

	/**
	 * Mark and find neighbours.
	 * 
	 * @param persons the persons
	 * @param row the row
	 * @param column the column
	 * @param currentRow the current row
	 * @param currentColumn the current column
	 * @param isParent the is parent
	 */
	private void markAndFindNeighbours(Person_72094[][] persons, int row, int column, int currentRow, int currentColumn, boolean isParent) {
		if(currentRow >= row || currentColumn >= column || currentColumn < 0 || currentRow < 0){
			return;
		}
		Person_72094 person = persons[currentRow][currentColumn];
		if(person == null){
			return;
		}
		if(person.isMarked()){
			return;
		}
		if(person.getGroup().equals("A")){
			person.mark();
			peopleInCurrentGroup++;
			
			markAndFindNeighbours(persons,row,column,currentRow,currentColumn+1,false);
			markAndFindNeighbours(persons,row,column,currentRow+1,currentColumn,false);
			markAndFindNeighbours(persons,row,column,currentRow,currentColumn-1,false);
			markAndFindNeighbours(persons,row,column,currentRow-1,currentColumn,false);
			
			if(isParent && peopleInCurrentGroup >= 3){
				incrementCurrentRoomCount();				
			}			
		}else{
			person.mark();
			return;
		}		
	}
	
	/**
	 * Move to next room.
	 */
	public void moveToNextRoom(){
		currItem++;
	}
	
	/**
	 * Increment current room count.
	 */
	public void incrementCurrentRoomCount(){
		noOfGroupsPerRoom[currItem]++;
	}
}