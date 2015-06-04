import java.util.ArrayList;
/**
 * class ScoresList_72094
 * 
 * Description: Stores the list of scores
 * 
 * @author Bastin Gomez H
 *
 */

public class ScoresList_72094 {
	ArrayList<Scores_72094> scoresList;
	
	public ScoresList_72094() {
		scoresList = new ArrayList<Scores_72094>();
	}
	
	public void addScore(Scores_72094 scores){
		scoresList.add(scores);
	}
	
	@Override
	public String toString() {
		return scoresList.toString();
	}
	
	public int getCount(){
		return scoresList.size();
	}
	
	public Scores_72094 getElementAt(int index){
		return scoresList.get(index);
	}
	
	public void remove(int index){
		scoresList.remove(index);
	}
	
}
