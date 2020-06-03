package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.programacion2.epn.variablesGlobales;
import com.programacion2.epn.utilitarios.LanzadorReportes;
import com.sun.org.apache.xerces.internal.impl.xs.models.CMBuilder;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ReportesGUI extends JInternalFrame implements ActionListener{

	private JLabel lblMedico, lblEspecialidad, lblSexo, lblFondo,lblTipoRepor;
	private JButton btnAceptar, btnSalir, btnCancelar;
	private JComboBox cmbEspecialidad, cmbMedicos,cmbOpciones;
	private JRadioButton rbtnMasculino, rbtnFemenino;
	private ButtonGroup btnGr;

	private ArrayList <String> listaMedico, listaEspe, listaMed, listaGrup, listaEsp;
	private String opciones[]={"Seleccione reporte","Listado","Grafico","Tabla de Cruce"};

	String acumuladorPacientes="";

	public ReportesGUI() {

		super ("REPORTES");
		getContentPane().setLayout(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

		lblEspecialidad = new JLabel("ESPECIALIDAD: ");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEspecialidad.setBounds(69, 30, 78, 14);
		getContentPane().add(lblEspecialidad);
		lblMedico = new JLabel("MEDICO:");
		lblMedico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMedico.setBounds(69, 75, 78, 14);
		getContentPane().add(lblMedico);
		lblTipoRepor = new JLabel("TIPO DE REPORTE");
		lblTipoRepor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoRepor.setBounds(69,120,78,14);
		getContentPane().add(lblTipoRepor);
		lblSexo = new JLabel("SEXO:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSexo.setBounds(69, 165, 55, 14);
		getContentPane().add(lblSexo);

		btnGr = new ButtonGroup();
		rbtnMasculino = new JRadioButton("M");
		rbtnMasculino.setBounds(181, 153, 43, 23);
		rbtnMasculino.addActionListener(this);
		getContentPane().add(rbtnMasculino);
		btnGr.add(rbtnMasculino);
		rbtnFemenino = new JRadioButton("F");
		rbtnFemenino.setBounds(233, 153, 55, 23);
		rbtnFemenino.addActionListener(this);
		getContentPane().add(rbtnFemenino);
		btnGr.add(rbtnFemenino);

		btnAceptar = new JButton("GENERAR");
		btnAceptar.setBounds(69, 193, 89, 23);
		btnAceptar.addActionListener(this);
		btnAceptar.setBackground(new Color(255,255,255));
		getContentPane().add(btnAceptar);
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(180, 193, 96, 23);
		btnCancelar.addActionListener(this);
		btnCancelar.setBackground(new Color(255,255,255));
		getContentPane().add(btnCancelar);
		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(300, 193, 89, 23);
		btnSalir.setBackground(new Color(255,255,255));
		btnSalir.addActionListener(this);
		getContentPane().add(btnSalir);

		cmbEspecialidad = new JComboBox();
		cmbEspecialidad.setBounds(150, 30, 235, 20);
		cmbEspecialidad.setBackground(new Color(255,255,255));
		llenarEspecialidad(cmbEspecialidad);
		getContentPane().add(cmbEspecialidad);
		cmbEspecialidad.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				llenarMedico(cmbEspecialidad, cmbMedicos);
			}
		});

		cmbMedicos = new JComboBox();
		cmbMedicos.setBounds(150, 73, 235, 20);
		cmbMedicos.setBackground(new Color(255,255,255));
		getContentPane().add(cmbMedicos);

		cmbOpciones = new JComboBox(opciones);
		cmbOpciones.setBounds(150,113,235,20);
		cmbOpciones.setBackground(new Color(255,255,255));
		getContentPane().add(cmbOpciones);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(RegistrarLugarGeoGUI.class.getResource("/Imagenes/13.jpg")));
		lblFondo.setBounds(0, 0, 465, 300);
		getContentPane().add(lblFondo);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/13.jpg"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);

	}

	public void llenarEspecialidad(JComboBox cboEspe) {

		listaEspe = new ArrayList<>();
		listaEspe.add("Seleccione Especialidad");

		String Aux="";

		String cadena= "SELECT esp.descripcionEsp  FROM especialidad esp order by descripcionEsp";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs.next()) {
				Aux="";

				Aux += rs.getString("descripcionEsp");

				listaEspe.add(Aux);
			}
		} catch (Exception u) {}

		for (String e: listaEspe) {
			cboEspe.addItem(e);	
		}
	}
	public void llenarMedico(JComboBox esp, JComboBox med) {

		med.removeAllItems();
		listaEsp = new ArrayList<String>();
		listaEsp.add("Seleccione Medico");

		String cadena="",aux="",especialidad="", idEsp="", idPersona="";

		especialidad = (String) esp.getSelectedItem();

		cadena = "SELECT * FROM especialidad WHERE descripcionEsp = '"+especialidad+"' ";
		ResultSet  rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while(rs.next()) {
				idEsp = rs.getString("idEspecialidad");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}

		cadena = "SELECT * FROM empleado WHERE idEspecialidad = '"+idEsp+"' ";
		ResultSet  rs2 = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while(rs2.next()) {
				idPersona = rs2.getString("idPersona");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}

		cadena = "SELECT nombre1Per, apellido1Per FROM empleado e, persona p where e.idEspecialidad = '"+idEsp+"' and p.idPersona = '"+idPersona+"'  ";	
		ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try{	
			while (rs1.next()) {
				aux+=rs1.getString("nombre1Per")+" ";
				aux+=rs1.getString("apellido1Per");
				listaEsp.add(aux);
			}
		}catch (Exception u) {}
		for (String e: listaEsp) {
			med.addItem(e);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()== btnCancelar) {
			cmbEspecialidad.setSelectedIndex(0);
			cmbMedicos.removeAllItems();
			cmbOpciones.setSelectedIndex(0);
			btnGr.clearSelection();
		}

		if(e.getSource()==btnAceptar) {

			boolean correcto=false;
			while (correcto==false) {	
				correcto = true;
				String error="";

				if(cmbOpciones.getSelectedIndex()==0) {
					error+="No ha seleccionado  tipo de reporte.\n";
					correcto=false;
				}
				if(cmbEspecialidad.getSelectedIndex()>0 && cmbMedicos.getSelectedIndex()==0) {
					error+="No ha seleccionado medico.\n";
					correcto=false;
				}
				if (!error.equals("")){
					JOptionPane.showMessageDialog(null, error , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}

			int tReporte = cmbOpciones.getSelectedIndex();
			if(tReporte>0){
				switch (tReporte){
				case 1:{
					String sexo="";
					if(rbtnFemenino.isSelected()||rbtnMasculino.isSelected()){
						if(rbtnFemenino.isSelected()){
							sexo = "F";
						}else{
							sexo="M";
						}
					}

					int especialidad=0, medico=0;
					String cadena="", subtitulo="";

					cadena+="SELECT p.*, pe.*,a.idPaciente, a.idEmpleado, a.fechaAte FROM paciente p, persona pe, atencion a, empleado e WHERE p.idpersona=pe.idpersona and a.idPaciente=p.idPaciente and a.idEmpleado=e.idEmpleado and a.idPaciente=p.idPaciente ";

					if(!sexo.equals("")) {
						cadena+=" and sexoPac ='"+sexo+"'";
						subtitulo+="   POR SEXO  ";
					}

					String cadenaConsulta="";
					String nomMed="", apeMed="", med="",idPerMed="",idEmp="";
					med = (String) cmbMedicos.getSelectedItem();

					if(cmbMedicos.getSelectedIndex()> 0) {

						StringTokenizer tokens = new StringTokenizer(med, " ");
						while(tokens.hasMoreTokens()) {
							nomMed=tokens.nextToken();
							apeMed=tokens.nextToken();
						}

						cadenaConsulta = "SELECT * FROM persona WHERE nombre1Per='"+nomMed.trim()+"' and apellido1Per= '"+apeMed.trim()+"' ";
						ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadenaConsulta);
						try {
							while(rs.next()) {
								idPerMed = rs.getString("idPersona");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
						}

						cadenaConsulta = "SELECT * FROM empleado WHERE idPersona = '"+idPerMed+"' ";
						ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadenaConsulta);
						try {
							while(rs1.next()) {
								idEmp = rs1.getString("idEmpleado");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
						}

						cadena += " and a.idEmpleado='"+idEmp+"' ";
						subtitulo+="     POR MEDICO  ";
					}

					if(subtitulo.equals(""))
					subtitulo = "completa";
					HashMap parametros= new HashMap();
					parametros.put("seleccion", cadena);
					parametros.put("subtitulo", subtitulo);
					LanzadorReportes lanzadorReportes=new LanzadorReportes(new JFrame(),"Reporte");
					lanzadorReportes.cargarReporte("reportes/listaPacientes.jasper",parametros,variablesGlobales.conexionBaseMySql.getConeccion() );
					lanzadorReportes.setSize(new Dimension(800,600));
					lanzadorReportes.setLocationRelativeTo(null);
					lanzadorReportes.show(true);
					break;
				}
				case 2:{
					String cadena ="";
					String cadenaConsulta="";
					String nomMed="", apeMed="", med="",idPerMed="",idEmp="";
					med = (String) cmbMedicos.getSelectedItem();
					cadena += "SELECT sexoPac as Sexo,count(paciente.idPaciente) as PPS FROM paciente, atencion, empleado, especialidad where atencion.idPaciente = paciente.idPaciente and atencion.idEmpleado=empleado.idEmpleado and especialidad.idEspecialidad=empleado.idEspecialidad ";
					String sexo="";
					if(rbtnFemenino.isSelected()||rbtnMasculino.isSelected()){
						if(rbtnFemenino.isSelected()){
							sexo = "F";
						}else{
							sexo="M";
						}
					}
					if(!sexo.equals("")) {
						cadena+=" and sexoPac ='"+sexo+"'";
					}
					if(cmbMedicos.getSelectedIndex()> 0) {

						StringTokenizer tokens = new StringTokenizer(med, " ");
						while(tokens.hasMoreTokens()) {
							nomMed=tokens.nextToken();
							apeMed=tokens.nextToken();
						}
						cadenaConsulta = "SELECT * FROM persona WHERE nombre1Per='"+nomMed.trim()+"' and apellido1Per= '"+apeMed.trim()+"' ";
						ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadenaConsulta);
						try {
							while(rs.next()) {
								idPerMed = rs.getString("idPersona");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
						}

						cadenaConsulta = "SELECT * FROM empleado WHERE idPersona = '"+idPerMed+"' ";
						ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadenaConsulta);
						try {
							while(rs1.next()) {
								idEmp = rs1.getString("idEmpleado");
							}
						} catch (SQLException e1) {
						}

						cadena+=" and atencion.idEmpleado='"+idEmp+"'";
					}
					cadena+=" group by Sexo";
					HashMap parametros= new HashMap();
					parametros.put("seleccion", cadena);
					LanzadorReportes lanzadorReportes=new LanzadorReportes(new JFrame(),"Reporte");
					lanzadorReportes.cargarReporte("reportes/pie.jasper",parametros,variablesGlobales.conexionBaseMySql.getConeccion() );
					lanzadorReportes.setSize(new Dimension(800,600));
					lanzadorReportes.setLocationRelativeTo(null);
					lanzadorReportes.show(true);
					break;
				}
				case 3:{
					String cadena ="";
					String sexo="";
					cadena+="SELECT concat(apellido1Per ,'  ',nombre1Per) as nombreMedico, pro.descripcionLugGeo ,count(atencion.idAtencion) as NAte FROM paciente, empleado, especialidad, persona, atencion, lugargeo can, lugargeo pro, lugargeo parr  where empleado.idpersona=persona.idPersona  and empleado.idEspecialidad=especialidad.idEspecialidad and atencion.idPaciente=paciente.idpaciente and atencion.idEmpleado=empleado.idEmpleado and paciente.idLugGeoResidPac = parr.idLugarGeo  and parr.idLugarGeoPadre=can.idLugarGeo and can.idLugarGeoPadre=pro.idLugarGeo  ";
					if(rbtnFemenino.isSelected()||rbtnMasculino.isSelected()){
						if(rbtnFemenino.isSelected()){
							sexo = "F";
						}else{
							sexo="M";
						}
					}
					if(!sexo.equals("")) {
						cadena+=" and sexoPac ='"+sexo+"'";
					}
					String cadenaConsulta="";
					String nomMed="", apeMed="", med="",idPerMed="",idEmp="";
					med = (String) cmbMedicos.getSelectedItem();

					if(cmbMedicos.getSelectedIndex()>0) {

						StringTokenizer tokens = new StringTokenizer(med, " ");
						while(tokens.hasMoreTokens()) {
							nomMed=tokens.nextToken();
							apeMed=tokens.nextToken();
						}
						cadenaConsulta = "SELECT * FROM persona WHERE nombre1Per='"+nomMed.trim()+"' and apellido1Per= '"+apeMed.trim()+"' ";
						ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadenaConsulta);
						try {
							while(rs.next()) {
								idPerMed = rs.getString("idPersona");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
						}

						cadenaConsulta = "SELECT * FROM empleado WHERE idPersona = '"+idPerMed+"' ";
						ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadenaConsulta);
						try {
							while(rs1.next()) {
								idEmp = rs1.getString("idEmpleado");
							}
						} catch (SQLException e1) {
						}

						cadena+=" and empleado.idEmpleado='"+idEmp+"'";
					}
					cadena+=" group by nombreMedico";
					
					HashMap parametros= new HashMap();
					parametros.put("seleccion", cadena);
					LanzadorReportes lanzadorReportes=new LanzadorReportes(new JFrame(),"Reporte");
					lanzadorReportes.cargarReporte("reportes/tabal2.jasper",parametros,variablesGlobales.conexionBaseMySql.getConeccion() );
					lanzadorReportes.setSize(new Dimension(800,600));
					lanzadorReportes.setLocationRelativeTo(null);
					lanzadorReportes.show(true);
					break;
				}
				}
			}

		}

		if(e.getSource()==btnSalir){
			try {
				this.setClosed(true);
			} catch (PropertyVetoException er) {
				er.printStackTrace();
			}
		}
	}

}