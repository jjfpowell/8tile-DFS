import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
	private static HashMap<String,PuzzleNode> nodesInBoth = new LinkedHashMap<String, PuzzleNode>();

	public static void main(String[] args) {
		int[][] s1 = new int[3][3];
		int[][] s2 = new int[3][3];
		System.out.println("Enter first puzzle state number by number:");
		System.out.println("A=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, Blank=-1");
		Scanner input1 = new Scanner(System.in); // Takes user input
		for(int i=0;i<3;i++) {
			for(int j=0; j<3;j++) {
					s1[i][j] = input1.nextInt(); // Adds to array
				}
			}
		System.out.println("Enter second puzzle state number by number:");
		System.out.println("A=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, Blank=-1");
		Scanner input2 = new Scanner(System.in);
		for(int i=0;i<3;i++) {
			for(int j=0; j<3;j++) {
					s2[i][j] = input2.nextInt();
			}
		}
		input1.close();
		input2.close();
		System.out.println("Starting DFS...");
		PuzzleNode s1Node = new PuzzleNode(s1); // Creates initial nodes with starting states
		PuzzleNode s2Node = new PuzzleNode(s2);
		DFS s1Search = new DFS(s1Node); // Calls DFS algorithms with these nodes
		DFS s2Search = new DFS(s2Node);
		System.out.println("Starting comparison...");
		for(String key1: s1Search.getVisitedNodes().keySet()) { // Checks if node exists in both sets
				if(s2Search.getVisitedNodes().get(key1)!=null) { // if they do add to hashmap
					nodesInBoth.put(key1,s1Search.getVisitedNodes().get(key1)); 
					System.out.println(Arrays.deepToString(s1Search.getVisitedNodes().get(key1).getPuzzleState()));
			}
		};
		System.out.println("Number of states in set 1 is: " + s1Search.getMapSize());
		System.out.println("Number of states in set 2 is: " + s2Search.getMapSize());
		System.out.println("Number of states that occur in s1 and s2 is: " + nodesInBoth.size());
		}
};
