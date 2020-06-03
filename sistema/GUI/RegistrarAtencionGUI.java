package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.programacion2.epn.variablesGlobales;

import clasesBean.Atencion;
import clasesBean.Enfermedad_Atencion;
import clasesBean.pacienteBean;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Panel;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class RegistrarAtencionGUI extends JInternalFrame implements ActionListener{

	private JLabel lblDiag,lblDescripcion,lblMedTra, lblAte ,lblIDLugGeoPad,lblIdPaciente, lblIdEmpleado, lblIdAte, lblNombre, lblApellido,lblNombreMedico ,lblCedula, lblHistCli, lblSigVit,lblDia,lblPresc,lblDiagnsticoCie,lblGrupo,lblEspecfico, lblFecha;
	private JTextField txtFecha,txtMed,txtidPac,txtidMed,txtidAte,txtNom1Pac,txtApe1Pac,txtCedPac,txtHcl,txtidCIE,textNom2Pac, textApe2Pac;
	private JTable tblPrincipal, tblEnf;
	private JTextArea areaTextoTabla,areaTextoSignos,areaTextoDiagnostico, areaTextPres, areaTextoCIE;
	private JScrollPane desplazarTbl, desplazarSig, desplazarDiag, desplazarPres, desplazarTblAte;
	private DefaultTableModel modelo, modelo2;
	private JComboBox comboGrupo,comboEsp,comboMed;
	private JButton btnAceptar,btnGuardar,btnCancelar,btnSalir, btnAceptarCIE,btnEliminar;
	private Calendar fechaAct;
	private String [] arregloDatos =  new String [11];
	private String [] arregloCIE = new String[3];


	Atencion objAtencion = new Atencion();
	Enfermedad_Atencion objAteEnf = new Enfermedad_Atencion();
	pacienteBean objPaciente = new pacienteBean();

	String acumuladorTblPacientes="";

	private ArrayList <Atencion> listaAte = new ArrayList<Atencion>();
	private ArrayList <Enfermedad_Atencion> listaCIE = new ArrayList<Enfermedad_Atencion>();
	private ArrayList <String> listaPacientes = new ArrayList<String>();

	private ArrayList <String> listaMed, listaGrup, listaEsp;
	private JLabel lblFondo;

	public RegistrarAtencionGUI() {

		super ("REGISTRAR ATENCION");
		setBorder(null);
		getContentPane().setLayout(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);


		lblGrupo = new JLabel("Grupo:");
		lblGrupo.setBounds(44, 464, 46, 14);
		getContentPane().add(lblGrupo);
		lblEspecfico = new JLabel("Específico");
		lblEspecfico.setBounds(297, 464, 77, 14);
		getContentPane().add(lblEspecfico);
		lblMedTra = new JLabel("MÉDICO TRATANTE");
		lblMedTra.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblMedTra.setBounds(457, 22, 224, 14);
		getContentPane().add(lblMedTra);
		lblAte = new JLabel("Atenciones Pendientes para médico ");
		lblAte.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAte.setForeground(Color.WHITE);
		lblAte.setBounds(10, 102, 243, 14);
		getContentPane().add(lblAte);
		lblIdPaciente = new JLabel("ID Paciente: ");
		lblIdPaciente.setBounds(10, 273, 86, 14);
		getContentPane().add(lblIdPaciente);
		lblIdEmpleado = new JLabel("ID Médico:");
		lblIdEmpleado.setBounds(10, 317, 86, 14);
		getContentPane().add(lblIdEmpleado);
		lblIdAte = new JLabel("ID Atención: ");
		lblIdAte.setBounds(10, 361, 86, 14);
		getContentPane().add(lblIdAte);
		lblNombre = new JLabel("Primer Nombre:");
		lblNombre.setBounds(167, 273, 129, 14);
		getContentPane().add(lblNombre);
		lblApellido = new JLabel("Primer Apellido");
		lblApellido.setBounds(167, 317, 129, 14);
		getContentPane().add(lblApellido);
		lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(167, 361, 46, 14);
		getContentPane().add(lblCedula);
		lblHistCli = new JLabel("Historia Clínica");
		lblHistCli.setBounds(376, 361, 86, 14);
		getContentPane().add(lblHistCli);
		lblSigVit = new JLabel("Signos Vitales");
		lblSigVit.setBounds(545, 266, 110, 14);
		getContentPane().add(lblSigVit);
		lblDiag = new JLabel("DIAGNOSTICO");
		lblDiag.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiag.setBounds(824, 272, 121, 14);
		getContentPane().add(lblDiag);
		lblPresc = new JLabel("PRESCRIPCION");
		lblPresc.setForeground(Color.BLACK);
		lblPresc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPresc.setBounds(824, 438, 110, 14);
		getContentPane().add(lblPresc);
		lblDiagnsticoCie = new JLabel("DIAGNOSTICO CIE");
		lblDiagnsticoCie.setBounds(213, 439, 211, 14);
		getContentPane().add(lblDiagnsticoCie);
		lblNombreMedico = new JLabel();
		lblNombreMedico.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreMedico.setForeground(Color.WHITE);
		lblNombreMedico.setBounds(254, 102, 274, 14);
		getContentPane().add(lblNombreMedico);
		lblFecha = new JLabel();
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(33, 17, 89, 29);
		getContentPane().add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setVisible(false);
		txtFecha.setBounds(224, 17, 72, 29);
		getContentPane().add(txtFecha);

		fechaAct = Calendar.getInstance();
		String fecha=fechaAct.get(fechaAct.YEAR)+"-"+(fechaAct.get(fechaAct.MONTH)+1)+"-"+fechaAct.get(fechaAct.DATE);
		txtFecha.setText(fecha);
		lblFecha.setText(fecha);

		txtidPac = new JTextField();
		txtidPac.setEditable(false);
		txtidPac.setBounds(10, 286, 86, 20);
		txtidPac.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtidPac);
		txtidPac.setColumns(10);
		txtidMed = new JTextField();
		txtidMed.setEditable(false);
		txtidMed.setBounds(10, 330, 86, 20);
		txtidMed.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtidMed);
		txtidMed.setColumns(10);
		txtidAte = new JTextField();
		txtidAte.setEditable(false);
		txtidAte.setText("");
		txtidAte.setBounds(10, 376, 86, 20);
		txtidAte.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtidAte);
		txtidAte.setColumns(10);
		txtNom1Pac = new JTextField();
		txtNom1Pac.setEditable(false);
		txtNom1Pac.setBounds(164, 286, 132, 20);
		txtNom1Pac.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtNom1Pac);
		txtNom1Pac.setColumns(10);
		txtApe1Pac = new JTextField();
		txtApe1Pac.setEditable(false);
		txtApe1Pac.setBounds(164, 330, 132, 20);
		txtApe1Pac.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtApe1Pac);
		txtApe1Pac.setColumns(10);
		txtCedPac = new JTextField();
		txtCedPac.setEditable(false);
		txtCedPac.setBounds(167, 376, 129, 20);
		txtCedPac.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtCedPac);
		txtCedPac.setColumns(10);
		txtHcl = new JTextField();
		txtHcl.setEditable(false);
		txtHcl.setBounds(376, 376, 110, 20);
		txtHcl.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtHcl);
		txtHcl.setColumns(10);
		txtidCIE = new JTextField();
		txtidCIE.setEditable(false);
		txtidCIE.setBounds(44, 619, 46, 20);
		txtidCIE.setVisible(false);
		getContentPane().add(txtidCIE);
		txtidCIE.setColumns(10);
		textNom2Pac = new JTextField();
		textNom2Pac.setEditable(false);
		textNom2Pac.setBounds(376, 286, 110, 20);
		textNom2Pac.setBackground(new Color(255, 255, 255));
		getContentPane().add(textNom2Pac);
		textNom2Pac.setColumns(10);
		textApe2Pac = new JTextField();
		textApe2Pac.setEditable(false);
		textApe2Pac.setBackground(new Color(255, 255, 255));
		textApe2Pac.setBounds(376, 330, 110, 20);
		getContentPane().add(textApe2Pac);
		textApe2Pac.setColumns(10);

		JLabel lblSegundoNombre = new JLabel("Segundo Nombre");
		lblSegundoNombre.setBounds(376, 273, 110, 14);
		getContentPane().add(lblSegundoNombre);

		JLabel lblSegundoApellido = new JLabel("Segundo Apellido");
		lblSegundoApellido.setBounds(376, 317, 110, 14);
		getContentPane().add(lblSegundoApellido);


		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(716, 51, 89, 23);
		btnAceptar.setBackground(new Color(255, 255, 255));
		btnAceptar.addActionListener(this);
		btnAceptar.setEnabled(false);
		getContentPane().add(btnAceptar);
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBackground(new Color(255, 255, 255));
		btnGuardar.setBounds(643, 618, 110, 21);
		btnGuardar.addActionListener(this);
		btnGuardar.setEnabled(false);
		getContentPane().add(btnGuardar);
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setBounds(790, 616, 121, 23);
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		getContentPane().add(btnCancelar);
		btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBounds(955, 618, 89, 23);
		btnSalir.addActionListener(this);
		getContentPane().add(btnSalir);
		btnAceptarCIE = new JButton("Aceptar");
		btnAceptarCIE.setBounds(198, 618, 89, 23);
		btnAceptarCIE.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnAceptarCIE);
		btnAceptarCIE.addActionListener(this);
		btnAceptarCIE.setEnabled(false);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(297, 618, 89, 23);
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.addActionListener(this);
		btnEliminar.setEnabled(false);
		getContentPane().add(btnEliminar);


		areaTextoSignos = new JTextArea();
		areaTextoSignos.setEditable(false);
		areaTextoSignos.setLineWrap(true);
		areaTextoSignos.setWrapStyleWord(true);
		desplazarSig = new JScrollPane(areaTextoSignos);
		desplazarSig.setBounds(544, 282, 185, 100);
		getContentPane().add(desplazarSig);

		areaTextoDiagnostico = new JTextArea();
		areaTextoDiagnostico.setToolTipText("");
		areaTextoDiagnostico.setEditable(false);
		areaTextoDiagnostico.setLineWrap(true);
		areaTextoDiagnostico.setWrapStyleWord(true);
		desplazarDiag = new JScrollPane(areaTextoDiagnostico);
		desplazarDiag.setBounds(790, 286, 300, 135);
		getContentPane().add(desplazarDiag);

		areaTextPres = new JTextArea();
		areaTextPres.setEditable(false);
		areaTextPres.setLineWrap(true);
		areaTextPres.setWrapStyleWord(true);
		desplazarPres = new JScrollPane(areaTextPres);
		desplazarPres.setBounds(790, 460, 300, 135);
		getContentPane().add(desplazarPres);

		comboMed = new JComboBox();
		comboMed.setBounds(390, 52, 301, 20);
		comboMed.setBackground(new Color(255, 255, 255));
		getContentPane().add(comboMed);
		llenarMedicos(comboMed);
		comboMed.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(comboMed.getSelectedIndex()>0) {
					btnAceptar.setEnabled(true);
				} else {
					btnAceptar.setEnabled(false);
				}
			}
		});

		comboGrupo = new JComboBox();
		comboGrupo.setEnabled(false);
		comboGrupo.setBackground(new Color(255, 255, 255));
		comboGrupo.setBounds(44, 477, 243, 20);
		getContentPane().add(comboGrupo);
		llenarEnfermedadGrupo(comboGrupo);
		comboGrupo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if(comboGrupo.getSelectedIndex()>0) {
					llenarEnfermedadEspecifico(comboGrupo, comboEsp);
				}else {
					comboEsp.removeAllItems();
				}
			}
		});

		comboEsp = new JComboBox();
		comboEsp.setEnabled(false);
		comboEsp.setBounds(297, 477, 243, 20);
		comboEsp.setBackground(new Color(255, 255, 255));
		getContentPane().add(comboEsp);
		comboEsp.addItemListener( new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				String cadena="";

				cadena = "SELECT * FROM cie WHERE descripcionEnf = '"+comboEsp.getSelectedItem()+"' ";	
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

				try {
					while (rs.next()) {
						txtidCIE.setText(variablesGlobales.conexionBaseMySql.getString(rs, "idCIE"));				
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se pudo obtner");
				}
			}
		});


		areaTextoCIE = new JTextArea();
		desplazarTblAte = new JScrollPane(areaTextoCIE);
		desplazarTblAte.setBounds(40,520,500,90);
		getContentPane().add(desplazarTblAte);
		modelo2 = new DefaultTableModel() {
			public boolean isCellEditable (int fila, int colomna) {
				return false;
			}
		};
		tblEnf = new JTable(modelo2);
		desplazarTblAte.setViewportView(tblEnf);

		modelo2.addColumn("IDCIE");
		modelo2.addColumn("GRUPO");
		modelo2.addColumn("ESPECIFICO");
		tblEnf.getTableHeader().setReorderingAllowed(false) ;
		tblEnf.getColumnModel().getColumn(0).setMaxWidth(0);
		tblEnf.getColumnModel().getColumn(0).setMinWidth(0);
		tblEnf.getColumnModel().getColumn(0).setPreferredWidth(0);
		tblEnf.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tblEnf.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

		TableColumn columna1=tblEnf.getColumnModel().getColumn(1);
		columna1.setPreferredWidth(247);
		columna1.setMaxWidth(380);
		columna1.setMinWidth(60);
		TableColumn columna2=tblEnf.getColumnModel().getColumn(2);
		columna2.setPreferredWidth(250);
		columna2.setMaxWidth(380);
		columna2.setMinWidth(60);


		areaTextoTabla = new JTextArea();
		desplazarTbl=new JScrollPane(areaTextoTabla);
		desplazarTbl.setBounds(10, 130, 1145, 125);
		getContentPane().add(desplazarTbl);	
		modelo = new DefaultTableModel(){
			public boolean isCellEditable (int fila, int columna) {
				return false;
			}
		};

		tblPrincipal = new JTable(modelo);
		//tblPrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		desplazarTbl.setViewportView(tblPrincipal);

		modelo.addColumn("ID ATENCION");
		modelo.addColumn("ID MEDICO");
		modelo.addColumn("ID PACIENTE");
		modelo.addColumn("NOMBRES DEL PACIENTE");
		modelo.addColumn("NOMBRE1");
		modelo.addColumn("NOMBRE2");
		modelo.addColumn("APELLIDO1");
		modelo.addColumn("APELLIDO2");
		modelo.addColumn("CEDULA");
		modelo.addColumn("HISTORIA");
		modelo.addColumn("SIGNOS VITALES");
		tblPrincipal.getTableHeader().setReorderingAllowed(false) ;
		for(int i=0; i<11;i++) {
			TableColumn columna=tblPrincipal.getColumnModel().getColumn(i);
			columna.setPreferredWidth(125);
			columna.setMaxWidth(380);
			columna.setMinWidth(60);
			if(i==0) {
				columna.setPreferredWidth(80);
			}
			if(i==3) {
				columna.setPreferredWidth(300);
			}
			if(i==1) {
				columna.setPreferredWidth(80);
			}
			if(i==2) {
				columna.setPreferredWidth(80);
			}
			if(i==9) {
				columna.setPreferredWidth(300);
			}
		}

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tblPrincipal.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tblPrincipal.getColumnModel().getColumn(1).setCellRenderer(tcr);
		tblPrincipal.getColumnModel().getColumn(2).setCellRenderer(tcr);
		for(int i=4; i<8; i++) {
			tblPrincipal.getColumnModel().getColumn(i).setMaxWidth(0);
			tblPrincipal.getColumnModel().getColumn(i).setMinWidth(0);
			tblPrincipal.getColumnModel().getColumn(i).setPreferredWidth(0);
			tblPrincipal.getTableHeader().getColumnModel().getColumn(i).setMaxWidth(0);
			tblPrincipal.getTableHeader().getColumnModel().getColumn(i).setMinWidth(0);
		}
		tblPrincipal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int rec = tblPrincipal.getSelectedRow();

				txtidAte.setText(tblPrincipal.getValueAt(rec, 0).toString());
				txtidMed.setText(tblPrincipal.getValueAt(rec, 1).toString());
				txtidPac.setText(tblPrincipal.getValueAt(rec, 2).toString());
				txtNom1Pac.setText(tblPrincipal.getValueAt(rec, 4).toString());	
				textNom2Pac.setText(tblPrincipal.getValueAt(rec, 5).toString());
				txtApe1Pac.setText(tblPrincipal.getValueAt(rec, 6).toString());
				textApe2Pac.setText(tblPrincipal.getValueAt(rec, 7).toString());
				txtCedPac.setText(tblPrincipal.getValueAt(rec, 8).toString());
				txtHcl.setText(tblPrincipal.getValueAt(rec, 9).toString());
				areaTextoSignos.setText(tblPrincipal.getValueAt(rec, 10).toString());

				objAtencion.setIdAtencion(Integer.parseInt(txtidAte.getText().trim()));
				objAtencion.setIdEmpleado(Integer.parseInt(txtidMed.getText().trim()));
				objAtencion.setIdPaciente(Integer.parseInt(txtidPac.getText().trim()));
				objPaciente.setNombre1Per(txtNom1Pac.getText().trim());
				objPaciente.setNombre2Per(textNom2Pac.getText().trim());
				objPaciente.setApellido1Per(txtApe1Pac.getText().trim());
				objPaciente.setApellido2Per(textApe2Pac.getText().trim());
				objPaciente.setCedulaPer(txtCedPac.getText().trim());
				objPaciente.setHclPac(txtHcl.getText().trim());
				objAtencion.setSignosVitales(areaTextoSignos.getText());


			}
		});

		lblFondo = new JLabel("");
		//lblFondo.setIcon(new ImageIcon(RegistrarAtencionGUI.class.getResource("/Imagenes/9092966_xl.jpg")));
		lblFondo.setBounds(0,0,1196,710);
		getContentPane().add(lblFondo);

		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/9092966_xl.jpg"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}

	public void llenarMedicos(JComboBox cboMed) {

		listaMed = new ArrayList<>();
		listaMed.add("SELECCIONE UN MÉDICO");

		String Aux="";

		String cadena= "SELECT e.idEmpleado,p.nombre1Per, p.apellido1Per, esp.descripcionEsp  FROM empleado e, persona p, especialidad esp WHERE e.idPersona=p.idPersona  and e.idEspecialidad=esp.idEspecialidad ";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs.next()) {
				Aux="";

				Aux += rs.getString("idEmpleado")+"-";
				Aux += rs.getString("nombre1Per")+"  ";
				Aux += rs.getString("apellido1Per")+"  ";
				Aux += "["+rs.getString("descripcionEsp")+"]";

				listaMed.add(Aux);

			}
		} catch (Exception u) {}

		for (String e: listaMed) {
			cboMed.addItem(e);	
		}
	}

	public void llenarEnfermedadGrupo(JComboBox grupo) {

		listaGrup= new ArrayList<String>();
		listaGrup.add("GRUPO");

		String cadena = "SELECT * FROM  cie WHERE idCIEpadre is null ORDER BY descripcionEnf";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs.next()) {

				listaGrup.add(rs.getString("descripcionEnf"));
			}
		} catch (Exception u) {}


		for (String e: listaGrup) {
			grupo.addItem(e);
		}

	}

	public void llenarEnfermedadEspecifico(JComboBox grupo, JComboBox especifico) {

		especifico.removeAllItems();
		listaEsp = new ArrayList<String>();
		listaEsp.add("ESPECÍFICO");

		int aux=0;
		String cadena="";

		cadena = "SELECT * FROM cie WHERE descripcionEnf = '"+grupo.getSelectedItem()+"' ";	
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

		try {
			while (rs.next()) {
				aux = Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idCIE"));				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo obtner");
		}


		cadena = "SELECT * FROM cie WHERE idCIEpadre = "+aux+" ORDER BY descripcionEnf";
		ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs1.next()) {
				listaEsp.add(rs1.getString("descripcionEnf"));
			}
		} catch (Exception u) {}


		for (String e: listaEsp) {
			especifico.addItem(e);
		}
	}

	public void llenarTablaCIE( JComboBox grupo, JComboBox especifico, DefaultTableModel modeloTabla, String [] arreglo, JTextField idCIE ) {

		String grup = (String) grupo.getSelectedItem();
		String esp = (String) especifico.getSelectedItem();
		String id = idCIE.getText();

		arreglo [0]=id;
		arreglo [1]=grup;
		arreglo [2]=esp;

		modeloTabla.addRow(arreglo);

	}

	public void eliminarFilaTablaCIE(JTable cie, DefaultTableModel mode) {

		int fila = cie.getSelectedRow();
		mode.removeRow(fila);	

	}

	public void mostrarTablaMedico (JComboBox cmbMed, DefaultTableModel modeloTabla, String [] arreglo, JLabel medico, JTextField fecha) {

		modeloTabla.setRowCount(0);
		String auxiliar = (String) cmbMed.getSelectedItem();

		String idEmpleado="", resto="";
		
		StringTokenizer nuevo = new StringTokenizer (auxiliar, "-");
		while(nuevo.hasMoreTokens()) {
			idEmpleado=nuevo.nextToken();
			resto=nuevo.nextToken();

		}
		medico.setText(resto);

		String fechaActual = fecha.getText();

		String cadena3="";
		cadena3 = "SELECT a.*,p.*, pe.*, concat (pe.apellido1Per,'  ',pe.apellido2Per,'  ',pe.nombre1Per,'  ',pe.nombre2Per) as NombresPacientes FROM atencion a, paciente p, persona pe  WHERE a.idPaciente = p.idPaciente and p.idPersona = pe.idPersona and a.idEmpleado = '"+idEmpleado+"' and a.diagnosticoDescAte = '' and a.fechaAte = '"+fechaActual+"' ";	
		ResultSet rs4 = variablesGlobales.conexionBaseMySql.consulta(cadena3);



		try {
			listaPacientes.clear();
			while(rs4.next()) {

				acumuladorTblPacientes="";

				acumuladorTblPacientes += Integer.parseInt(rs4.getString("idAtencion"))+"- ";
				acumuladorTblPacientes += Integer.parseInt(rs4.getString("idEmpleado"))+"- ";
				acumuladorTblPacientes += Integer.parseInt(rs4.getString("idPaciente"))+"- ";
				acumuladorTblPacientes += rs4.getString("NombresPacientes")+"- ";
				acumuladorTblPacientes += rs4.getString("nombre1Per")+"- ";
				acumuladorTblPacientes += rs4.getString("nombre2Per")+"- ";
				acumuladorTblPacientes += rs4.getString("apellido1Per")+"- ";
				acumuladorTblPacientes += rs4.getString("apellido2Per")+"- ";
				acumuladorTblPacientes += rs4.getString("cedulaPer")+"- ";
				acumuladorTblPacientes += rs4.getString("hclPac")+"- ";
				acumuladorTblPacientes += rs4.getString("signosVitales");

				listaPacientes.add(acumuladorTblPacientes); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e+"\nNo se pudo llenar lista");
		}

		for (String e: listaPacientes) {

			StringTokenizer tokens = new StringTokenizer (e, "-");
			while (tokens.hasMoreTokens()) {

				arreglo[0]= tokens.nextToken();
				arreglo[1]=tokens.nextToken();
				arreglo[2]=tokens.nextToken();
				arreglo[3]=tokens.nextToken();
				arreglo[4]=tokens.nextToken();
				arreglo[5]=tokens.nextToken();
				arreglo[6]=tokens.nextToken();
				arreglo[7]=tokens.nextToken();
				arreglo[8]=tokens.nextToken();
				arreglo[9]=tokens.nextToken();
				arreglo[10]=tokens.nextToken();
			}
			modeloTabla.addRow(arreglo);
			System.out.println(e);
		}
		
		if(modeloTabla.getRowCount()==0) {
			JOptionPane.showMessageDialog(null, "El médico "+lblNombreMedico.getText()+" no tienes citas para "+fecha.getText());
			return;
		}
	}

	public void actionPerformed(ActionEvent evento) {

		if(evento.getSource()==btnSalir) {
			try {
				this.setClosed(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}

		if(evento.getSource()==btnCancelar) {

			modelo2.setRowCount(0);
			btnAceptar.setEnabled(true);
			txtidAte.setText("");
			txtidPac.setText("");
			txtidMed.setText("");
			txtNom1Pac.setText("");
			textNom2Pac.setText("");
			txtApe1Pac.setText("");
			textApe2Pac.setText("");
			txtHcl.setText("");
			txtCedPac.setText("");
			areaTextoSignos.setText("");
			areaTextoDiagnostico.setText("");
			areaTextPres.setText("");
			comboEsp.setEnabled(false);
			comboGrupo.setEnabled(false);
			comboGrupo.setSelectedIndex(0);
			btnAceptarCIE.setEnabled(false);
			areaTextoDiagnostico.setEditable(false);
			areaTextPres.setEditable(false);
			lblNombreMedico.setText("");
			txtidCIE.setText("");
			btnGuardar.setEnabled(false);
			btnEliminar.setEnabled(false);

			if(tblPrincipal.getSelectedRow()== -1) {
				modelo.setRowCount(0);
				comboMed.setEnabled(true);
				comboMed.setSelectedIndex(0);
			}else {
				modelo.removeRow(tblPrincipal.getSelectedRow());
			}
		}

		if(evento.getSource()==btnAceptar) {

			if(comboMed.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(null, "No ha seleccionado Médico" , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
			}else {
				mostrarTablaMedico (comboMed, modelo, arregloDatos, lblNombreMedico, txtFecha);
			}
			comboMed.setEnabled(false);
			btnAceptar.setEnabled(false);
			comboEsp.setEnabled(true);
			comboGrupo.setEnabled(true);
			btnAceptarCIE.setEnabled(true);
			areaTextoDiagnostico.setEditable(true);
			areaTextPres.setEditable(true);
			btnGuardar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnCancelar.setEnabled(true);
		}

		if(evento.getSource()==btnAceptarCIE) {
			boolean correcto=false;
			while (correcto==false) {	
				correcto = true;
				String error="";
				if(comboGrupo.getSelectedIndex()==0) {
					error+="No ha seleccionado Grupo de Enfermedades.\n";
					correcto=false;
				}
				if(comboEsp.getSelectedIndex()==0) {
					error+="No ha seleccionado Especificacion de la Enfermedad.\n";
					correcto=false;
				}
				if (!error.equals("")){
					JOptionPane.showMessageDialog(null, error , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}
			llenarTablaCIE( comboGrupo, comboEsp, modelo2, arregloCIE, txtidCIE );			
			comboGrupo.setSelectedIndex(0);
		}

		if(evento.getSource()==btnEliminar) {

			if(tblEnf.getSelectedRowCount()>0) {
				eliminarFilaTablaCIE( tblEnf, modelo2);
			}
		}

		if(evento.getSource()==btnGuardar) {


			try {
				objAtencion = new Atencion();
				objAtencion.setDiagnosticoDescAte(areaTextoDiagnostico.getText());
				objAtencion.setPrescripcionAte(areaTextPres.getText());
				objAtencion.setIdAtencion(Integer.parseInt(txtidAte.getText()));

				for (int i=0; i<tblEnf.getRowCount(); i++) {
					objAteEnf = new Enfermedad_Atencion();
					objAteEnf.setIdCie(Integer.parseInt(tblEnf.getValueAt(i, 0).toString()));	
					listaCIE.add(objAteEnf);
				}
				for(Enfermedad_Atencion e: listaCIE) {

					String cadena="";
					cadena = "UPDATE  atencion SET diagnosticoDescAte = '"+objAtencion.getDiagnosticoDescAte()+"', prescripcionAte = '"+objAtencion.getPrescripcionAte()+"' WHERE idAtencion = "+objAtencion.getIdAtencion()+" ";
					variablesGlobales.conexionBaseMySql.insertar(cadena);


					cadena = "SELECT * FROM atencion WHERE idPaciente = "+objAtencion.getIdPaciente()+" and idEmpleado = "+objAtencion.getIdEmpleado()+" ";
					ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);


					cadena = "INSERT INTO enfermedad_atencion (idAtencion, idCie) VALUES ("+objAtencion.getIdAtencion()+",'"+e.getIdCie()+"')";
					variablesGlobales.conexionBaseMySql.insertar(cadena);
					System.out.println(cadena);


				} 
			}catch (Exception error) {
				JOptionPane.showMessageDialog(null, "No se pudo ingresar datos" , "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Se actualizo paciente.");
			btnCancelar.doClick();
		}

	}


	public static void main(String[] args) {

		RegistrarAtencionGUI capturar = new RegistrarAtencionGUI();
		capturar.setBounds(0,0,1173,710);
		capturar.setVisible(true);
		//capturar.setLocationRelativeTo(null); //Aparezaca al centro de pantalla
		capturar.setResizable(false); //no deja modificar el tamaño de la interfaz
	}
}
