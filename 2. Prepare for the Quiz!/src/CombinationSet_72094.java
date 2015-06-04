/**
 * class CombinationSet_72094
 * 
 * @author BASTING
 *
 * description: It stores combination of subjects like below
 *  [0] - math
 *	[1] - phy
 *	[2] - chem
 *	[3] - bio
 *
 */
public class CombinationSet_72094 implements Comparable<CombinationSet_72094>{
	//[0] - math
	//[1] - phy
	//[2] - chem
	//[3] - bio
	public Combination_72094[] set = new Combination_72094[4];
	
	public long total;
	
	@Override
	public String toString() {
		return set[0].toString()+":"+set[1].toString()+":"+
		set[2].toString()+":"+set[3].toString()+":"+total;
	}

	public int compareTo(CombinationSet_72094 comSet) {
		if(total > comSet.total){
			return -1;
		}else if(total <comSet.total){
			return 1;
		}
		return 0;
	}
	
	
	
}
