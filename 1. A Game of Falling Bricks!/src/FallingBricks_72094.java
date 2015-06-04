import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * class FallingBricks_72094
 * 
 * Description: Handles the Falling bricks based on the predined input from command line.
 * 
 * @author Bastin Gomez H
 *
 */
public class FallingBricks_72094 {
	public static void main(String[] args) throws IOException  {
		new FallingBricks_72094().perform();
	}

	/**
	 * void perform() : contains the logic to read from command line & process it.
	 * 
	 * @throws IOException
	 */
	public void perform() throws IOException{

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		// Contains the list of Brick-Position lists. The whole input from command line will be read into this.
		ArrayList<BrickPositionList_72094> listOfBrickPositions = new ArrayList<BrickPositionList_72094>(); 

		String line = null;
		while(true){
			line = br.readLine();
			line = line.trim();
			if("0".equals(line)) // If 0, then break out & process each of the list
				break;
			if("".equals(line))  // If blank, then loop again - go to next line
				continue;
			if(line.indexOf(" ") == -1){ // A line without a space, meaning the number of bricks
				int noOfBlocks = 0;
				try{
					noOfBlocks = Integer.parseInt(line); // No: of bricks
				}catch(NumberFormatException e){
					System.out.println("Invalid Number..exiting");
					System.exit(0);
				}
				if(noOfBlocks >= 100){ // validation. no: of bricks should be less than 100 (n < 100)
					System.out.println("Invalid Input. Number of bricks should be < 100..exiting");
					System.exit(0);
				}
				String brickAndPositions = br.readLine();
				BrickPositionList_72094 brickPositionList = createBrickPositionList(brickAndPositions,noOfBlocks);
				listOfBrickPositions.add(brickPositionList);				
			}			
		}
		for(BrickPositionList_72094 currBrickList: listOfBrickPositions){
			processBricks(currBrickList);
		}
	}

	/**
	 * void processBricks() : Takes each set of bricks & processes it to be fit in the bucket.
	 * 
	 * @param BrickPositionList_72094 currBrickList
	 *  
	 */
	private void processBricks(BrickPositionList_72094 currBrickList) {
		Bucket_72094 currBucket = new Bucket_72094(400);
		fillDotsIntoBucket(currBucket); // Fill dots in the bucket. Initialization.
		for(BrickPosition_72094 brickPosition : currBrickList){
			int position = brickPosition.position;
			String whichBlock = brickPosition.whichBlock;			
			insertIntoBucket(currBucket,position,whichBlock); // Inserting the current brick into the bucket.
			checkAndRemoveFilledRows(currBucket); // 'disappearing' process after a brick is placed.			
		}
		currBucket.printOutput(); // prints out the output in desired fashion.
	}

	/**
	 * void fillDotsIntoBucket() : Initializes a bucket with dots.
	 * 
	 * @param Bucket_72094 currBucket
	 */
	private void fillDotsIntoBucket(Bucket_72094 currBucket) {
		int height = currBucket.height;
		for(int i=0;i<height;i++){
			for(int j=0;j<8;j++){
				currBucket.matrix[j][i] = '.';
			}
		}

	}
	/**
	 * void checkAndRemoveFilledRows() : 'disappearing' process. If a row is completely filled with 'X', move one row down.  
	 * 
	 * @param Bucket_72094 currBucket
	 */
	private void checkAndRemoveFilledRows(Bucket_72094 currBucket) {
		int height = currBucket.height;
		for(int i=0;i<height;i++){
			boolean blanksInBwFlag = false;
			for(int j=0;j<8;j++){
				if(currBucket.matrix[j][i] != 'X'){
					blanksInBwFlag = true;
					break;
				}
			}
			if(!blanksInBwFlag){
				moveOneRowDown(currBucket,i);
				i--;
			}
		}
	}

