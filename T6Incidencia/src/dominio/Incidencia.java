package dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Incidencia implements Comparable, Serializable{

	private Integer id;
	private LocalDate fecha;
	private Estado estado;
	private String problema;
	
	public Incidencia(Integer id, LocalDate fecha, Estado estado, String problema) {
		this.id = id;
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fecha.format(f);//Formateamos la fecha al formato que quremos
		this.fecha = LocalDate.parse(fechaFormateada, f);
		this.estado = estado.PENDIENTE;// por defectos todas las incidencias están en el estado de pendiente
		this.problema = problema;
	}
	
	
	/**
	 * Obtiene el identificador de un objeto
	 * @return devuelve el id
	 */
	public int getId() {
		return id;
	}
	/**
	 * establece un id pasado como parámetro
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProblema() {
		return problema;
	}
	
	/**
	 * Enumerador que indica el estado de la incidencia
	 * @return devuelve el estado
	 */
	public Estado getEstado() {
		return estado;
	}
	/**
	 * Establece el estado pasado por parámetro
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public int antiguedad() {
		return (int) fecha.until(LocalDate.now(),ChronoUnit.YEARS);
	}

	// Establece un orden natural (de menor a mayor)
	@Override
	public int compareTo(Object o) {
		return id.compareTo(((Incidencia)o).id);
	}
	
	@Override
	public boolean equals(Object o) {
		boolean iguales = false;

		if(o!=null && this.id==((Incidencia)o).id){
			iguales = true;
		}

		return iguales;
	}
	
	
	public String toString() {
		return "\nID: "+this.getId()+"\nFecha: "+this.getFecha()+"\nEstado: "+this.getEstado()+"\nProblema: "+this.getProblema();
	}
	
	
}
