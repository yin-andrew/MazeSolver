//Andrew Yin

import java.util.Stack;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Iterator;


public class MazeSolver {

	static char[][] maze;
	static int startX, startY;  // indices for starting the maze search
	static int endX, endY; // indices for ending the maze search

	// Constructor that creates the maze
	public MazeSolver(String fileName) throws IOException {
		startX = 0;
		startY = 0;
		readMaze(fileName); // initialize maze
	}

	// Helper method for reading the maze content from a file
	public static void readMaze(String filename) throws IOException {
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("[ERROR] Invalid filename: " + filename);
			return;
		}

		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		endX = N-1; endY = N-1;
		int i = 0;
		while(i < N && scanner.hasNext()) {
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j != N){
				System.err.println("[ERROR] Invalid line: " + i + " has wrong # columns: " + j);
				return;
			}
			i++;
		}
		if(i != N){
			System.err.println("[ERROR] Invalid file: has wrong number of rows: " + i);
			return;
		}
	}

	// Helper method for printing the maze in a matrix format
	public void printMaze() {
		for (int i=0; i < maze.length; i++) {
			for (int j=0; j < maze.length; j++) {
				System.out.print(maze[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	// Method returns whether or not the matrix maze is solvable and marks visited locations with X
	public void solveMaze() {

		Stack<MazePosition> s = new Stack<MazePosition>(); //stack of mazeposition objects 
		MazePosition start = new MazePosition(0,0);
		s.push(start);

		while(!s.empty()) {
			MazePosition a = s.pop();
			maze[a.getX()][a.getY()] = 'X'; // marking current coordinates as visited with X
			int n = maze.length;

			if(a.getX() == n-1 && a.getY() == n-1) {
				System.out.println("Maze is solvable.");
				return;
			} else {
				MazePosition[] neighbors; //array of 4 different neighbors and their positions relative to current position
				neighbors = new MazePosition[]{new MazePosition(a.getX(), a.getY()+1),
						new MazePosition(a.getX()+1, a.getY()),
						new MazePosition(a.getX(), a.getY()-1),
						new MazePosition(a.getX()-1, a.getY())};

				//checking bounds condition and whether there is a wall or not
				for(int i = 0; i < neighbors.length; i++) {
					if ((neighbors[i].getX() >= 0) && (neighbors[i].getX()) < n && 
							(neighbors[i].getY()) >= 0 && (neighbors[i].getY() < n)) {
						if(maze[neighbors[i].getX()][neighbors[i].getY()] == '0') {
							s.push(neighbors[i]);
						}  
					}

				}
			}
		}
		// if no path is found 
		System.out.println("Maze is not solvable.");
	}

	public static void main(String[] args) throws IOException {

		//displaying error message if file is not processed properly  
		if(args.length < 1) {
			System.err.println("[ERROR] Usage: java PathFinder maze_file");
			System.exit(-1); 
		}

		String fileName =  args[0];

		MazeSolver ms = new MazeSolver(fileName);
		System.out.println("[Before Traversal] Maze:");
		ms.printMaze();
		System.out.println();

		ms.solveMaze();
		System.out.println();
		System.out.println("[After Traversal] Maze:");
		ms.printMaze();
	}


}
