import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class Maze {
	private static final int open = 0;
	private static final int tried = 11;
	private static final int path = 12;
	private static final int start = 20;
	private static final int end = 30;
	private static final int closed = 1;
	
	private int rows, columns;
	private int endRow, endColumn;
	private int startRow, startColumn;
	private int[][] grid;
	private int[][] solution;
	private List<int[]> startCoordinates;
	private List<int[]> endCoordinates;
	private int directions;
	private int starts;
	private int ends;

	
	public Maze(File fileName) throws FileNotFoundException {
	    List<String> lines = new ArrayList<>();
	    Scanner fileScanner = new Scanner(fileName);

	    while (fileScanner.hasNextLine()) {
	        lines.add(fileScanner.nextLine());
	    }

	    rows = Integer.parseInt(lines.get(0).split(",")[0]);
	    columns = Integer.parseInt(lines.get(0).split(",")[1]);
	    directions = Integer.parseInt(lines.get(0).split(",")[2]);
	    grid = new int[rows][columns];
	    startCoordinates = new ArrayList<>();
	    endCoordinates = new ArrayList<>();
	    solution = new int[rows][columns];
	    for (int i = 1; i < rows + 1; i++) {
	        String[] values = lines.get(i).split(",");
	        for (int j = 0; j < columns; j++) {
	            String cellValue = values[j];

	            if (cellValue.equals("S")) {
	                grid[i - 1][j] = 20; // Marcador de inicio
	                int[] startCoord = { i , j +1 };
	                startCoordinates.add(startCoord);
	                startRow=i;
	                startColumn =j+1;
	                starts++;
	            } else if (cellValue.equals("F")) {
	                grid[i - 1][j] = 30; // Marcador de final
	                int[] endCoord = { i , j +1 };
	                endCoordinates.add(endCoord);
	                endRow=i;
	                endColumn =j+1;
	                ends++;
	            } else {
	                int cell = Integer.parseInt(cellValue);
	                grid[i - 1][j] = cell;
	            }
	        }
	    }
	}


    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 20) {
                    System.out.print("S" + " ");
                } else if (grid[i][j] == 30) {
                    System.out.print("F" + " ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
	
    public void printSolution() {
		for(int i=0; i<rows;i++) {
			for(int j=0; j<columns;j++) {
				
				if (solution[i][j] == 2) {
	                System.out.print("2" + " "); // Movimiento hacia abajo
	            } else if (solution[i][j] == 4) {
	                System.out.print("4" + " "); // Movimiento hacia la izquierda
	            } else if (solution[i][j] == 6) {
	                System.out.print("6" + " "); // Movimiento hacia la derecha
	            } else if (solution[i][j] == 8) {
	                System.out.print("8" + " "); // Movimiento hacia arriba
	            }else if(solution[i][j] == 7){
	            	System.out.print("7"+ " ");
	            }else if(solution[i][j] == 9 ) {
	            	System.out.print("9"+ " ");
	            }else if(solution[i][j] == 24 ) {
	            	System.out.print("24"+ " ");
	            }else if(solution[i][j] == 3 ) {
	            	System.out.print("3"+ " ");
	            }
	            else if(grid[i][j] == start) {
	            	System.out.print("S"+" ");
	            }else if(grid[i][j] == end) {
	            	System.out.print("F"+" ");
	            }
				else {
					System.out.print(grid[i][j] + " ");
				}
				
			}
			System.out.println();	
		}
	}
	
	public void setStartRow(int row) {
		this.startRow = row;
	}
	
	public void setStartColumn(int column) {
		this.startColumn = column;
	}
	
	public void setEndRow(int eRow) {
		endRow = eRow;
	}
	
	public void setEndColum(int eCol) {
		endColumn = eCol;
	}
	
	public int getStarts() {
		return starts;
	}
	
	public int getEnds() {
		return ends;
	}
	
	public List<int[]> getStartCoordinates(){
		return startCoordinates;
	}
	
	public List<int[]> getEndCoordinates(){
		return endCoordinates;
	}
	
	public void tryPosition(int row, int column) {
		
		grid[row][column] = tried;
	}
	
	public boolean solved(int row, int column) {
		return (row == endRow && column == endColumn);
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public int getDirections() {
		return directions;
	}
	
	public int getRows() {
		return grid.length;
	}
	
	public int getColumns() {
		return grid[0].length;
	}
	
	public int getEndRow() {
		return endRow;
	}
	
	public int getEndColumn() {
		return endColumn;
	}
	
	public int getStartColumn() {
		return startColumn;
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public void markPath(int row, int column, String direction) {
	    
	        int value = 0;

	        if (direction.equals("U")) {
	            value = 8;
	        } else if (direction.equals("D")) {
	            value = 2;
	        } else if (direction.equals("L")) {
	            value = 4;
	        } else if (direction.equals("R")) {
	            value = 6;
	        } else if (direction.equals("UR")) {
	            value = 9;
	        } else if (direction.equals("DR")) {
	            value = 3;
	        } else if (direction.equals("UL")) {
	            value = 7;
	        } else if (direction.equals("DL")) {
	            value = 24;
	        } else {
	            value = 0; // Valor predeterminado para otras direcciones
	        }

	        grid[row][column] = path;
	        solution[row][column] = value;
	    
	}

	
	
	
	public boolean validPosition(int row, int column) {
	    boolean result = false;
	    if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length){
	        if (grid[row][column] == open) {
	            result = true;
	        }
	    }
	    return result;
	}
	
	/*
	public boolean isValid(int row, int column) {
	    int rows = grid.length;
	    int columns = grid[0].length;

	    // Verificar si las coordenadas son válidas
	    if (row >= 0 && row < rows && column >= 0 && column < columns) {
	        // Verificar si el valor en la coordenada es igual a open o closed
	        if (grid[row][column] == open) {
	            return true;
	        }else if(grid[row][column] == closed) {
	        	return false;
	        }
	    }
	    return false;
	}	*/
	
	public String toString() {
		String result = "\n";
		for(int row=0;row<grid.length;row++) {
			for(int column=0;column<grid[row].length;column++) {
				result += grid[row][column]+"";
				result += "\n";
			}
		}
		return result;
	}
	
}
