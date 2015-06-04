import java.util.ArrayList;
/**
 * class CombinationSetList_72094
 * 
 * @author BASTING
 *
 * description: It stores list of combination sets
 *
 */

public class CombinationSetList_72094 {
	ArrayList<CombinationSet_72094> combSet;
	
	public CombinationSetList_72094(){
		combSet = new ArrayList<CombinationSet_72094>();
	}
	
	public void add(CombinationSet_72094 set){
		combSet.add(set);
	}
	
	@Override
	public String toString() {
	
		return combSet.toString();
	}
	
	
}