	/**
	 * void moveOneRowDown() : Disappear the current row. Move the rows above it one row down.
	 * 
	 * 
	 * @param Bucket_72094 currBucket
	 * @param int currRow
	 */
	private void moveOneRowDown(Bucket_72094 currBucket, int currRow) {
		int height = currBucket.height;
		for(int j=0;j<8;j++){
			currBucket.matrix[j][currRow] = '.';			
		}

		for(int i=currRow+1;i<height;i++){
			for(int j=0;j<8;j++){
				currBucket.matrix[j][i-1] = currBucket.matrix[j][i];
				if( i == height - 1){
					currBucket.matrix[j][i] = '.';
				}
			}			
		}
	}

	/**
	 * int findStartLevel() : which is the row in which brick should be inserted, searching from top.
	 * 
	 * @param Bucket_72094 currBucket
	 * @param int position
	 * @return int
	 */
	private int findStartLevel(Bucket_72094 currBucket, int position) {
		int height = currBucket.height;
		for(int i=height-1;i>=0;i--){
			if(currBucket.matrix[position][i] == 'X'){
				return i+1;
			}
		}
		return 0;
	}

	/**
	 * void insertIntoBucket() : Based on the type of Brick, insert it to the appropriate location in the bucket.
	 * 
	 * @param Bucket_72094 currBucket
	 * @param int position
	 * @param String whichBlock
	 */
	private void insertIntoBucket(Bucket_72094 currBucket, int position, String whichBlock) {
		char block = whichBlock.toCharArray()[0];
		int height = currBucket.height;
		int startLevel = findStartLevel(currBucket,position);
		int j=position;
		boolean done = false;
		switch (block) {			
		case 'A':
			//   . .
			//   . .

			if(position >= 7 || position < 0){
				System.out.println("Invalid Input: "+whichBlock + " " + position);
				System.exit(0);
			}
			for(int i=startLevel;i<height-1;i++){
				if(currBucket.matrix[j][i] != 'X' && currBucket.matrix[j][i+1] != 'X' 
					&& currBucket.matrix[j+1][i] != 'X' && currBucket.matrix[j+1][i+1] != 'X')  {

					currBucket.matrix[j][i] = 'X';
					currBucket.matrix[j][i+1] = 'X'; 
					currBucket.matrix[j+1][i] = 'X';
					currBucket.matrix[j+1][i+1] = 'X';
					done = true;
					break;
				}
			}			
			break;
		case 'B':
			//	 . 
			//   . .
			if(position >= 7 || position < 0){
				System.out.println("Invalid Input: "+whichBlock + " " + position);
				System.exit(0);
			}
			for(int i=startLevel;i<height-1;i++){
				if(currBucket.matrix[j][i] != 'X' && currBucket.matrix[j][i+1] != 'X' 
					&& currBucket.matrix[j+1][i] != 'X' )  {

					currBucket.matrix[j][i] = 'X';
					currBucket.matrix[j][i+1] = 'X'; 
					currBucket.matrix[j+1][i] = 'X';						
					done = true;
					break;
				}				
			}	
			break;
		case 'C':
			//	. . 
			//    .
			if(position >= 7 || position < 0){
				System.out.println("Invalid Input: "+whichBlock + " " + position);
				System.exit(0);
			}
			for(int i=startLevel;i<height-1;i++){
				if(i == 0)
					continue;
				if(currBucket.matrix[j][i] != 'X' 
					&& currBucket.matrix[j+1][i] != 'X' && currBucket.matrix[j+1][i-1] != 'X')  {

					currBucket.matrix[j][i] = 'X'; 
					currBucket.matrix[j+1][i] = 'X';
					currBucket.matrix[j+1][i-1] = 'X';
					done = true;
					break;
				}
			}
			break;
		case 'D':
			// . .
			// .
			if(position >= 7 || position < 0){
				System.out.println("Invalid Input: "+whichBlock + " " + position);
				System.exit(0);
			}
			for(int i=startLevel;i<height-1;i++){
				if(currBucket.matrix[j][i] != 'X' && currBucket.matrix[j][i+1] != 'X' 
					&& currBucket.matrix[j+1][i+1] != 'X')  {

					currBucket.matrix[j][i] = 'X';
					currBucket.matrix[j][i+1] = 'X'; 
					currBucket.matrix[j+1][i+1] = 'X';
					done = true;
					break;
				}

			}	
			break;
		case 'E':
			//   .
			// . .
			if(position >= 7 || position < 0){
				System.out.println("Invalid Input: "+whichBlock + " " + position);
				System.exit(0);
			}
			for(int i=startLevel;i<height-1;i++){
				if(currBucket.matrix[j][i] != 'X'  
					&& currBucket.matrix[j+1][i] != 'X' && currBucket.matrix[j+1][i+1] != 'X')  {

					currBucket.matrix[j][i] = 'X';
					currBucket.matrix[j+1][i] = 'X';
					currBucket.matrix[j+1][i+1] = 'X';
					done = true;
					break;
				}

			}	

			break;
		case 'F':
			//	.
			//	.
			//	.
			//	.
			if(position > 7 || position < 0){
				System.out.println("Invalid Input: "+whichBlock + " " + position);
				System.exit(0);
			}
			for(int i=startLevel;i<height-3;i++){
				if(currBucket.matrix[j][i] != 'X' && currBucket.matrix[j][i+1] != 'X' 
					&& currBucket.matrix[j][i+2] != 'X' && currBucket.matrix[j][i+3] != 'X')  {

					currBucket.matrix[j][i] = 'X';
					currBucket.matrix[j][i+1] = 'X'; 
					currBucket.matrix[j][i+2] = 'X';
					currBucket.matrix[j][i+3] = 'X';
					done = true;
					break;
				}

			}	
			break;
		case 'G':
			// . . . .
			if(position > 4 || position < 0){
				System.out.println("Invalid Input: "+whichBlock + " " + position);
				System.exit(0);
			}			
			for(int i=startLevel;i<height-1;i++){
				if(currBucket.matrix[j][i] != 'X' && currBucket.matrix[j+1][i] != 'X' 
					&& currBucket.matrix[j+2][i] != 'X' && currBucket.matrix[j+3][i] != 'X')  {

					currBucket.matrix[j][i] = 'X';
					currBucket.matrix[j+1][i] = 'X'; 
					currBucket.matrix[j+2][i] = 'X';
					currBucket.matrix[j+3][i] = 'X';
					done = true;
					break;
				}
			}	

			break;
		default:
			System.out.println("Invalid type of brick '"+block +"'. exiting...");
		System.exit(0);
		break;
		}
	}


