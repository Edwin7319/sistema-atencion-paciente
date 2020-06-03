package pruebas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.programacion2.epn.variablesGlobales;


import clasesBean.pacienteBean;

public class uno {
	private LocalDate fechaNacimiento, fechaActual;
	private Period periodo;
	
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter formatoFechaNac = DateTimeFormatter.ofPattern(("yyyy-MM-dd"));
	
	pacienteBean obj = new pacienteBean();
	ArrayList<pacienteBean> listaPac;
	
	public void llenarLista() {
		
		listaPac = new ArrayList<pacienteBean>();
		String cadena="";
		cadena = "SELECT pac.*, per.* FROM paciente pac, persona per WHERE per.idPersona = pac.idPersona ";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		
		try {
			listaPac.clear();
			while(rs.next()) {
				obj = new pacienteBean();
				obj.setApellido1Per(rs.getString("apellido1Per"));
				obj.setApellido2Per(rs.getString("apellido2Per"));
				obj.setNombre1Per(rs.getString("nombre1Per"));
				obj.setNombre2Per(rs.getString("nombre2Per"));
				
				fechaNacimiento = LocalDate.parse(rs.getString("fechaNacPac"), formatoFechaNac);
				fechaActual = LocalDate.now();
				periodo = Period.between(fechaNacimiento, fechaActual);
				obj.setEdad(String.valueOf(periodo.getYears()));
				
				listaPac.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void edades() {
		
		int edad=0, edadMayor=-120, edadMenor=120;
		
		for(pacienteBean e: listaPac) {
				
			edad = Integer.parseInt(e.getEdad());
			
			if(edad>edadMayor) {
				edadMayor = edad;
			}
			if(edad<edadMenor) {
				edadMenor = edad;
			}
		}
		
		for (int i=0; i<listaPac.size(); i++) {
			
		}
			
		System.out.println("MAYOR:  "+edadMayor);
		System.out.println("MENOR:  "+edadMenor);
	}
	
	public static void main (String []args) {
		
		uno nuevo = new uno();
		nuevo.llenarLista();
		System.out.println("-------------------------------------------------------------------------");
		nuevo.edades();
	}

}
