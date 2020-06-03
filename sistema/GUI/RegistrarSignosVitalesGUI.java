package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.programacion2.epn.variablesGlobales;

import clasesBean.Atencion;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RegistrarSignosVitalesGUI extends  JInternalFrame implements ActionListener{

	private JLabel lblFecha, lblPaciente, lblMedico,lblSigVit, lblFondo ;
	private JTextField txtFecha,txtidPaciente, txtidMedico;
	private JComboBox comboPaciente,comboMedico ;
	private JTextArea areaTextoSigVit;
	private JButton btnGuardar,btnCancelar,btnSalir;
	private JScrollPane desplazar;
	private Calendar fechaAct;

	Atencion objAtencion = new Atencion();

	private ArrayList <Atencion> listaAten = new ArrayList<Atencion>();
	private ArrayList <String> listaMedico, listaPaciente;
	

	public RegistrarSignosVitalesGUI() {

		super ("REGISTRAR SIGNOS VITALES");
		setBorder(null);
		getContentPane().setLayout(null); //Indica al programa que se usa elementos con coordenadas
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBounds(76, 42, 73, 14);
		getContentPane().add(lblFecha);
		lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPaciente.setBounds(76, 97, 73, 14);
		getContentPane().add(lblPaciente);
		lblMedico = new JLabel("Medico:");
		lblMedico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedico.setBounds(76, 151, 73, 14);
		getContentPane().add(lblMedico);
		lblSigVit = new JLabel("Signos Vitales");
		lblSigVit.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSigVit.setBounds(214, 227, 133, 14);
		getContentPane().add(lblSigVit);

		txtFecha = new JTextField();
		txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFecha.setEditable(false);
		txtFecha.setBounds(147, 39, 107, 20);
		txtFecha.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtFecha);

		fechaAct = Calendar.getInstance();
		String fecha=fechaAct.get(fechaAct.YEAR)+"-"+(fechaAct.get(fechaAct.MONTH)+1)+"-"+fechaAct.get(fechaAct.DATE);
		txtFecha.setText(fecha);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(76, 380, 111, 23);
		btnGuardar.setBackground(new Color(255, 255, 255));
		btnGuardar.addActionListener(this);
		getContentPane().add(btnGuardar);
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(225, 380, 107, 23);
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);
		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(365, 380, 89, 23);
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.addActionListener(this);
		getContentPane().add(btnSalir);

		comboPaciente = new JComboBox();
		comboPaciente.setBounds(147, 95, 307, 20);
		comboPaciente.setBackground(new Color(255, 255, 255));
		getContentPane().add(comboPaciente);
		llenarComboPaciente (comboPaciente);
		comboPaciente.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if(comboPaciente.getSelectedIndex()>0) {
					recuperarIdPaciente(comboPaciente, txtidPaciente);
				}else{
					txtidPaciente.setText("");
				}
			}
		});

		comboMedico = new JComboBox();
		comboMedico.setBounds(147, 149, 307, 20);
		comboMedico.setBackground(new Color(255, 255, 255));
		getContentPane().add(comboMedico);
		llenarComboMedico(comboMedico);
		comboMedico.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if(comboMedico.getSelectedIndex()>0) {
					recuperarIdMedico(comboMedico, txtidMedico );
				}else {
					txtidMedico.setText("");
				}
			}
		});

		areaTextoSigVit = new JTextArea();
		getContentPane().add(areaTextoSigVit);
		areaTextoSigVit.setLineWrap(true);
		areaTextoSigVit.setWrapStyleWord(true);
		desplazar = new JScrollPane(areaTextoSigVit);
		desplazar.setBounds(76, 252, 378, 88);
		getContentPane().add(desplazar);

		txtidPaciente = new JTextField();
		txtidPaciente.setEditable(false);
		txtidPaciente.setBounds(147, 122, 86, 20);
		getContentPane().add(txtidPaciente);
		txtidPaciente.setColumns(10);
		txtidPaciente.setVisible(false);

		txtidMedico = new JTextField();
		txtidMedico.setEditable(false);
		txtidMedico.setText("");
		txtidMedico.setBounds(147, 180, 86, 20);
		getContentPane().add(txtidMedico);
		txtidMedico.setColumns(10);
		txtidMedico.setVisible(false);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ImportarPacientesGUI.class.getResource("/Imagenes/3.jpg")));
		lblFondo.setBounds(0, 0, 525,500);
		getContentPane().add(lblFondo);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/3.jpg"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}

	public void llenarComboPaciente (JComboBox paciente) {

		listaPaciente = new ArrayList<>();
		listaPaciente.add("Seleccione Paciente");


		String Aux="";
		String cadena= "SELECT pac.idPaciente, per.* FROM paciente pac, persona per WHERE pac.idPersona = per.idPersona ";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs.next()) {
				Aux="";
				Aux += rs.getString("idPaciente")+"-";
				Aux += rs.getString("nombre1Per")+" ";
				Aux += rs.getString("apellido1Per")+" ";
				Aux += "["+rs.getString("cedulaPer")+"]";

				listaPaciente.add(Aux);

			}
		} catch (Exception u) {}

		for (String e: listaPaciente) {
			paciente.addItem(e);
			//System.out.println(e);	
		}
	}

	public void llenarComboMedico(JComboBox medico) {

		listaMedico = new ArrayList<>();
		listaMedico.add("Seleccione Médico");

		String Aux="";

		String cadena= "SELECT emp.idEmpleado, per.*, esp.descripcionEsp FROM empleado emp, persona per, especialidad esp  WHERE emp.idPersona = per.idPersona and emp.idEspecialidad = esp.idEspecialidad ";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs.next()) {
				Aux="";
				Aux += rs.getString("idEmpleado")+"-";
				Aux += rs.getString("nombre1Per")+" ";
				Aux += rs.getString("apellido1Per")+" ";
				Aux += "["+rs.getString("descripcionEsp")+"]";

				listaMedico.add(Aux);

			}
		} catch (Exception u) {}

		for (String e: listaMedico) {
			medico.addItem(e);
			//System.out.println(e);	
		}
	}

	public void recuperarIdPaciente(JComboBox paciente, JTextField idpaciente) {


		String auxiliar = (String) paciente.getSelectedItem();
		String idPac="", resto="";
		StringTokenizer nuevo = new StringTokenizer (auxiliar, "-");

		while(nuevo.hasMoreTokens()) {
			idPac=nuevo.nextToken();
			resto=nuevo.nextToken();
		}

		idpaciente.setText(idPac);

	}

	public void recuperarIdMedico(JComboBox medico, JTextField idmedico) {

		String auxiliar = (String) medico.getSelectedItem();
		String idMed="", resto="";
		StringTokenizer nuevo = new StringTokenizer (auxiliar, "-");

		while(nuevo.hasMoreTokens()) {
			idMed=nuevo.nextToken();
			resto=nuevo.nextToken();

		}
		idmedico.setText(idMed);
	}

	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource()==btnSalir) {
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}

		}

		if(evento.getSource()==btnCancelar) {
			areaTextoSigVit.setText("");
			comboMedico.setSelectedIndex(0);
			comboPaciente.setSelectedIndex(0);
		}

		if(evento.getSource()==btnGuardar) {

			boolean correcto=false;
			while (correcto==false) {	
				correcto = true;
				String error="";

				if(comboMedico.getSelectedIndex()==0) {
					error+="No ha seleccionado un médico.\n";
					correcto=false;
				}
				if(comboPaciente.getSelectedIndex()==0) {
					error+="No ha seleccionado un paciente.\n";
					correcto=false;
				}
				if(areaTextoSigVit.getText().trim().equals("")) {
					error+="No escrito nada en campo 'Signos Vitales'.\n";
					correcto=false;
				}

				if (!error.equals("")){
					JOptionPane.showMessageDialog(null, error , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}

			objAtencion = new Atencion();
			objAtencion.setIdPaciente(Integer.parseInt(txtidPaciente.getText()));
			objAtencion.setIdEmpleado(Integer.parseInt(txtidMedico.getText()));
			objAtencion.setSignosVitales(areaTextoSigVit.getText());
			objAtencion.setFechaAte(txtFecha.getText());
			objAtencion.setPrescripcionAte("");
			objAtencion.setDiagnosticoDescAte("");

			String cadena = "INSERT INTO atencion (signosVitales, fechaAte, idPaciente,idEmpleado,diagnosticoDescAte,prescripcionAte) VALUES ('"+objAtencion.getSignosVitales()+"','"+objAtencion.getFechaAte()+"' ,"+objAtencion.getIdPaciente()+" , "+objAtencion.getIdEmpleado()+", '"+objAtencion.getDiagnosticoDescAte()+"','"+objAtencion.getPrescripcionAte()+"' ) ";
			variablesGlobales.conexionBaseMySql.insertar(cadena);

			JOptionPane.showMessageDialog(null, "Signos Vitales agregados correctamente");

			btnCancelar.doClick();
		}
	}


	public static void main(String[] args) {

		RegistrarSignosVitalesGUI capturar = new RegistrarSignosVitalesGUI();
		capturar.setBounds(0,0,525,500);
		capturar.setVisible(true);
		//capturar.setLocationRelativeTo(null); //Aparezaca al centro de pantalla
		capturar.setResizable(false); //no deja modificar el tamaño de la interfaz
	}
}