	/**
	 * BrickPositionList_72094 createBrickPositionList() : Create the datastructure for Brick-Position List
	 * 
	 * @param String bricksAndPostions
	 * @param int noOfBlocks
	 * @return
	 */
	private BrickPositionList_72094 createBrickPositionList(String bricksAndPostions,int noOfBlocks){
		String[] brickPositionArr = bricksAndPostions.split(" ");
		int count = brickPositionArr.length;
		if(noOfBlocks * 2 != count){
			System.out.println("Invalid Input... Number of bricks don't match with the no: of (Shape,Count) pairs... exiting... ");
			System.exit(0);
		}		
		BrickPositionList_72094 brickPositionsList = new BrickPositionList_72094();

		for(int i=0;i< count;i++){
			BrickPosition_72094 brickPosition = new BrickPosition_72094();
			brickPosition.whichBlock = brickPositionArr[i];
			int pos ;
			pos = 0;
			try{
				pos = Integer.parseInt(brickPositionArr[++i]);
			}catch(NumberFormatException e){
				System.out.println("Invalid input :" + brickPositionArr[i-1] + " "+brickPositionArr[i]);
				System.exit(0);
			}
			brickPosition.position = pos;
			brickPositionsList.add(brickPosition);
		}
		return brickPositionsList;
	}
}
