/**
 * class Combination_72094
 * 
 * @author BASTING
 *
 * description: It stores the combination of rollno, subject & the score
 *
 */
public class Combination_72094 {
	int rollno;
	int subject; // 1 = m; 2 = p; 3 = c; 4 = b
	int score;
	
	
	@Override
	public String toString() {
		String sub  = getSubject();
		return "rsc="+rollno+":"+sub+":"+score;
	}


	private String getSubject() {
		if(subject == 1){
			return "m";
		}else if(subject == 2){
			return "p";
		}else if(subject == 3){
			return "c";
		}else if(subject == 4){
			return "b";
		}
		return null;
	}
}
