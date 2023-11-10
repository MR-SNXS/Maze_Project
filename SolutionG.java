import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SolutionG {
static File solutionFile = new File("miSolucion");
	
	void imprimirSolucion (List <String> instrucciones) {
		if(solutionFile.exists()) {
			System.out.println("\nSU ARCHIVO CON LA SOLUCION YA EXISTE");
		}
		else {
			System.out.println("\nCREANDO ARCHIVO CON SOLUCION...");
			try {
				FileWriter fileWriter = new FileWriter(solutionFile);
				PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.println("SOLUCION A LABERINTO");
				printWriter.println(instrucciones);
				printWriter.close();
				System.out.println("ARCHIVO CREADO CON EXITO");
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}