import java.util.ArrayList;
import java.util.HashMap;

public class PuzzleNode {
	private int[][] puzzleState;
	private ArrayList<PuzzleNode> childNodes = new ArrayList<PuzzleNode>();

	
	public PuzzleNode(int[][] _puzzleState) {
		this.puzzleState = _puzzleState;
	}

	public int[][] getPuzzleState() { // PuzzleState getter
		return puzzleState;
	}

	public void setPuzzleState(int[][] puzzleState) { // PuzzleState setter
		this.puzzleState = puzzleState;
	}

	public ArrayList<PuzzleNode> getChildren() { // Children getter
		return childNodes;
	}
	
	public ArrayList<PuzzleNode> generateChildren(HashMap<String,PuzzleNode> visitedNodes) { 
		// Generates next possible children from a node
		for(int[][] state: generateLegalStates(findBlank(puzzleState),visitedNodes)) {
			childNodes.add(new PuzzleNode(state));
		}
		return childNodes;
	}
	
	
	public ArrayList<int[][]> generateLegalStates(int[] blankTile, HashMap<String,PuzzleNode> visitedNodes){
		// creates an array of any legal states which can be generated from a node
		ArrayList<int[][]> newStatesArray = new ArrayList<int[][]>();
		
			if(checkLeft(blankTile,visitedNodes)) { // Checks each direction to see if the blank can be moved 
				newStatesArray.add(moveLeft(blankTile));
			}
			if(checkRight(blankTile,visitedNodes)) {
				newStatesArray.add(moveRight(blankTile));
			}
			if(checkDown(blankTile,visitedNodes)) {
				newStatesArray.add(moveDown(blankTile));
			}
			if(checkUp(blankTile,visitedNodes)) {
				newStatesArray.add(moveUp(blankTile));
			}
			return newStatesArray;
	}
	public boolean checkRight(int[] blankTile,HashMap<String,PuzzleNode> visitedNodes) {
		int[][] checkState = copyArray(moveRight(blankTile));
		if(isItANewState(checkState, visitedNodes)) { // if the state is not new, not a valid move
			return true;
		}
		return false;
	}
	public boolean checkDown(int[] blankTile, HashMap<String,PuzzleNode> visitedNodes) {
		int[][] checkState = copyArray(moveDown(blankTile));
		if(isItANewState(checkState, visitedNodes)) {
			return true;
		}
		return false;
	}
	public boolean checkUp(int[] blankTile, HashMap<String,PuzzleNode> visitedNodes) {
		int[][] checkState = copyArray(moveUp(blankTile));
		if(isItANewState(checkState, visitedNodes)) {
			return true;
		}
		return false;
	}
	public boolean checkLeft(int[] blankTile, HashMap<String,PuzzleNode> visitedNodes) {
		int[][] checkState = copyArray(moveLeft(blankTile));
		if(isItANewState(checkState, visitedNodes)) {
			return true;
		}
		return false;
	}
	public int[][] moveDown(int[] blankTile) { // moves the blank tile into a new space
		int[][] newState = copyArray(puzzleState);
		int temp;
		if((blankTile[0]+1)<3) {
			temp=puzzleState[blankTile[0]+1][blankTile[1]];
			newState[blankTile[0]+1][blankTile[1]]=-1;
			newState[blankTile[0]][blankTile[1]]=temp;
			return newState;
			}
		else {
			return puzzleState;
		}
	}
	public int[][] moveUp(int[] blankTile) {
		int[][] newState = copyArray(puzzleState);
		int temp;
		if((blankTile[0]-1)>-1) {
			temp=puzzleState[blankTile[0]-1][blankTile[1]];
			newState[blankTile[0]-1][blankTile[1]]=-1;
			newState[blankTile[0]][blankTile[1]]=temp;
			return newState;
			}
		else {
			return puzzleState;
		}
	}
	public int[][] moveLeft(int[] blankTile) {
		int[][] newState = copyArray(puzzleState);
		int temp;
		if((blankTile[1]-1)>-1) {
			temp=puzzleState[blankTile[0]][blankTile[1]-1];
			newState[blankTile[0]][blankTile[1]-1]=-1;
			newState[blankTile[0]][blankTile[1]]=temp;
			return newState;
			}
		else {
			return puzzleState;
		}
	}
	public int[][] moveRight(int[] blankTile) {
		int[][] newState = copyArray(puzzleState);
		int temp;
		if((blankTile[1]+1)<3) {
			temp=puzzleState[blankTile[0]][blankTile[1]+1];
			newState[blankTile[0]][blankTile[1]+1]=-1;
			newState[blankTile[0]][blankTile[1]]=temp;
			return newState;
			}
		else {
			return puzzleState;
		}
	}
	public int[][] copyArray(int[][] arr1){ // copies one array to another
		int[][] arr2 = {{0,0,0},{0,0,0},{0,0,0}};;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				arr2[i][j]=arr1[i][j];
			}
		}
		return arr2;
	}
	public int[] findBlank(int[][] stateArray) { // finds the index of the blank tile
		int[] loc={0,0};
		for (int j=0;j<3;j++) {
			for (int i=0;i<3;i++) {
				if (stateArray[j][i] == -1) {
					loc[0]=j;
					loc[1]=i;
					return loc;
				}
	        }
	    }
		return loc;
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
	
	public boolean isItANewState(int[][] arr1, HashMap<String,PuzzleNode> visitedNodes) { // Checks if node exists
		for(PuzzleNode n: visitedNodes.values()) {
			if(arraysEqual(arr1,n.getPuzzleState())) {
				return false;
			}
		}
		return true;
	}
}