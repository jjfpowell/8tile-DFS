import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DFS {
	private PuzzleNode startNode;
	// Hashmap to store any expanded nodes
	private HashMap<String,PuzzleNode> visitedNodes = new LinkedHashMap<String, PuzzleNode>();
	public DFS(PuzzleNode _startNode) {
		this.startNode=_startNode;
		runSearch(startNode); // Starts DFS search
		System.out.println("Complete set generated");
		
	}
	public void runSearch(PuzzleNode currentNode) {
		boolean genChildVar = true;
		if(!(newNodeState(currentNode.getPuzzleState()))) { 
			// If this is a not new node, end the recursion for this branch
			genChildVar = false;
			}
		else { // else add it to the vistedNodes
			visitedNodes.put(namingFunc(currentNode.getPuzzleState()),new PuzzleNode(currentNode.getPuzzleState()));
			System.out.println(Arrays.deepToString(currentNode.getPuzzleState()));
			}
		if(genChildVar) { // if recursion on this branch should continue, recursively call this function
			for(PuzzleNode childNode: currentNode.generateChildren(visitedNodes)) {
				runSearch(childNode);
			}
		}
	}
	
	public boolean arraysEqual(int[][] arr1,int[][] arr2) { // Checks if two arrays are equal
		boolean equal = true;
		for (int j=0;j<3;j++) {
			for (int i=0;i<3;i++) {
				if(arr1[i][j]!=arr2[i][j]) {
					equal = false;
					return equal;
				}
			}
	    }
		return equal;
	    }
	public boolean newNodeState(int[][] arr1) { // Checks if node exists
		for(PuzzleNode n: visitedNodes.values()) {
			if(arraysEqual(arr1,n.getPuzzleState())) {
				return false;
			}
		}
		return true;
	}
	public String namingFunc(int[][] arr1) { // Creates unique hash key for given puzzle state
		char[] letters={'a','b','c','d','e','f','g','h','i'};
		List<Character> letterList = new ArrayList<Character>();
		for (int j=0;j<3;j++) {
			for (int i=0;i<3;i++) {
				if(!(arr1[j][i]==-1)) {
					letterList.add(letters[arr1[j][i]-1]);
				}else {
					letterList.add(letters[8]);
				}
	        }
	    }
		StringBuilder name = new StringBuilder(letterList.size());
	    for(Character ch: letterList){
	        name.append(ch);
	    }
	    return name.toString();
	}
	public void printArrays() { // Prints all visited nodes
		for(PuzzleNode n: visitedNodes.values()) {
			System.out.println(Arrays.deepToString(n.getPuzzleState()));
			}
	}
	public int getMapSize() { // get the size of the map
		return visitedNodes.size();
	}

	public HashMap<String, PuzzleNode> getVisitedNodes() { // getter function for the Hashmap
		return visitedNodes;
	}
}