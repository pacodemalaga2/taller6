package presentacion;

import java.util.Scanner;

/**
 * Clase que mostrará al usuario las diferentes opciones disponibles
 */
public class InterfazUsuario {

	//Imprime un mensaje de bienvenida
	public static void mensajeBienvenida() {
		System.out.println("******************\nINCIDENCIAS\n*******************");
		System.out.println("Bienvenido/a al programa.");
	}
	
	//Imprime el menú del usuario
	public static void imprimeMenu() {
		System.out.println("1. Alta incidencia");
		System.out.println("2. Baja incidencia");
		System.out.println("3. Modificar incidencia");
		System.out.println("4. Resolver incidencia");
		System.out.println("5. Eliminar incidencia");
		System.out.println("6. Listar incidencias por ID");
		System.out.println("7. ListarIncidencias por fecha");
		System.out.println("8. Salir");
		System.out.println("\nPulse alguna opción");
	}
	
	//Epera una tecla
	public static void esperaTecla() {
		System.out.println("Pulse una tecla, para continuar");
		new Scanner(System.in).nextLine();
	}
	
	//Salta X lineas
	public static void saltarLineas() {
		for(int i=0;i<5;i++) {
			System.out.println();
		}
	}
	
	//Muestra los atributos a modificar de la lista X
	public static String[] modificaAtributo(String[] datosIncidencia) {
		String datos[] = new String[3];
		
		System.out.println("Nuevo ID");
		datos[0] = new Scanner(System.in).nextLine();
		System.out.println("Nueva fecha (dd/mm/yyyy):");
		datos[1] = new Scanner(System.in).nextLine();
		System.out.println("Nuevo problema");
		datos[2] = new Scanner(System.in).nextLine();
		
		return datos;
	}
	
	
	public static String[] pedirDatos() {
		String[] datosAltaIncidencia = new String[3];
		
		System.out.println("Introduce la ID de la incidencia");
		datosAltaIncidencia[0] = new Scanner(System.in).nextLine();
		System.out.println("Introduce la fecha (dd/MM/yyyy)");
		datosAltaIncidencia[1] = new Scanner(System.in).nextLine();
		System.out.println("Introduce el problema");
		datosAltaIncidencia[2] = new Scanner(System.in).nextLine();
		
		return datosAltaIncidencia;
	}
	
	
	/**
	 * Método que muestra por pantalla si la operación es posible
	 * @param opcion indica por el usuario
	 * @param operacion nos permite controlar si la operacion es posible
	 */
	public static void resultado(int opcion, boolean operacion) {
		
		switch(opcion){
		case 1 -> {
			if(operacion)
				System.out.println("\nIncidencia registrada");
			else
				System.out.println("\nEl ID de la incidencia ya existe");
		}
		case 2 -> {
			if(operacion)
				System.out.println("\nLa incidencia eliminada");
			else
				System.out.println("\nEl ID de la incidencia no existe");
		}
		case 3 -> {
			if(operacion)
				System.out.println("\nIncidencia modificada");
			else
				System.out.println("\nEl ID de la incidencia no es válido");
		}
		
		case 4 -> {
			if(operacion)
				System.out.println("\nIncidencia resulta");
			else
				System.out.println("\nEl ID no existe");
		}
		
		case 5 -> {
			if(operacion)
				System.out.println("\nIncidencia resulta");
			else
				System.out.println("\nEl ID no existe");
		}
		
		case 6,7 -> {
			if(!operacion)
				System.out.println("\nListado de incidencias vacio");
		}
			
		}
		
	}
	
	
	public static String indicaIdIncidencia(int opcion) {
		String id = null;
		
		switch(opcion) {
		
		case 2 -> {
			System.out.println("Indique el ID de la incidencia a eliminar");
			id = new Scanner(System.in).nextLine();
		}
		case 3 -> {
			System.out.println("Indique el ID de la incidencia a modificar");
			id = new Scanner(System.in).nextLine();
		}
		case 4 ->{
			System.out.println("Indique el ID de la incidencia a resolver");
			id = new Scanner(System.in).nextLine();
		}
		
		case 5 -> {
			System.out.println("Indique el ID de la incidencia a eliminar");
			id = new Scanner(System.in).nextLine();
		}
		
		}
		
		return id;
	}
	
	public static void listadoIncidencia(int opcion) {
		switch(opcion) {
		case 6 ->
			System.out.println("Listado por ID:");
		case 7 -> 
			System.out.println("Listado por fecha:");
		}
	}
	
	
}
