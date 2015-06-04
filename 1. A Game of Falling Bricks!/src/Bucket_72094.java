
public class Bucket_72094 {
	public char [][] matrix;
	public int height;
	
	public Bucket_72094(int height){
		this.height = height;
		matrix = new char[8][height];
	}
	
	@Override
	public String toString() {
		StringBuilder builder  = new StringBuilder();
		for(int i=height-1;i>=0;i--){
			for(int j=0;j<8;j++){
				builder.append(matrix[j][i]);
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public void printOutput(){
		int j,i,count;
		count=0;
		StringBuilder builder = null;
		builder = new StringBuilder();
		for(i=height-1;i>=0;i--){			
			boolean xInBwFlag = false;
			for(j=0;j<8;j++){
				if(matrix[j][i] != '.'){
					xInBwFlag = true;
					break;
				}
			}
			if(xInBwFlag){
				count++;
				printRow(i,builder);
				if(i > 0){
					builder.append("\n");
				}
			}
		}
		System.out.println(count);
		if(builder.length() > 0){
			System.out.println(builder.toString());
		}
	}

	private void printRow(int currRow, StringBuilder builder) {
		// TODO Auto-generated method stub
		for(int j=0;j<8;j++){
			builder.append(matrix[j][currRow]);
		}		
	}
}
