package GUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.programacion2.epn.variablesGlobales;

import ServiciosGenerales.*;
import clasesBean.pacienteBean;

import javax.swing.Icon;
import javax.swing.ImageIcon;



public class ImportarPacientesGUI  extends JInternalFrame implements ActionListener {

	private Font nuevaFuente = new Font("Courier", Font.ROMAN_BASELINE, 12);

	private JLabel lblPac, lblArchivo, lblFondo; 
	private JButton btnGrabarTbl, btnCancelar, btnSalir, btnEscoger, btnGrabarArr;
	private JTextField txtNomArchivo;
	private JTextArea areaTextoErrores, areaTextoTabla;
	private JScrollPane desplazar, desplazar2;
	private DefaultTableModel modelo;
	private JTable tblPacientes;

	private String [] arregloDatos =  new String [14]; //{"NOMBRE1","NOMBRE2", "APELLIDO1", "APELLIDO2", "CORREO", "CEDULA", "TELEFONO", "HISTORIA", "SEXO", "DIRECCION","ESTADO CIVIL", "FECHA NACIMIENTO","LUGAR NACIMINETO","LUGAR RECIDENCIA"};

	pacienteBean objPaciente = new pacienteBean();

	private ArrayList<pacienteBean> listaPa = new ArrayList<pacienteBean>();
	
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

	String texto;
	private JLabel label;

	public ImportarPacientesGUI() {
		super ("IMPORTAR PACIENTE");
		setBorder(null);
		getContentPane().setLayout(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

		lblPac = new JLabel("PACIENTE A SUBIR: ");
		lblPac.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPac.setForeground(Color.BLACK);
		lblPac.setBounds(340, 10, 115, 30);
		getContentPane().add(lblPac);
		lblArchivo = new JLabel ("NOMBRE DE ARCHIVO: ");
		lblArchivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArchivo.setForeground(Color.BLACK);
		lblArchivo.setBounds(340, 65, 150, 30);
		getContentPane().add(lblArchivo);


		btnGrabarTbl = new JButton ("GRABAR DESDE TABLA");
		btnGrabarTbl.setBounds(20, 610, 190, 30);
		btnGrabarTbl.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnGrabarTbl);
		btnGrabarTbl.setEnabled(false);
		btnGrabarTbl.addActionListener(this);

		btnGrabarArr=new JButton("GRABAR DESDE LISTA");
		btnGrabarArr.setBounds(233, 610, 190, 30);
		btnGrabarArr.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnGrabarArr);
		btnGrabarArr.setEnabled(false);
		btnGrabarArr.addActionListener(this);

		btnCancelar = new JButton ("CANCELAR");
		btnCancelar.setBounds(437, 610, 100, 30);
		btnCancelar.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(this);

