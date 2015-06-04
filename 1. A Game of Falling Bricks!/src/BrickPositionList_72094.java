import java.util.ArrayList;
import java.util.Iterator;


public class BrickPositionList_72094 implements Iterable<BrickPosition_72094>{
	public ArrayList<BrickPosition_72094> brickPositions;
	
	public BrickPositionList_72094(){
		brickPositions = new ArrayList<BrickPosition_72094>();
	}
	
	public void add(BrickPosition_72094 bPosition){
		brickPositions.add(bPosition);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return brickPositions.toString();
	}

	public Iterator<BrickPosition_72094> iterator() {
		// TODO Auto-generated method stub
		return brickPositions.iterator();
	}
}
