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
public class Maze_Tester {
	private static String filePath = "C:\\Mazes";
	
	public static void main(String[] args)throws FileNotFoundException{
		// TODO Auto-generated method stub
		boolean flag = false;
		Scanner scan = new Scanner(System.in);
		System.out.println("Escriba el nombre del archivo de su laberinto: ");
		String fileName = scan.nextLine();
		File mazeFile = new File(filePath + File.separator + fileName);
		
		if (mazeFile.exists()) {
            Maze labyrinth = new Maze(mazeFile);
            labyrinth.printMaze();

            List<int[]> startCoordinates = labyrinth.getStartCoordinates();
            int startRow;
            int startColumn;

            if (startCoordinates.size() == 1) {
                // Caso: Solo hay un punto de inicio
                int[] startCoord = startCoordinates.get(0);
                startRow = startCoord[0];
                startColumn = startCoord[1];
            } else {
                // Caso: Múltiples puntos de inicio, permitir al usuario elegir uno
                System.out.println("El laberinto tiene múltiples comienzos:");
                for (int i = 0; i < startCoordinates.size(); i++) {
                    int[] startCoord = startCoordinates.get(i);
                    System.out.println("Comienzo " + (i + 1) + ": (" + startCoord[0] + ", " + startCoord[1] + ")");
                }
                System.out.print("Elige un comienzo (1-" + startCoordinates.size() + "): ");
                int chosenStart = scan.nextInt();
                if (chosenStart < 1 || chosenStart > startCoordinates.size()) {
                    System.out.println("Selección no válida");
                    scan.close();
                    return;
                } else {
                    int[] selectedStartCoord = startCoordinates.get(chosenStart - 1);
                    startRow = selectedStartCoord[0];
                    startColumn = selectedStartCoord[1];
                    labyrinth.setStartColumn(startColumn);
                    labyrinth.setStartRow(startRow);
                }
            }

            MazeSolver solver = new MazeSolver(labyrinth);
            System.out.println("Start at: "+labyrinth.getStartRow() + " " + labyrinth.getStartColumn());
            System.out.println("Finish at: "+labyrinth.getEndRow() + " " + labyrinth.getEndColumn());

            if (solver.resolverLaberinto(startRow, startColumn)) {
                System.out.println("\nThe maze was successfully traversed by BiggieCheese");
               System.out.println("\n"+labyrinth.getStarts());
               System.out.println(labyrinth.getEnds());
               labyrinth.printSolution();
            } else {
            	System.out.println("");
            	 labyrinth.printSolution();
                System.out.println("There is no possible path");
            }

            scan.close();
        }else {
			System.out.println("Verifique su sintaxis.\nVerifique que haya ingresado la extensión de su archivo.\nEjemplo:milaberinto.txt");
		}
		
		
	}

}
