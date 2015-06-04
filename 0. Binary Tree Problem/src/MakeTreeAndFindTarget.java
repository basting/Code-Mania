import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MakeTreeAndFindTarget {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		ArrayList allTrees = new ArrayList();
		
		String line = null;
		System.out.println("Input: ");
		while(true){
			int flag = 0;
			line = br.readLine();
			if(line.equals(""))
				break;
			ArrayList al = new ArrayList();
			allTrees.add(al);
			while(true){
				for(int i=0;i<line.length();){
					char c = line.charAt(i);
					if(c == '('){
						i++;
						Load load = new Load();
						if(line.charAt(i) != ')'){
							String dataTemp = "";
							while(line.charAt(i) != ','){
								dataTemp = dataTemp + String.valueOf(line.charAt(i));
								i++;
							}
							load.data = Integer.parseInt(dataTemp);
							i++;
							String temp = "";
							while(line.charAt(i) != ')'){
								temp = temp + String.valueOf(line.charAt(i));
								i++;
							}
							load.pattern = temp;
							al.add(load);
						}else{
							flag = 1;
							String dataTemp1 = "";
							i++;
							while(i < line.length()){
								dataTemp1 = dataTemp1 + String.valueOf(line.charAt(i));
								i++;
							}
							al.add(dataTemp1);
						}
					}
					i++;
				}
				if(flag == 1){
					break;
				}else{
					line = br.readLine();
				}
			}			
		}
		
		findPresence(allTrees);
	}
	
	private static void findPresence(ArrayList allTrees){
		System.out.println("Output:");
		for(int i = 0; i<allTrees.size();i++){
			ArrayList currentList = (ArrayList)allTrees.get(i);
			String searchItem = (String)currentList.get(currentList.size()-1);
			int target = Integer.parseInt(searchItem.trim());
			currentList.remove(currentList.size()-1);
			Tree tree = makeTree(currentList);
			Node rootNode = tree.root;
			//target = target - rootNode.data;
			findTargetInTree(rootNode,target,tree);
			if(tree.found){
				System.out.println("There is a path of sum "+searchItem);
			}else{
				System.out.println("There is no path of sum "+searchItem);
			}
		}
	}
	
	private static void findTargetInTree(Node node, int target,Tree tree) {
		// TODO Auto-generated method stub
		int temp = target - node.data;
		if(!tree.found){
			if((temp == 0)&&(isLeaf(node))){
				tree.found = true;
				return;
			}else {
				if(node.left != null){
					findTargetInTree(node.left,temp,tree);
				}
				if(node.right != null){
					findTargetInTree(node.right,temp,tree);
				}
			}
		}else if((temp < 0)||(tree.found)){
			return;
		}
	}

	private static boolean isLeaf(Node node) {
		// TODO Auto-generated method stub
		if((node.left == null)&&(node.right == null))
			return true;
		return false;
	}	

	private static Tree makeTree(ArrayList currentList){
		sort(currentList);
		Tree tree = new Tree();
		for(int i=0;i<currentList.size();i++){
			Load currLoad = (Load)currentList.get(i);
			int level = getLevel(currLoad.pattern);
			if(level == 0){
				tree.nodeCount += 1;
				Node node = new Node();
				node.data = currLoad.data;
				node.left = null;
				node.right = null;
				tree.root = node;
			}else{
				String directions[] = currLoad.pattern.split("");
				Node temp = tree.root;
				for(int currDirection=1;currDirection<directions.length;currDirection++){
					if(currDirection != directions.length - 1){
						if(directions[currDirection].equals("L")){
							temp = temp.left;
						}else if(directions[currDirection].equals("R")){
							temp = temp.right;
						}
					}else{
						if(directions[currDirection].equals("L")){
							Node newNode = new Node();
							newNode.data = currLoad.data;
							newNode.left = null;
							newNode.right = null;
							temp.left = newNode;
							tree.nodeCount += 1;
						}else if(directions[currDirection].equals("R")){
							Node newNode = new Node();
							newNode.data = currLoad.data;
							newNode.left = null;
							newNode.right = null;
							temp.right = newNode;
							tree.nodeCount += 1;	
						}
					}
				}
			}
		}
		return tree;
	}
	
	private static void sort(ArrayList currentList){
		Collections.sort(currentList,new TreeNodeComparator());
	}
	
	private static int getLevel(String pattern){
		return pattern.length();
	}
}
