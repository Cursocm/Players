package Controlador;

import Datos.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ControladorJugador {
	private static final String DB_URL = "jdbc:sqlite:C:\\Users\\alumno tarde\\Desktop\\Examen.txt";
	//private static Jugador[] listaJugadores;
	
	//***** Danach Loeschen 	

	public static void insertarJugador(Jugador jugador) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		try (Connection connection = DriverManager.getConnection(DB_URL)) {
			String insertQuery = "INSERT INTO Jugadores (ID, Nombre, Dorsal, Altura) VALUES (?, ?, ?, ?)";
			try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
				insertStatement.setInt(1, jugador.getId());
				insertStatement.setString(2, jugador.getNombre());
				insertStatement.setInt(3, jugador.getDorsal());
				insertStatement.setDouble(4, jugador.getAltura());
				insertStatement.executeUpdate();

			} catch (Exception e) {

				System.out.println("\u001B[32mError: ID ya existe .\u001B[0m");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void borrarJugador(int id) {
		try (Connection connection = DriverManager.getConnection(DB_URL)) {
			String deleteQuery = "DELETE FROM Jugadores WHERE ID = ?";
			try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
				deleteStatement.setInt(1, id);
				deleteStatement.executeUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void actualizarJugador(Jugador jugador) {
		try (Connection connection = DriverManager.getConnection(DB_URL)) {
			String updateQuery = "UPDATE Jugadores SET Nombre = ?, Dorsal = ?, Altura = ? WHERE ID = ?";
			try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
				updateStatement.setString(1, jugador.getNombre());
				updateStatement.setInt(2, jugador.getDorsal());
				updateStatement.setDouble(3, jugador.getAltura());
				updateStatement.setInt(4, jugador.getId());
				updateStatement.executeUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	


	// mirar si ID EXiste
	public static boolean isIDExists( int id) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		try {
			String selectQuery = "SELECT COUNT(*) FROM Jugadores WHERE ID = ?";
			try (Connection connection = DriverManager.getConnection(DB_URL)) {
				var selectStatement = connection.prepareStatement(selectQuery);
				selectStatement.setInt(1, id);
				ResultSet resultSet = selectStatement.executeQuery();
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	//*****************Daten aus der Bank zu bekommen 
	
	public static List<Jugador> obtenerListaJugadores() throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    List<Jugador> listaJugadores = new ArrayList<>();

	    try {
	        String selectQuery = "SELECT ID, Nombre, Dorsal, Altura FROM Jugadores";
	        try (Connection connection = DriverManager.getConnection(DB_URL)) {
	            var selectStatement = connection.prepareStatement(selectQuery);
	            ResultSet resultSet = selectStatement.executeQuery();
	            while (resultSet.next()) {
	                
	            	int id = resultSet.getInt("ID");
	                String nombre = resultSet.getString("Nombre");
	                int dorsal = resultSet.getInt("Dorsal");
	                double altura = resultSet.getDouble("Altura");
	                
	                String message = id + " " + nombre + " " + dorsal + " " + altura;
	                
	                
	                
	                JOptionPane.showMessageDialog(null, message);

	                
	             
	               
	               
	                Jugador jugador = new Jugador(id, nombre, dorsal, altura);
	                listaJugadores.add(jugador);
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    
	   
	    
	    return listaJugadores;
	}
	
	



}
