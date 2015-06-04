import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * class Quiz_72094
 * 
 * Description: Handles the quiz scores & does all the processing.
 * 				CONTAINS the Main method.
 * 
 * @author Bastin Gomez H
 *
 */
public class Quiz_72094 {
	private ArrayList<Integer> rollNos;
	private ArrayList<Integer> mathScores;
	private ArrayList<Integer> physScores;
	private ArrayList<Integer> chemScores;
	private ArrayList<Integer> bioScores;

	private static final int MATH = 1;
	private static final int PHY = 2;
	private static final int CHEM = 3;
	private static final int BIO = 4;

	public Quiz_72094() {
		rollNos = new ArrayList<Integer>();
		mathScores = new ArrayList<Integer>();
		physScores = new ArrayList<Integer>();
		chemScores = new ArrayList<Integer>();
		bioScores = new ArrayList<Integer>();
	}

	public static void main(String[] args) throws IOException  {
		if(args.length != 2){
			System.out.println("Wrong parameters");
			System.out.println("Usage: java Quiz_72094 <inputfilename> <outputfilename>");			
		}else{
			new Quiz_72094().perform(args[0],args[1]);
		}
	}

	public void perform(String inputfile,String outputfile) throws IOException{
		ArrayList<CombinationSet_72094> finalCombSet = new ArrayList<CombinationSet_72094>();
		FileReader fr = new FileReader(inputfile);
		BufferedReader br = new BufferedReader(fr);

		boolean crashed = false;
		
		String line = null;
		while(true){
			line = br.readLine();
			line = line.trim();
			if("0".equals(line)) // If 0, then break out & process each of the list
				break;
			if("".equals(line))  // If blank, then loop again - go to next line
				continue;
			if(line.indexOf(" ") == -1){ // A line without a space, meaning the number of bricks
				int noOfScores = 0;
				try{
					noOfScores = Integer.parseInt(line); // No: of bricks
				}catch(NumberFormatException e){
					System.out.println("Invalid Number..exiting");
					crashed = true;
					break;
				}
				if(noOfScores > 100 || noOfScores < 4){ // validation. no: of bricks should be less than 100 (n < 100)
					System.out.println("Invalid Input. Number of scores should be less than 100 and greater than 4..exiting");
					crashed = true;
					break;
				}
				ScoresList_72094 scoreList = new ScoresList_72094();
				rollNos.clear();
				mathScores.clear();
				physScores.clear();
				chemScores.clear();
				bioScores.clear();
				for(int i=0;i<noOfScores;i++){
					String scores = br.readLine();
					Scores_72094 currScore = processScore(scores);
					scoreList.addScore(currScore);
				}
				//System.out.println(scoreList);
				if(scoreList.scoresList.size()>0){
					ArrayList<CombinationSet_72094> selectedCombSet = processScoreList(scoreList);
					finalCombSet.addAll(selectedCombSet);
				}
			}			
		}
		if(!crashed){
			writeScoreToFile(finalCombSet,outputfile);
		}
	}

	private ArrayList<CombinationSet_72094> processScoreList(ScoresList_72094 currScoreList) {
		ScoresList_72094 validScoreList = new ScoresList_72094();
		int count = currScoreList.getCount();
		for(int i=0;i<count;i++){
			Scores_72094 currScore = currScoreList.getElementAt(i);
			boolean valid = validateScore(currScore);
			if(valid){
				validScoreList.addScore(currScore);
			}else{
				rollNos.remove(i);
				mathScores.remove(i);
				physScores.remove(i);
				chemScores.remove(i);
				bioScores.remove(i);
				currScoreList.remove(i);
				i--;
				count--;
			}
		}
		CombinationSetList_72094 combSetList = findAllCombinations();
		Collections.sort(combSetList.combSet);
		ArrayList<CombinationSet_72094> selectedCombSet = getAllElementsWithHighestTotal(combSetList.combSet,combSetList.combSet.get(0).total);
		selectFinalScores(selectedCombSet);
		return selectedCombSet;
	}

	private void writeScoreToFile(ArrayList<CombinationSet_72094> finalCombSet, String outputfile) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(outputfile);

			BufferedWriter br = new BufferedWriter(fw);

