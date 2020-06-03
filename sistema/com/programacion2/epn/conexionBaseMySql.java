package com.programacion2.epn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class conexionBaseMySql {
	private Connection con;
	/**
	 * @wbp.parser.entryPoint
	 */
	public conexionBaseMySql() {
		String url;
		String server = "localhost";
		String base = "consultaexterna2017b"; 
		String usuario = "root";
		String pass = "edwin7319";
		try{
	        //Register the JDBC driver for MySQL.
	        Class.forName("com.mysql.jdbc.Driver");

	        //Define URL of database server for
	        // database named mysql on the localhost
	        // with the default port number 3306.
	        url ="jdbc:mysql://" + server + "/"+ base;
	        //usar la opcion de reconectar
	        //Get a connection to the database for a
	        // user named root with a blank password.
	        // This user is the default administrator
	        // having full privileges to do anything.
	        url+="?connectTimeout=7000&socketTimeout=7000";
	        DriverManager.setLoginTimeout(1);
	        con =DriverManager.getConnection(url,usuario, pass);
	       
	        
		}
		catch(Exception e ) {
			String 	message="<html><p><b>No se ha podido conectar con la base de datos </b></p>" +
					"<p>Verifique los parametros de conexion o el estado de su conexion</p> ";
			JOptionPane.showMessageDialog(new JFrame(), message);
	    }		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public ResultSet consulta (String queryBusqueda){
		Statement comando;
		ResultSet resultado=null;		
		try{
			comando = con.createStatement();	
			resultado=comando.executeQuery(queryBusqueda);	
		}
			catch( SQLException e ) {				
				e.getSQLState();				
				String message="<html><p><b>La consulta ejecutada fue: </b>" +queryBusqueda+" </p>" +
						"<p><b>Error de Mysql: </b> "+e.getMessage() +  "</p> " +
						"<p><b>Error codigo: </b>" + e.getErrorCode()+" </p></html>";
				System.out.println(queryBusqueda);
				JOptionPane.showMessageDialog(new JFrame(), message);
		}
		return resultado;
	}
	 /**
	  * @wbp.parser.entryPoint
	  */
	 public boolean siguiente (ResultSet rs){
		  try { 	
		   return rs.next();
		  }
		  catch(SQLException error ){
		  	return false;
		  } 
		}
		/**
		 * @wbp.parser.entryPoint
		 */
		public void insertar (String insert){
			Statement comando;
			try{
				comando = con.createStatement();	
				comando.executeUpdate(insert);	
			}
			catch( SQLException error ) {
				
				String message=null;
				message="<html><p><b>Error de Mysql: </b> "+error.getMessage() +  "</p> " +
				"<p><b>Error c�digo: </b>" + error.getErrorCode()+" </p></html>";
				JOptionPane.showMessageDialog(new JFrame(),message);
				
			}//end catch		
	  
	   }
		/**
		 * @wbp.parser.entryPoint
		 */
		public boolean insertarB (String insert){
			Statement comando;
			try{
				comando = con.createStatement();	
				comando.executeUpdate(insert);
				return true;
				
			}
			catch( SQLException error ) {
				return false;
			}	
	  
	   }
	/**
	 * @wbp.parser.entryPoint
	 */
	public String getString (ResultSet datos, String columna){
			
			try{
			return datos.getString(columna);
			}
			catch (SQLException error){
				
				String message="<html><p><b>Error de Mysql: </b> "+error.getMessage() +  "</p> " +
				"<p><b>Error código: </b>" + error.getErrorCode()+" </p></html>";
				JOptionPane.showMessageDialog(new JFrame(),message);
			return (null);
			}			
		} 
	/**
	 * @wbp.parser.entryPoint
	 */
	public java.util.Date obtenerDate (ResultSet datos, String columna){
		
		try{
		return datos.getDate(columna);
		}
		catch (SQLException error){
			
			String message="<html><p><b>Error de Mysql: </b> "+error.getMessage() +  "</p> " +
			"<p><b>Error código: </b>" + error.getErrorCode()+" </p></html>";
			JOptionPane.showMessageDialog(new JFrame(),message);
		return (null);
		}			
	} 
	/**
	 * @wbp.parser.entryPoint
	 */
	public Connection getConeccion(){
		return this.con;
	}
}
