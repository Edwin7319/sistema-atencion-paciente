package ServiciosGenerales;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.programacion2.epn.variablesGlobales;

public class validarPaciente {

	public static boolean validarLetras (String letras) {
		boolean letrasCorrecto=false;
		Pattern mayusculas = Pattern.compile("^[A-Za-zñÑ]{1,100}$");
		Matcher matchLetras = mayusculas.matcher(letras);

		if (matchLetras.find()){
			return true;
		}
		return letrasCorrecto;
	}

	public static boolean validarNumero(String numeros) {
		boolean numCorrecto=false;
		Pattern num = Pattern.compile("^[0-9]{1,100}$");
		Matcher matchNum = num.matcher(numeros);

		if(matchNum.find()) {
			return true;
		}
		return numCorrecto;
	}

	public static boolean validarTelefono (String telef) {
		boolean telefCorrecto=false;
		Pattern telefono = Pattern.compile("^[0-9]{2,3}-? ?[0-9]{6,7}$");
		Matcher matchTelf = telefono.matcher(telef);
		if(matchTelf.find() == true) {
			return true;
		}
		return telefCorrecto;
	}

	public static boolean validarEmail (String correo) {
		boolean correoBien=false;
		Pattern mail = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		Matcher matchCorreo = mail.matcher(correo);
		if (matchCorreo.find() == true) {
			return true;
		}
		return correoBien;
	}

	public static boolean validarCedula(String cedula) {
		boolean cedulaCorrecta = false;	 
		try { 
			if (cedula.length() == 10) 
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9,10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					}
					else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} 
		return cedulaCorrecta;
	}

	public static boolean cedulaRepetida(String cedula) {

		boolean repetida=false;

		String cadena3 = "SELECT * FROM persona WHERE cedulaPer = '"+cedula+"' ";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena3);

		try {
			while (rs.next()) {
				repetida=true;
			}
		} catch (Exception u) {}	

		return repetida;
	}

	public static boolean codigoLugGeo(String codigo) {

		boolean codigoRep=false;

		String cadena3 = "SELECT * FROM lugargeo WHERE codigoLugGeo = '"+codigo+"' ";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena3);

		try {
			while (rs.next()) {
				codigoRep=true;
			}
		} catch (Exception u) {
			
		}	

		return codigoRep;
	}

	public static boolean validarLugarNacimiento (String cedula, String numeroProv) {
		boolean coincide=false;
		numeroProv.substring(0, 2);
		int auxiliar = Integer.parseInt(numeroProv.substring(0, 2));

		if(auxiliar <= 24 || auxiliar >=1) {
			int verificador = Integer.parseInt(cedula.substring(0,2));
			if(verificador == auxiliar) {
				coincide=true;
			}
		}
		return coincide;
	}
}


