package aplicacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dominio.Estado;
import dominio.Incidencia;
import persistencia.IncidenciaDAO;
import presentacion.InterfazUsuario;

public class Logica {
	
	
	/**
	 * Método que contiene la estructura del programa principal
	 * @param opcion introducida por el usuario
	 * @param dao objeto de la clase IncidenciaDAO, con sus métodos
	 */
	public static void ejecutaOpcion(int opcion, IncidenciaDAO dao) {
		
		switch(opcion){
		case 1 -> {
			String datos[] = InterfazUsuario.pedirDatos();
			boolean operacionPosible = altaIncidencia(dao,datos);
			InterfazUsuario.resultado(opcion, operacionPosible);
		}
		case 2 -> {
			String id = InterfazUsuario.indicaIdIncidencia(opcion);
			boolean operacionPosible = bajaIncidencia(dao,Integer.parseInt(id));
			InterfazUsuario.resultado(opcion, operacionPosible);
		}
		
		case 3 -> {
			String id = InterfazUsuario.indicaIdIncidencia(opcion);
			boolean operacionPosible = modificaIncidencia(Integer.valueOf(id),dao);
			InterfazUsuario.resultado(opcion, operacionPosible);
		}
		
		case 4 -> {
			String id = InterfazUsuario.indicaIdIncidencia(opcion);
			boolean operacionPosible = incidenciaResuelta(Integer.valueOf(id),dao);
			InterfazUsuario.resultado(opcion, operacionPosible);
		}
		
		case 5 -> {
			String id = InterfazUsuario.indicaIdIncidencia(opcion);
			boolean operacionPosible = incidenciaEliminada(Integer.valueOf(id),dao);
			InterfazUsuario.resultado(opcion, operacionPosible);
		}
		
		case 6,7 ->{
			boolean operacionCorrecta = listarIncidencias(opcion, dao);
			InterfazUsuario.resultado(opcion, operacionCorrecta);
		}
		
		case 8 -> {
			System.out.println("¡¡¡Hasta la próxima!!!");
		}
		
		}
		
	}
	
	/**
	 * Método que da de alta una incidencia
	 * @param dao objeto de la clase IncidenciaDAO
	 * @param datosAltaIncidencia un array de tipo String que recibe la capa de interfaz los datos
	 * @return retorna un boolean
	 */
	public static boolean altaIncidencia(IncidenciaDAO dao, String[] datosAltaIncidencia) {
		//Por defecto la operacion es posible
		boolean operacionPosible = true;
		String id = datosAltaIncidencia[0];
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//Si el id no existe se crea una nueva incidencia
		if(dao.buscaIncidencia(Integer.valueOf(id))==null) {
			LocalDate fecha = LocalDate.parse(datosAltaIncidencia[1],f);
			String problema = datosAltaIncidencia[2];
			dao.create(new Incidencia(Integer.valueOf(id),fecha,Estado.PENDIENTE,problema));
		} else operacionPosible = false;
		
		return operacionPosible;
	}
	
	/**
	 * Método que permite dar de baja una incidencia
	 * @param dao objeto de la clase IncidenciaDAO
	 * @param id identificador de la incidencia
	 * @return retorna un boolean
	 */
	public static boolean bajaIncidencia(IncidenciaDAO dao,Integer id) {
		boolean operacionPosible = false;
		Incidencia inci = dao.buscaIncidencia(id);
		if(inci != null) {
			dao.delete(id);
			operacionPosible = true;
		}
		return operacionPosible;
	}
	
	
	/**
	 * Método que modifica todos los atributos de una incidencia
	 * @param id identificador de la incidencia
	 * @param dao objeto de la clase IncidenciaDAO
	 * @return retorna un boolean
	 */
	public static boolean modificaIncidencia(Integer id, IncidenciaDAO dao) {
		boolean operacionPosible = false;
		Incidencia inci = dao.buscaIncidencia(id);
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		if(inci != null) {
			String[] datos = {String.valueOf(inci.getFecha()),inci.getProblema()};
			datos = InterfazUsuario.modificaAtributo(datos);
			dao.update(new Incidencia(Integer.valueOf(datos[0]),LocalDate.parse(datos[1],f),Estado.PENDIENTE,datos[2]));
			operacionPosible = true;
		}
		return operacionPosible;
	}
	
	public static boolean incidenciaResuelta(Integer id, IncidenciaDAO dao) {
		boolean operacionPosible=false;
		Incidencia inci = dao.buscaIncidencia(id);
		
		if(inci!=null) {
			inci.setEstado(Estado.RESUELTO);
			dao.update(inci);
			operacionPosible = true;
		}
		return operacionPosible;
	}
	
	/**
	 * Método que elimina una incidencia
	 * @param id identificador de la incidencia
	 * @param dao objeto de la clase IncidenciaDAO
	 * @return retorna un boolean
	 */
	public static boolean incidenciaEliminada(Integer id, IncidenciaDAO dao) {
		boolean operacionPosible=false;
		Incidencia inci = dao.buscaIncidencia(id);
		
		if(inci!=null) {
			inci.setEstado(Estado.ELIMINADO);
			dao.update(inci);
			operacionPosible = true;
		}
		return operacionPosible;
	}
	
	
	/**
	 * Método que permite listar las incidencias según la opción marcada por el usuario
	 * @param opcion introducida por el usuario
	 * @param dao objeto de la clase IncidenciaDAO
	 * @return retorna un boolean
	 */
	public static boolean listarIncidencias(int opcion, IncidenciaDAO dao) {
		boolean operacionPosible = false;
		ArrayList<Incidencia> lista = dao.listaIncidencia();
		
		
		if(lista!=null && !lista.isEmpty()) {
			InterfazUsuario.listadoIncidencia(opcion);
			
			Comparator<Incidencia> c = new Comparator<>() {
				@Override
				public int compare(Incidencia i1, Incidencia i2) {
					return (i2.antiguedad()-i1.antiguedad());
				}
			};
			
			switch(opcion) {
			case 6->
				Collections.sort(lista);
				
			case 7 -> 
				Collections.sort(lista,c);
			}
			System.out.println(lista);
			operacionPosible = true;
			
		}
		return operacionPosible;
		
	}

}
