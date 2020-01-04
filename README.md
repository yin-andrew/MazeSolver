# MazeSolver
 
Objective: This project determines whether a maze is solvable or not. The maze takes the form of an n x n binary matrix. A "0" designates a valid place in the maze that can be travelled to and "1" represents a wall in the maze. The beginning of the maze is the top leftmost corner [0][0] and the end of the maze is the bottom rightmost corner [n-1][n-1]. The program accepts the textfile containing the maze as a command-line argument and prints out the completed maze, designated by a path of "X" characters from the beginning to the end.  

Implementation:  The maze solver utilizes the built-in java stack class to keep track of positions. The beginning position is initially pushed into the stack. Then, that position is immediately popped and marked as traversed by the character "X". The program then creates an array of the possible adjacent positions (top, bottom, left, right) and their locations. It then iterates through each position in the array and checks whether or not the position is in-bounds and whether or not it is a traversable location (i.e a "0"). If both criteria are satisfied, that location is pushed into the stack of the positions to be traveled to. After all of the neighbors have been pushed or rejected, the while loop repeats. This process continues until the maze has been "solved" or the maze has been deemed unsolvable, which is when the stack has popped all traversable locations and none of them have the position of [n-1][n-1].    

Example Input:
A 7x7 binary matrix with a path from the beginning to end:
0 0 1 0 0 0 0
0 0 0 0 1 1 0
0 1 1 0 0 1 0
0 1 1 1 0 1 1
0 1 0 0 0 0 0
1 1 0 1 1 1 1
0 0 0 0 0 0 0 

Output:
X X 1 X X X X 
X X X X 1 1 X 
X 1 1 X X 1 X 
X 1 1 1 X 1 1 
X 1 X X X 0 0 
1 1 X 1 1 1 1 
X X X X X X X

Note: As demonstrated here, the program does not demonstrate the optimal path or paths that lead to the end and may potentially traverse all positions, marking even paths to dead ends as traversed.  
