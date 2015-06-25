import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class WarOfLiners {
	public static void main(String[] args) throws IOException {
		new WarOfLiners().playWarOfLiners();		
	}

	public void playWarOfLiners() throws IOException{
		String inputFile = "input.txt";
		List<String> lines = Files.readAllLines(Paths.get(inputFile), Charset.defaultCharset());
		
		Board initialBoard = getInitialBoard();
		
		for(String line : lines){
			if("0".equals(line.trim())){
				System.out.println("End of Input.. exiting...");
				break;
			}
			String [] inputTokens = line.split(" ");
			int count = Integer.parseInt(inputTokens[0]);
			for(int i=0;i<count-1;i++){
				boolean hit = checkForHits(initialBoard, inputTokens[i+1]);
				if(hit){
					System.out.println("No");
					break;
				}
			}
			System.out.println("Yes");
		}
		
	}
	
	private boolean checkForHits(Board initialBoard, String string) {
		return false;
	}

	private Board getInitialBoard() {
		return new Board();
	}
}
