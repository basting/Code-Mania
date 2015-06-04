
public class Node {
	public int data;
	public Node left;
	public Node right;
	
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer strRep = new StringBuffer("data: "+data);
		if(left != null){
			strRep.append(" left: "+left.data);
		}
		if(right != null){
			strRep.append(" right: "+right.data);
		}		
		return new String(strRep);
	}
}
