# 8tile-DFS
Depth first search algorithm to generate solutions for the 8 tile game.
This program runs on Mac and has been compiled using compiler compliance 1.8.
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
java.class.version = 52.0

To run navigate to the relevant directory contain the jar, then in terminal use:

java -Xss129m -jar F016316Ex.jar

to run. 

To input values for the puzzle state, convert the letters to integers using 
A=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, Blank=-1
Enter one number and press enter.
The values you enter will be stored in the order shown below.
1	2	3
4	5	6
7	8	9
When you press entry on the final value it will begin the search automatically.
The program will take around 2 hours to run and will output all states to the terminal window with the number of states written at the end.

