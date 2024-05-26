package aplicacion;

import java.util.Scanner;

import persistencia.IncidenciaDAO;
import presentacion.InterfazUsuario;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		IncidenciaDAO dao = new IncidenciaDAO();
		
		int opcion = 0;
		
		InterfazUsuario.mensajeBienvenida();
		
		do {
			InterfazUsuario.imprimeMenu();
			try {
				opcion = sc.nextInt();
				sc.nextLine();
				Logica.ejecutaOpcion(opcion, dao);
				InterfazUsuario.saltarLineas();
				
			}catch(Exception e) {
				System.out.println("Valor introducido no válido");
				e.printStackTrace();
			}
			
		} while(opcion!=8);
		
		sc.close();
	}

}