			int finalCombSetSize = finalCombSet.size();
			for(int i=0;i<finalCombSetSize;i++){
				CombinationSet_72094 combSet = finalCombSet.get(i);
				Combination_72094[] combination = combSet.set;
				br.write(combination[0].rollno+" "+combination[1].rollno+" "+combination[2].rollno+" "+
						combination[3].rollno+" "+combSet.total);
				br.newLine();
			}			
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void selectFinalScores(ArrayList<CombinationSet_72094> selectedCombSetList) {
		removeNonToppers(selectedCombSetList,MATH);
		removeNonToppers(selectedCombSetList,PHY);
		removeNonToppers(selectedCombSetList,CHEM);
		removeNonToppers(selectedCombSetList,BIO);
	}

	private void removeNonToppers(ArrayList<CombinationSet_72094> selectedCombSetList, int subject) {
		int selectedCombSetSize = selectedCombSetList.size();
		//[0] - math
		int highScore = selectedCombSetList.get(0).set[subject-1].score;
		for(int i=0;i<selectedCombSetSize;i++){
			int tempScore = selectedCombSetList.get(i).set[subject-1].score;
			if( tempScore > highScore){
				highScore = tempScore; 
			}
		}
		for(int i=0;i<selectedCombSetList.size();i++){
			if( selectedCombSetList.get(i).set[subject-1].score != highScore){
				selectedCombSetList.remove(i);
				i--;
			}
		}
	}

	private ArrayList<CombinationSet_72094> getAllElementsWithHighestTotal(ArrayList<CombinationSet_72094> combSetList, long total) {
		ArrayList<CombinationSet_72094> selectedCombSet = new ArrayList<CombinationSet_72094>();
		int combSetSize = combSetList.size();
		for(int i=0;i<combSetSize;i++){
			CombinationSet_72094 combSet = combSetList.get(i);
			if(combSet.total == total){
				selectedCombSet.add(combSet);
			}
		}
		return selectedCombSet;
	}

	private CombinationSetList_72094 findAllCombinations() {
		CombinationSetList_72094 combSetList = new CombinationSetList_72094();
		int count = rollNos.size();
		for(int i=0;i<count;i++){
			for(int j=0;j<count;j++){
				if(i == j){
					continue;
				}	
				for(int k=0;k<count;k++){
					if(j == k || i == k){
						continue;
					}
					for(int l=0;l<count;l++){
						if(l == k || l == j || l == i){
							continue;
						}
						CombinationSet_72094 combSet = new CombinationSet_72094();

						int math = mathScores.get(i);
						Combination_72094 comb = new Combination_72094();
						comb.rollno = rollNos.get(i);
						comb.score = math;
						comb.subject = 1; // 1 = maths
						combSet.set[0] = comb;

						int phy = physScores.get(j);
						comb = new Combination_72094();
						comb.rollno = rollNos.get(j);
						comb.score = phy;
						comb.subject = 2; // 2 = physics
						combSet.set[1] = comb;

						int chem = chemScores.get(k);
						comb = new Combination_72094();
						comb.rollno = rollNos.get(k);
						comb.score = chem;
						comb.subject = 3; // 3 = chemistry
						combSet.set[2] = comb;

						int bio = bioScores.get(l);
						comb = new Combination_72094();
						comb.rollno = rollNos.get(l);
						comb.score = bio;
						comb.subject = 4; // 4 = bio
						combSet.set[3] = comb;

						combSet.total = math + phy + chem + bio;

						combSetList.add(combSet);
					}
				}

			}

		}
		//System.out.println(combSetList);
		return combSetList;
	}

	private boolean validateScore(Scores_72094 currScore) {
		int math = currScore.math;
		int phy = currScore.phy;
		int chem = currScore.chem;
		int bio = currScore.bio;

		if(math >= 50 && phy >= 50 && chem >= 50 && bio >= 50){
			return true;
		}

		return false;

	}

	private Scores_72094 processScore(String scoresStr) {
		Scores_72094 scores = new Scores_72094();
		String []scoreArr = scoresStr.split(" ");
		scores.rollno = Integer.parseInt(scoreArr[0]);
		scores.math = Integer.parseInt(scoreArr[1]);
		scores.phy = Integer.parseInt(scoreArr[2]);
		scores.chem = Integer.parseInt(scoreArr[3]);
		scores.bio = Integer.parseInt(scoreArr[4]);

		rollNos.add(scores.rollno);
		mathScores.add(scores.math);
		physScores.add(scores.phy);
		chemScores.add(scores.chem);
		bioScores.add(scores.bio);

		return scores;
	}

}