		btnSalir = new JButton ("SALIR");
		btnSalir.setBounds(560, 610, 100, 30);
		btnSalir.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnSalir);
		btnSalir.addActionListener(this);

		btnEscoger = new JButton("ESCOGER ARCHIVO");
		btnEscoger.setBounds(500, 10, 150, 30);
		btnEscoger.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnEscoger);
		btnEscoger.addActionListener(this);


		txtNomArchivo = new JTextField();
		txtNomArchivo.setBounds(500, 66, 150, 22);
		txtNomArchivo.setBackground(new Color(255, 255, 255));
		txtNomArchivo.setEditable(false);
		getContentPane().add(txtNomArchivo);

		areaTextoErrores = new JTextArea();
		areaTextoErrores.setEditable(false);
		areaTextoErrores.setFont(nuevaFuente);
		desplazar = new JScrollPane(areaTextoErrores);
		desplazar.setBounds(20, 100, 940, 150);
		getContentPane().add(desplazar);

		areaTextoTabla = new JTextArea();
		desplazar2=new JScrollPane(areaTextoTabla);
		desplazar2.setBounds(20, 300, 940, 300);
		getContentPane().add(desplazar2);	

		modelo = new DefaultTableModel(){
			public boolean isCellEditable (int fila, int columna) {
				return false;
			}
		};
		tblPacientes = new JTable(modelo);
		tblPacientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		desplazar2.setViewportView(tblPacientes);
		
		
		
		modelo.addColumn("NOMBRE1");
		modelo.addColumn("NOMBRE2");
		modelo.addColumn("APELLIDO1");
		modelo.addColumn("APELLIDO2");
		modelo.addColumn("CORREO");
		modelo.addColumn("CEDULA");
		modelo.addColumn("TELEFONO");
		modelo.addColumn("HISTORIA");
		modelo.addColumn("SEXO");
		modelo.addColumn("DIRECCION");
		modelo.addColumn("ESTADO CIVIL");
		modelo.addColumn("FECHA NACIMIENTO");
		modelo.addColumn("LUGAR NACIMINETO");
		modelo.addColumn("LUGAR RECIDENCIA");

		tblPacientes.getTableHeader().setReorderingAllowed(false) ;


		for(int i=0; i<14;i++) {
			TableColumn columna=tblPacientes.getColumnModel().getColumn(i);
			columna.setPreferredWidth(125);
			columna.setMaxWidth(380);
			columna.setMinWidth(60);

			if(i==4) {
				columna.setPreferredWidth(220);
			}
			if(i==5) {
				columna.setPreferredWidth(90);
			}
			if(i==6) {
				columna.setPreferredWidth(90);
			}
			if(i==7) {
				columna.setPreferredWidth(65);
			}
			if(i==8) {
				columna.setPreferredWidth(60);
			}
			if(i==9) {
				columna.setPreferredWidth(280);
			}
			if(i==10) {
				columna.setPreferredWidth(90);
			}
		}
		///tblPacientes.setRowHeight(25); ancho de celda
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tblPacientes.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tblPacientes.getColumnModel().getColumn(6).setCellRenderer(tcr);
		tblPacientes.getColumnModel().getColumn(8).setCellRenderer(tcr);
		for(int i=10; i<14;i++) {
			tblPacientes.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ImportarPacientesGUI.class.getResource("/Imagenes/11.jpg")));
		lblFondo.setBounds(0, 0, 1000, 700);
		getContentPane().add(lblFondo);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/11.jpg"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}

	public void actionPerformed(ActionEvent evento) {

		if(evento.getSource()==btnSalir){
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}

		if(evento.getSource()==btnCancelar) { 

			//modelo.removeRow(0);
			modelo.setRowCount(0);
			areaTextoErrores.setText("");
			txtNomArchivo.setText("");
			btnGrabarTbl.setEnabled(false);
			btnGrabarArr.setEnabled(false);
		}


		if(evento.getSource()==btnEscoger){

			JFileChooser escogerArchivo = new JFileChooser();
			escogerArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY );  

			int opcionEscogida = escogerArchivo.showSaveDialog( this );

			if ( opcionEscogida == JFileChooser.CANCEL_OPTION ){
				return;
			}

			File nombreArchivo = escogerArchivo.getSelectedFile();
			txtNomArchivo.setText(nombreArchivo.getName());

			if ( nombreArchivo == null || nombreArchivo.getName().equals( "" ) ){
				JOptionPane.showMessageDialog( this,"nombre de archivo inválido", "Error",JOptionPane.ERROR_MESSAGE );
				return;
			}		



			//INFORMACION DE ARCHIVO
			if ( nombreArchivo.exists()) {
				System.out.print(nombreArchivo.getName() + ": Existe.\n"
						+ (nombreArchivo.isFile() ?  nombreArchivo.getName()+": Es un archivo.\n" 
								: nombreArchivo.getName()+": No es un archivo.\n")
						+ (nombreArchivo.isDirectory() ? nombreArchivo.getName()+": Es un directorio.\n"
								: nombreArchivo.getName()+ ": No es un directorio.\n")
						+ (nombreArchivo.isAbsolute() ? nombreArchivo.getName()+": Es una ruta absoluta\n"
								: nombreArchivo.getName()+": No es una ruta absoluta\n")
						+ "Ultima modificacion del archivo: " + nombreArchivo.lastModified()
						+ "\nLongitud del archivo: " + nombreArchivo.length() + "\nRuta del archivo: "
						+ nombreArchivo.getPath() + "\nRuta Absoluta del archivo: "
						+ nombreArchivo.getAbsolutePath() + "\nPadre del archivo:"
						+ nombreArchivo.getParent()+"\n");
			} else {
				JOptionPane.showMessageDialog(this, evento.getActionCommand() + " NO Existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println();
			//entra aca cuando el archivo es valido
			StringTokenizer trozosLinea = null;  

			try {
				BufferedReader datosEntrada = new BufferedReader(new FileReader( nombreArchivo ) );


				while(( texto = datosEntrada.readLine())  != null) {

					String error="";
					trozosLinea= new StringTokenizer(texto, ";");

					arregloDatos[0]= trozosLinea.nextToken(); //Nombre1
					if (arregloDatos[0].length()==1) {
						error += "ERROR Nombre1: se encuentra nulo.\n";
					} else if (!validarPaciente.validarLetras(arregloDatos[0])) {
						error +="ERROR Nombre1: Debe ingresar solo letras.\n";
					}else if (arregloDatos[0].length()<3 || arregloDatos[0].length()>15) {
						error += "ERROR Nombre2: Debe ingresar solo de 3 a 15 letras";
					}


					arregloDatos[1] = trozosLinea.nextToken();
					if (arregloDatos[1].length()==1) {  //Nombre2
					} else if (!validarPaciente.validarLetras(arregloDatos[1])) {
						error += "ERROR Nombre2: Debe ingresar solo letras.\n";
					}else if (arregloDatos[1].length()<3 || arregloDatos[1].length()>15) {
						error += "ERROR Nombre2: Debe ingresar solo de 3 a 15 letras";
					}


					arregloDatos[2]= trozosLinea.nextToken();//Apellido1
					if (arregloDatos[2].length()==1) {
						error += "ERROR Apellido1: Se encuentra nulo.\n";
					} else if (!validarPaciente.validarLetras(arregloDatos[2])) {
						error +="ERROR Apellido1: Debe ingresar solo letras.\n";
					}else if (arregloDatos[2].length()<3 || arregloDatos[2].length()>15) {
						error += "ERROR Apellido1: Debe ingresar solo de 3 a 15 letras";
					}

					arregloDatos[3]= trozosLinea.nextToken();//Apellido2
					if (arregloDatos[3].length()==1) {
					} else if (!validarPaciente.validarLetras(arregloDatos[3])) {
						error +="ERROR Apellido2: Debe ingresar solo letras.\n";
					}else if (arregloDatos[3].length()<3 || arregloDatos[3].length()>15) {
						error += "ERROR Apellido2: Debe ingresar solo de 3 a 15 letras.\n";
					}

					arregloDatos[4]= trozosLinea.nextToken();//Correo
					if(arregloDatos[4].length()==1) {
					} else if(!validarPaciente.validarEmail(arregloDatos[4])) {
						error += "ERROR Correo: Correo ingresado no valido.\n";
					}

					arregloDatos[5]= trozosLinea.nextToken();//Cedula
					if(arregloDatos[5].length()==1) {
						error+="ERROR Cedula: Se encuentra nulo.\n";
					} else if(!validarPaciente.validarCedula(arregloDatos[5])) {
						error += "ERROR Cedula: Cedula ingresada no valida.\n";
					} else if(validarPaciente.cedulaRepetida(arregloDatos[5])) {
						error += "ERROR Cedula: Cedula ingresada ya existe.\n";
					}

					arregloDatos[6]= trozosLinea.nextToken();//Telefono
					if(arregloDatos[6].length()==1) {
						error+="ERROR Telefono: No ha ingresado datos.\n";
					} else if(!validarPaciente.validarNumero(arregloDatos[6])) {
						error+="ERROR Telefono: Debe ingresar solo datos numericos.\n";
					}else if(!validarPaciente.validarTelefono(arregloDatos[6])) {
						error += "ERROR Telefono: Número telefonico no valido.\n";
					}

					arregloDatos[7]= trozosLinea.nextToken(); //Historia Clinica
					if(arregloDatos[7].length()==1) {
						error +="ERROR Historia Clínica: El campo se encuentra vacio.\n";
					}else if(!validarPaciente.validarNumero(arregloDatos[7])) {
						error +="ERROR Historia Clínica: Debe ingresar solo números.\n";
					}else if(arregloDatos[7].trim().length()!=8) {
						error +="ERROR Historia Clínica no valida.\n";
					}

					arregloDatos[8]= trozosLinea.nextToken();//Sexo
					if(arregloDatos[8].length()==0) {
						error +="ERROR Sexo: No ha ingresado valores.\n";
					}else if(!(arregloDatos[8].equals("M")||arregloDatos[8].equals("F"))) {
						error +="ERROR Sexo: Debe ingresar solo masculino o femeino.\n";
					}

					arregloDatos[9]= trozosLinea.nextToken(); //Direccion
					if(arregloDatos[9].length()==1) {
						error +="ERROR Dirección: No ha ingresado valores.\n";
					} 

					arregloDatos[10]= trozosLinea.nextToken(); //Estado Civil
					if(!(arregloDatos[10].equals("S")||arregloDatos[10].equals("C")||arregloDatos[10].equals("V")||arregloDatos[10].equals("D"))) {
						error +="ERROR Estado Civil: El dato ingresado no es valido.\n";
					}

					arregloDatos[11]= trozosLinea.nextToken(); //Fecha de Nacimiento

					arregloDatos[12]= trozosLinea.nextToken(); //Lugar Nacimiento
					if(!validarPaciente.validarNumero(arregloDatos[12])) {
						error +="ERROR id Lugar Nacimiento: Debe ingresar solo números.\n";
					}

					arregloDatos[13]= trozosLinea.nextToken(); //Lugar Recidencia
					if(!validarPaciente.validarNumero(arregloDatos[13])) {
						error +="ERROR id Lugar Residencia: Debe ingresar solo números.\n";
					}

					if(!error.equals("")){
						areaTextoErrores.append("\n");
						for(int i=0; i<arregloDatos.length;i++){
							areaTextoErrores.append(arregloDatos[i]+"  ");
						}
						areaTextoErrores.append("\n"+error);
						continue;
					}


					objPaciente.setNombre1Per(arregloDatos[0].toString().toUpperCase());
					objPaciente.setNombre2Per(arregloDatos[1].toString().toUpperCase());
					objPaciente.setApellido1Per(arregloDatos[2].toString().toUpperCase());
					objPaciente.setApellido2Per(arregloDatos[3].toString().toUpperCase());
					objPaciente.setCorreoPer(arregloDatos[4].toString());
					objPaciente.setCedulaPer(arregloDatos[5].toString());
					objPaciente.setTelefonoPer(arregloDatos[6]);
					objPaciente.setHclPac(arregloDatos[7].toString());
					objPaciente.setSexoPac(arregloDatos[8].toString().toUpperCase());
					objPaciente.setDireccionPac(arregloDatos[9].toString().toUpperCase());
					objPaciente.setEstadoCivilPer(arregloDatos[10].toString().toUpperCase());
					try {
						objPaciente.setFechaNacPac(formato.parse(arregloDatos[11].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					objPaciente.setIdLugGeoNacePac(Integer.parseInt(arregloDatos[12]));
					objPaciente.setIdLugGeoResPac(Integer.parseInt(arregloDatos[13])); 

					arregloDatos[0]=objPaciente.getNombre1Per();
					arregloDatos[1]=objPaciente.getNombre2Per();
					arregloDatos[2]=objPaciente.getApellido1Per();
					arregloDatos[3]=objPaciente.getApellido2Per();
					arregloDatos[4]=objPaciente.getCorreoPer();
					arregloDatos[5]=objPaciente.getCedulaPer();
					arregloDatos[6]=String.valueOf(objPaciente.getTelefonoPer());
					arregloDatos[7]=objPaciente.getHclPac();
					arregloDatos[8]=objPaciente.getSexoPac();
					arregloDatos[9]=objPaciente.getDireccionPac();
					arregloDatos[10]=objPaciente.getEstadoCivilPer();
					arregloDatos[11]=formato.format(objPaciente.getFechaNacPac());
					arregloDatos[12]=String.valueOf(objPaciente.getIdLugGeoNacePac());
					arregloDatos[13]=String.valueOf(objPaciente.getIdLugGeoResPac());

					modelo.addRow(arregloDatos);
				}
			}			
			catch ( IOException ioException ) {
				JOptionPane.showMessageDialog( this,"Error abriendo archivo", "Error",JOptionPane.ERROR_MESSAGE );
			}

			btnGrabarTbl.setEnabled(true);
			btnGrabarArr.setEnabled(true);

		}

		if(evento.getSource()==btnGrabarTbl) {

			System.out.println("\n");

			for(int i=0;i<tblPacientes.getRowCount();i++){

				objPaciente.setNombre1Per(tblPacientes.getValueAt(i, 0).toString().toUpperCase());
				objPaciente.setNombre2Per(tblPacientes.getValueAt(i, 1).toString().toUpperCase());
				objPaciente.setApellido1Per(tblPacientes.getValueAt(i, 2).toString().toUpperCase());
				objPaciente.setApellido2Per(tblPacientes.getValueAt(i, 3).toString().toUpperCase());
				objPaciente.setCorreoPer(tblPacientes.getValueAt(i, 4).toString());
				objPaciente.setCedulaPer(tblPacientes.getValueAt(i, 5).toString());
				objPaciente.setTelefonoPer(tblPacientes.getValueAt(i, 6).toString());
				objPaciente.setHclPac(tblPacientes.getValueAt(i, 7).toString());
				objPaciente.setSexoPac(tblPacientes.getValueAt(i, 8).toString().toUpperCase());
				objPaciente.setDireccionPac(tblPacientes.getValueAt(i, 9).toString().toUpperCase());
				objPaciente.setEstadoCivilPer(tblPacientes.getValueAt(i, 10).toString().toUpperCase());
				try {
					objPaciente.setFechaNacPac(formato.parse(tblPacientes.getValueAt(i, 11).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				objPaciente.setIdLugGeoNacePac(Integer.parseInt(tblPacientes.getValueAt(i, 12).toString()));
				objPaciente.setIdLugGeoResPac(Integer.parseInt(tblPacientes.getValueAt(i, 13).toString())); 

				String cadena="";
				cadena = "INSERT INTO persona (nombre1Per, nombre2Per, apellido1Per,apellido2Per,correoPer,cedulaPer,telefonoPer) VALUES ('"+objPaciente.getNombre1Per()+"','"+objPaciente.getNombre2Per()+"','"+objPaciente.getApellido1Per()+"','"+objPaciente.getApellido2Per()+"','"+objPaciente.getCorreoPer()+"','"+objPaciente.getCedulaPer()+"','"+objPaciente.getTelefonoPer()+"')";
				variablesGlobales.conexionBaseMySql.insertar(cadena);
				//System.out.println(cadena);

				//hacer un select para recuperar el idPERSONA
				cadena = "SELECT * FROM persona WHERE cedulaPer = '"+objPaciente.getCedulaPer()+"' ";
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

				try {
					while (rs.next()) {
						//colocar el iDPersona en el objeto bean
						objPaciente.setIdPersona(Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
						//System.out.println("Valor de idPersona: "+variablesGlobales.conexionBaseMySql.getString(rs, "idPersona"));					
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se pudo obtner ID persona");
				}

				//hacer el insert a paciente a traves de id persona 
				cadena = "INSERT INTO paciente (hclPac, sexoPac, direccionPac, estadoCivilPac, fechaNacPac, idLugGeoNacePac, idLugGeoResidPac, idPersona) VALUES ('"+objPaciente.getHclPac() +"','"+objPaciente.getSexoPac()+"','"+objPaciente.getDireccionPac()+"','"+objPaciente.getEstadoCivilPer()+"','"+formato.format(objPaciente.getFechaNacPac())+"',"+objPaciente.getIdLugGeoNacePac()+","+objPaciente.getIdLugGeoResPac()+","+objPaciente.getIdPersona()+")";
				variablesGlobales.conexionBaseMySql.insertar(cadena);
				//System.out.println(cadena); 

				//System.out.println(objPaciente.getNombre1Per()+" "+objPaciente.getNombre2Per()+" "+objPaciente.getApellido1Per()+" "+objPaciente.getApellido2Per()+" "+objPaciente.getCorreoPer()+" "+objPaciente.getCedulaPer()+" "+objPaciente.getTelefonoPer());

			}

			JOptionPane.showMessageDialog(null, "Se agrego correctamente desde la tabla");
			btnCancelar.doClick();
		}


		if(evento.getSource()==btnGrabarArr) {

			for (int i=0; i<tblPacientes.getRowCount(); i++) {

				objPaciente = new pacienteBean ();	

				objPaciente.setNombre1Per(tblPacientes.getValueAt(i, 0).toString().toUpperCase());
				objPaciente.setNombre2Per(tblPacientes.getValueAt(i, 1).toString().toUpperCase());
				objPaciente.setApellido1Per(tblPacientes.getValueAt(i, 2).toString().toUpperCase());
				objPaciente.setApellido2Per(tblPacientes.getValueAt(i, 3).toString().toUpperCase());
				objPaciente.setCorreoPer(tblPacientes.getValueAt(i, 4).toString());
				objPaciente.setCedulaPer(tblPacientes.getValueAt(i, 5).toString());
				objPaciente.setTelefonoPer(tblPacientes.getValueAt(i, 6).toString());
				objPaciente.setHclPac(tblPacientes.getValueAt(i, 7).toString());
				objPaciente.setSexoPac(tblPacientes.getValueAt(i, 8).toString().toUpperCase());
				objPaciente.setDireccionPac(tblPacientes.getValueAt(i, 9).toString().toUpperCase());
				objPaciente.setEstadoCivilPer(tblPacientes.getValueAt(i, 10).toString().toUpperCase());
				try {
					objPaciente.setFechaNacPac(formato.parse(tblPacientes.getValueAt(i, 11).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				objPaciente.setIdLugGeoNacePac(Integer.parseInt(tblPacientes.getValueAt(i, 12).toString()));
				objPaciente.setIdLugGeoResPac(Integer.parseInt(tblPacientes.getValueAt(i, 13).toString())); 

				listaPa.add(objPaciente);
			}

			for (pacienteBean e: listaPa) {

				/*System.out.println(e.getNombre1Per()+";"+e.getNombre2Per()+";"+e.getApellido1Per()+";"+e.getApellido2Per()+";"+e.getCorreoPer()+";"+e.getCedulaPer()+";"+e.getTelefonoPer()
				+";"+e.getHclPac()+";"+e.getSexoPac()+";"+e.getDireccionPac()+";"+e.getEstadoCivilPer()+";"+e.getFechaNacPac()+";"+e.getIdLugGeoNacePac()+";"+e.getIdLugGeoResPac());*/

				String cadena="";
				cadena = "INSERT INTO persona (nombre1Per, nombre2Per, apellido1Per,apellido2Per,correoPer,cedulaPer,telefonoPer) VALUES ('"+e.getNombre1Per()+"','"+e.getNombre2Per()+"','"+e.getApellido1Per()+"','"+e.getApellido2Per()+"','"+e.getCorreoPer()+"','"+e.getCedulaPer()+"','"+e.getTelefonoPer()+"')";
				variablesGlobales.conexionBaseMySql.insertar(cadena);
				System.out.println(cadena);

				//hacer un select para recuperar el idPERSONA
				cadena = "SELECT * FROM persona WHERE cedulaPer = '"+e.getCedulaPer()+"' ";
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

				try {
					while (rs.next()) {
						//colocar el iDPersona en el objeto bean
						e.setIdPersona(Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
						System.out.println("Valor de idPersona: "+variablesGlobales.conexionBaseMySql.getString(rs, "idPersona"));					
					}
				} catch (Exception u) {
					JOptionPane.showMessageDialog(null, "No se pudo obtner ID persona");
				}

				//hacer el insert a paciente a traves de id persona 
				cadena = "INSERT INTO paciente (hclPac, sexoPac, direccionPac, estadoCivilPac, fechaNacPac, idLugGeoNacePac, idLugGeoResidPac, idPersona) VALUES ('"+e.getHclPac() +"','"+e.getSexoPac()+"','"+e.getDireccionPac()+"','"+e.getEstadoCivilPer()+"','"+formato.format(e.getFechaNacPac())+"',"+e.getIdLugGeoNacePac()+","+e.getIdLugGeoResPac()+","+e.getIdPersona()+")";
				variablesGlobales.conexionBaseMySql.insertar(cadena);
				System.out.println(cadena); 

			}
			JOptionPane.showMessageDialog(null, "Se agrego correctamente desde la lista");
			btnCancelar.doClick();
		} 
	}


	public static void main(String[] args) {

		ImportarPacientesGUI capturar = new ImportarPacientesGUI ();
		capturar.setBounds(0,0,1000,700);
		capturar.setVisible(true);
		//capturar.setLocationRelativeTo(null); //Aparezaca al centro de pantalla
		capturar.setResizable(false); //no deja modificar el tamaño de la interfaz
	}
}
