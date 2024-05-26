package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dominio.Estado;
import dominio.Incidencia;

public class IncidenciaDAO {
	
	private Connection conexion;
	private final String USUARIO="pepe";
	private final String PASSWORD="12345";
	private final String MAQUINA="localhost";
	private final String BD="t6incidencias";
	
	
	public IncidenciaDAO() {
		conexion = conectar();
	}
	
	//Crea una conexion con el SGBD y la devuelve
	private Connection conectar() {
		Connection con = null;
		String url = "jdbc:mysql://"+MAQUINA+"/"+BD;
		
		try {
			con = DriverManager.getConnection(url,USUARIO,PASSWORD);
		} catch(SQLException e) {
			System.out.println("Error al conectar con la base de datos");
			e.printStackTrace();
		}
		return con;
	}
	
	//Cierra conexion
	public void cerrarConexion() {
		try {
			this.conexion.close();
		}catch(SQLException e) {
			System.out.println("Error al cerrar conexion");
			e.printStackTrace();
		}
	}
	
	//Método que busca una incidencia
	public Incidencia buscaIncidencia(int id) {
		Incidencia incidencia = null;
		String sql = "SELECT * FROM incidencia WHERE id = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(sql)){
			sentencia.setInt(1, id);
			try (ResultSet rs = sentencia.executeQuery()){
				if(rs.next()) {
					Integer idIncidencia = rs.getInt("id");
					Date fecha = rs.getDate("fecha");
					String estado = rs.getString("estado");
					String problema = rs.getString("problema");
					
					incidencia = new Incidencia(idIncidencia,fecha.toLocalDate(),Estado.valueOf(estado),problema);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return incidencia;
	}
	
	
	//Inserta una incidencia en la BD
	public void create(Incidencia inci) {
		
		if(inci!=null) {
			String sql = "INSERT INTO incidencia(id,fecha,estado,problema) VALUES(?,?,?,?)";
			try(PreparedStatement sentencia = conexion.prepareStatement(sql)){
				sentencia.setInt(1, inci.getId());
				sentencia.setDate(2, Date.valueOf(inci.getFecha()));
				sentencia.setString(3, String.valueOf(inci.getEstado()));
				sentencia.setString(4, inci.getProblema());
				sentencia.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al insertar datos");
				e.printStackTrace();
			}
		}
		
	}
	
	//Elimina una incidencia en la BD
	public void delete(Integer id) {
		String sql = "DELETE FROM incidencia WHERE id = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(sql)){
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Error al eliminar una incidencia en la BD");
			e.printStackTrace();
		}
	}
	
	//Método que actualiza los datos
	public void update(Incidencia inci) {
		if(inci != null) {
			String sql = "UPDATE incidencia set id=?,fecha=?,estado=?,problema=?";
			try (PreparedStatement sentencia = conexion.prepareStatement(sql)){
				sentencia.setInt(1, inci.getId());
				sentencia.setDate(2, Date.valueOf(inci.getFecha()));
				sentencia.setString(3, String.valueOf(inci.getEstado()));
				sentencia.setString(4, inci.getProblema());
				
				sentencia.executeUpdate();
			} catch(SQLException e) {
				System.out.println("Error al actualizar los datos");
				e.printStackTrace();
			}
		}
	}
	
	
	//Método que devuelve un listado
	public ArrayList<Incidencia> listaIncidencia(){
		ArrayList<Incidencia> lista = new ArrayList<>(4);
		Incidencia inci = null;
		String sql = "SELECT * FROM incidencia";
		try (Statement sentencia = conexion.createStatement();
				ResultSet rs = sentencia.executeQuery(sql)){
			while(rs.next()) {
				Integer idIncidencia = rs.getInt("id");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				String estado = rs.getString("estado");
				String problema = rs.getString("problema");
				
				inci = new Incidencia(idIncidencia,fecha,Estado.valueOf(estado),problema);
				lista.add(inci);
			}
			
		}catch (SQLException e) {
			System.out.println("Error al listar las incidencias");
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
}
