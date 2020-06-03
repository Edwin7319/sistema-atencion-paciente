package GUI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.programacion2.epn.variablesGlobales;
import com.toedter.calendar.JCalendar;
import ServiciosGenerales.*;
import clasesBean.pacienteBean;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.SystemColor;




public class RegistrarPacienteGUI extends  JInternalFrame implements ActionListener {


	private JLabel lblNom1, lblNom2, lblApe1, lblApe2, lblFecha, lblTelef, lblMail, lblCi, lblSexo, lblHist, lblDirec, lblEstCiv, lblTitulo,lblTitulo2, lblLugRes, lblLugNac, lblidLugNac, lblidLugRes, lblFondo; 
	private JButton btnGrabar, btnCancelar, btnSalir;
	private JTextField txtNom1, txtNom2, txtApe1, txtApe2, txtFecha, txtTelf, txtMail, txtCi, txtHist, txtDirec, txtidLugNac, txtidLugRes, txtidPac;
	private JTextArea areaTextoTabla;
	private JScrollPane desplazar;
	private JRadioButton masculino, femenino;
	private ButtonGroup btnGr;
	private JComboBox comboEstaCiv, comboProvNac, comboCantNac, comboParrNac, comboProvRes, comboCantRes, comboParrRes; 

	private String estadoCivil []= {"Seleccione Estado Civil","Casado/a","Soltero/a","Viudo/a","Divorciado/a"};

	private String [] arregloDatos =  new String [18];
	private DefaultTableModel modelo;
	private JTable tblPacientes;
	private JCalendar calendario;
	private Calendar fechaAct;
	private LocalDate fechaNacimiento, fechaActual;
	private Period periodo;
	private ArrayList <pacienteBean> listaPac = new ArrayList<pacienteBean>();
	private ArrayList <String> listaProv, listaCant, listaParr; 


	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter formatoFechaNac = DateTimeFormatter.ofPattern(("yyyy-MM-dd"));

	pacienteBean objPacientes = new pacienteBean();

	String sexo="";
	String estCivil = "";

	private final JDesktopPane desktopPane = new JDesktopPane();

	public RegistrarPacienteGUI() {

		super ("REGISTRAR PACIENTES");
		setBorder(null);
		getContentPane().setLayout(null); //Indica al programa que se usa elementos con coordenadas
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

		//Titulos
		lblTitulo = new JLabel("REGISTRO DE PACIENTES");
		lblTitulo.setBounds(540,10,1200,30);
		getContentPane().add(lblTitulo);
		Font font = new Font("Times New Roman", Font.BOLD,22);
		lblTitulo.setFont(font);
		lblTitulo2 = new JLabel("TABLA DE PACIENTES REGISTRADOS");
		lblTitulo2.setBounds(500,330,1200,30);
		getContentPane().add(lblTitulo2);
		Font font2 = new Font("Times New Roman", Font.BOLD,20);
		lblTitulo2.setFont(font2);

		//Etiquetas
		lblNom1 = new JLabel("Primer Nombre: ");
		lblNom1.setBounds(15,50,223,22);
		getContentPane().add(lblNom1);
		lblNom2 = new JLabel ("Segundo Nombre (Opcional): ");
		lblNom2.setBounds(15,100,300,30);
		getContentPane().add(lblNom2);
		lblApe1 = new JLabel("Primer Apellido: ");
		lblApe1.setBounds(15,150,300,30);
		getContentPane().add(lblApe1);
		lblApe2 = new JLabel("Segundo Apellido (Opcional): ");
		lblApe2.setBounds(15,200,300,30);
		getContentPane().add(lblApe2);
		lblCi = new JLabel("Cédula: ");
		lblCi.setBounds(15,250,300,30);
		getContentPane().add(lblCi);
		lblTelef = new JLabel("Telefono: ");
		lblTelef.setBounds(300,50,223,30);
		getContentPane().add(lblTelef);
		lblMail = new JLabel("Correo Electrónico (Opcional): ");
		lblMail.setBounds(300,100,300,30);
		getContentPane().add(lblMail);
		lblDirec = new JLabel("Direccion: ");
		lblDirec.setBounds(300, 150, 300, 30);
		getContentPane().add(lblDirec);
		lblEstCiv = new JLabel("Estado Civil: ");
		lblEstCiv.setBounds(300,200,230,30);
		getContentPane().add(lblEstCiv);
		lblSexo = new JLabel ("Sexo: ");
		lblSexo.setBounds(300,250,300,30);
		getContentPane().add(lblSexo);
		lblHist = new JLabel("Historia Clinica: ");
		lblHist.setBounds(585,50,300,30);
		getContentPane().add(lblHist);
		lblFecha = new JLabel("Fecha Nacimiento: ");
		lblFecha.setBounds(585,100,150,30);
		getContentPane().add(lblFecha);
		lblLugNac = new JLabel("Lugar de Nacimiento");
		lblLugNac.setBounds(870, 50, 200, 30);
		getContentPane().add(lblLugNac);
		lblidLugNac = new JLabel("id Lugar Nacimiento");
		lblidLugNac.setBounds(870,100,200,30);
		getContentPane().add(lblidLugNac);
		lblLugRes = new JLabel("Lugar de Residencia");
		lblLugRes.setBounds(870, 150, 200, 30);
		getContentPane().add(lblLugRes);
		lblidLugRes = new JLabel("id Lugar Recidencia");
		lblidLugRes.setBounds(870, 200, 200, 30);
		getContentPane().add(lblidLugRes);

		//Botones
		btnGrabar = new JButton ("GRABAR");
		btnGrabar.setBackground(new Color(255, 255, 255));
		btnGrabar.setBounds(511,610,90,30);
		getContentPane().add(btnGrabar);
		btnGrabar.addActionListener(this);
		btnCancelar = new JButton ("CANCELAR");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setBounds(635,610,100,30);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(this);
		btnSalir = new JButton ("SALIR");
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBounds(760,610,100,30);
		getContentPane().add(btnSalir);
		btnSalir.addActionListener(this);

		//Cajas de Texto
		txtNom1 = new JTextField();
		txtNom1.setBounds(15,74,230,22);
		getContentPane().add(txtNom1);
		txtNom2 = new JTextField();
		txtNom2.setBounds(15,124,230,22);
		getContentPane().add(txtNom2);
		txtApe1 = new JTextField();
		txtApe1.setBounds(15,174,230,22);
		getContentPane().add(txtApe1);
		txtApe2 = new JTextField();
		txtApe2.setBounds(15,224,230,22);
		getContentPane().add(txtApe2);
		txtCi = new JTextField();
		txtCi.setBounds(15, 274, 230, 22);
		getContentPane().add(txtCi);
		txtTelf = new JTextField();
		txtTelf.setBounds(300,74,230,22);
		getContentPane().add(txtTelf);
		txtMail = new JTextField();
		txtMail.setBounds(300,124,230,22);
		getContentPane().add(txtMail);
		txtDirec = new JTextField();
		txtDirec.setBounds(300,174,230,22);
		getContentPane().add(txtDirec);
		txtHist = new JTextField();
		txtHist.setBounds(585,74,175,22);
		getContentPane().add(txtHist);
		txtidLugNac = new JTextField();
		txtidLugNac.setBounds(870,124,120,22);
		txtidLugNac.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtidLugNac);
		txtidLugNac.setEditable(false);
		txtidLugRes = new JTextField();
		txtidLugRes.setBounds(870,224,120,22);
		txtidLugRes.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtidLugRes);
		txtidLugRes.setEditable(false);
		txtFecha = new JTextField();
		txtFecha.setBounds(585,270,120,22);
		getContentPane().add(txtFecha);
		txtFecha.setEditable(false);
		txtFecha.setBackground(new Color(255, 255, 255));

		txtidPac = new JTextField();
		txtidPac.setEditable(false);
		txtidPac.setBounds(22, 615, 86, 20);
		txtidPac.setVisible(false);
		getContentPane().add(txtidPac);
		txtidPac.setColumns(10);

		//calendario
		calendario = new JCalendar();
		calendario.setBounds(585,128,223,140);
		getContentPane().add(calendario);
		calendario.addPropertyChangeListener("calendar", new PropertyChangeListener () {
			public void propertyChange(PropertyChangeEvent cal) {
				int dia=0;
				int mes=0;
				int año=0;
				String fecha="";
				dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
				mes = calendario.getCalendar().get(Calendar.MONTH) + 1;
				año = calendario.getCalendar().get(Calendar.YEAR);
				fecha = año+ "-" + mes+ "-" + dia;
				txtFecha.setText(fecha);
			}
		});

		//Combo Estado Civil
		comboEstaCiv = new JComboBox(estadoCivil);
		comboEstaCiv.setBackground(new Color(255, 255, 255));
		comboEstaCiv.setBounds(300,224,166,20);
		getContentPane().add(comboEstaCiv);
		comboEstaCiv.addActionListener(this);

		//Combo Nacimiento
		comboProvNac = new JComboBox();
		comboProvNac.setBackground(new Color(255, 255, 255));
		comboProvNac.setBounds(870, 74, 120, 22);
		getContentPane().add(comboProvNac);

		llenarProv(comboProvNac);

		comboProvNac.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if(comboProvNac.getSelectedIndex()>0) {
					llenarCanton (comboCantNac, comboProvNac);
				}else {
					comboCantNac.removeAllItems();
				}
			}
		});


		comboCantNac = new JComboBox();
		comboCantNac.setBackground(new Color(255, 255, 255));
		comboCantNac.setBounds(1005, 74, 120, 22);
		getContentPane().add(comboCantNac);
		comboCantNac.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {

				if(comboCantNac.getSelectedIndex()>0) {
					llenarParroquia(comboCantNac ,comboParrNac);
				}else {
					comboParrNac.removeAllItems();
				}
			}
		});;

		comboParrNac = new JComboBox();
		comboParrNac.setBackground(new Color(255, 255, 255));
		comboParrNac.setBounds(1140, 74, 120, 22);
		getContentPane().add(comboParrNac);
		comboParrNac.addItemListener( new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {

				String cadena="";

				cadena = "SELECT * FROM lugargeo WHERE descripcionLugGeo='"+comboParrNac.getSelectedItem()+"' ";	
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

				try {
					while (rs.next()) {
						txtidLugNac.setText(variablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo"));				
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se pudo obtner");
				}

			}
		});

		//Combo Residencia
		comboProvRes = new JComboBox();
		comboProvRes.setBackground(new Color(255, 255, 255));
		comboProvRes.setBounds(870, 174, 120, 22);
		getContentPane().add(comboProvRes);
		llenarProv(comboProvRes);

		comboProvRes.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(comboProvRes.getSelectedIndex()>0) {
					llenarCanton (comboCantRes, comboProvRes);
				}else {
					comboCantRes.removeAllItems();
				}
			}
		});		
		comboCantRes = new JComboBox();
		comboCantRes.setBackground(new Color(255, 255, 255));
		comboCantRes.setBounds(1005, 174, 120, 22);
		getContentPane().add(comboCantRes);
		comboCantRes.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {


				if(comboCantRes.getSelectedIndex()>0) {
					llenarParroquia(comboCantRes ,comboParrRes);
				}else {
					comboParrRes.removeAllItems();
				}
			}
		});

		comboParrRes = new JComboBox();
		comboParrRes.setBackground(new Color(255, 255, 255));
		comboParrRes.setBounds(1140, 174, 120, 22);
		getContentPane().add(comboParrRes);
		comboParrRes.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				String cadena="";

				cadena = "SELECT * FROM lugargeo WHERE descripcionLugGeo='"+comboParrRes.getSelectedItem()+"' ";	
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

				try {
					while (rs.next()) {
						txtidLugRes.setText(variablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo"));				
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se pudo obtner");
				}

			}
		});


		//Btn grup sexo
		btnGr = new ButtonGroup();
		masculino = new JRadioButton("Masculino");
		masculino.setBounds(300,278,90,15);
		masculino.addActionListener(this);
		getContentPane().add(masculino);
		btnGr.add(masculino);
		femenino = new JRadioButton("Femenino");
		femenino.setBounds(400,278,100,15);
		femenino.addActionListener(this);
		getContentPane().add(femenino);
		btnGr.add(femenino);

		//Tabla
		areaTextoTabla = new JTextArea();
		desplazar=new JScrollPane(areaTextoTabla);
		desplazar.setBounds(15, 360, 1280, 230);
		getContentPane().add(desplazar);	

		modelo = new DefaultTableModel(){
			public boolean isCellEditable (int fila, int columna) {
				return false;
			}
		};
		tblPacientes = new JTable(modelo);
		tblPacientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		desplazar.setViewportView(tblPacientes);

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
		modelo.addColumn("id NACIMINETO");
		modelo.addColumn("id RECIDENCIA");
		modelo.addColumn("LUGAR NACIMINETO");
		modelo.addColumn("LUGAR RECIDENCIA");
		modelo.addColumn("EDAD");
		modelo.addColumn("ID");

		tblPacientes.getTableHeader().setReorderingAllowed(false) ;

		for(int i=0; i<18;i++) {
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
			if(i==14) {
				columna.setPreferredWidth(200);
			}
			if(i==15) {
				columna.setPreferredWidth(200);
			}
			if(i==16) {
				columna.setPreferredWidth(90);
			}
			if(i==17) {
				columna.setPreferredWidth(60);
			}
		}

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tblPacientes.getColumnModel().getColumn(5).setCellRenderer(tcr);
		tblPacientes.getColumnModel().getColumn(6).setCellRenderer(tcr);
		tblPacientes.getColumnModel().getColumn(8).setCellRenderer(tcr);
		tblPacientes.getColumnModel().getColumn(16).setCellRenderer(tcr);
		tblPacientes.getColumnModel().getColumn(17).setMaxWidth(0);
		tblPacientes.getColumnModel().getColumn(17).setMinWidth(0);
		tblPacientes.getColumnModel().getColumn(17).setPreferredWidth(0);
		tblPacientes.getTableHeader().getColumnModel().getColumn(17).setMaxWidth(0);
		tblPacientes.getTableHeader().getColumnModel().getColumn(17).setMinWidth(0);
		for(int i=10; i<14;i++) {
			tblPacientes.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}

		tblPacientes.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				int rec = tblPacientes.getSelectedRow();

				if(tblPacientes.getValueAt(rec, 8).toString().equals("M")) {
					masculino.setSelected(true);
				}else if(tblPacientes.getValueAt(rec, 8).toString().equals("F")) {
					femenino.setSelected(true);
				}

				if(tblPacientes.getValueAt(rec, 10).toString().equals("C")) {
					comboEstaCiv.setSelectedIndex(1);
				} else if(tblPacientes.getValueAt(rec, 10).toString().equals("S")) {
					comboEstaCiv.setSelectedIndex(2);
				} else if(tblPacientes.getValueAt(rec, 10).toString().equals("V")) {
					comboEstaCiv.setSelectedIndex(3);
				} else if(tblPacientes.getValueAt(rec, 10).toString().equals("D")) {
					comboEstaCiv.setSelectedIndex(4);
				}

				txtNom1.setText(tblPacientes.getValueAt(rec, 0).toString());
				txtNom2.setText(tblPacientes.getValueAt(rec, 1).toString());
				txtApe1.setText(tblPacientes.getValueAt(rec, 2).toString());
				txtApe2.setText(tblPacientes.getValueAt(rec, 3).toString());
				txtMail.setText(tblPacientes.getValueAt(rec, 4).toString());
				txtCi.setText(tblPacientes.getValueAt(rec, 5).toString());
				txtTelf.setText(tblPacientes.getValueAt(rec, 6).toString());
				txtHist.setText(tblPacientes.getValueAt(rec, 7).toString());
				txtDirec.setText(tblPacientes.getValueAt(rec, 9).toString());
				txtFecha.setText(tblPacientes.getValueAt(rec, 11).toString());
				txtidLugNac.setText(tblPacientes.getValueAt(rec, 12).toString());
				txtidLugRes.setText(tblPacientes.getValueAt(rec, 13).toString());
				txtidPac.setText(tblPacientes.getValueAt(rec, 17).toString());


				recuperarId (comboProvNac, comboCantNac, comboParrNac, txtidLugNac.getText());
				recuperarId (comboProvRes, comboCantRes, comboParrRes, txtidLugRes.getText());
			}	
		}); 

		mostrarTablaPac();
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(RegistrarPacienteGUI.class.getResource("/Imagenes/salud-congreso.jpg")));
		lblFondo.setBounds(0, 0, 1325,690);
		getContentPane().add(lblFondo);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/salud-congreso.jpg"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}


	public void mostrarTablaPac() {

		String cadena = "SELECT pe.*, pa.*, concat(l.codigoLugGeo,'-',l.descripcionLugGeo) as LugarNacimiento, concat(l2.codigoLugGeo,'-',l2.descripcionLugGeo) as LugarResidencia FROM persona pe, paciente pa, lugargeo l, lugargeo l2 WHERE pa.idPersona=pe.idPersona and pa.idLugGeoNacePac=l.idLugarGeo and pa.idLugGeoResidPac=l2.idLugarGeo ";	
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			listaPac.clear();
			while(rs.next()) {

				objPacientes = new pacienteBean();

				objPacientes.setNombre1Per(rs.getString("nombre1Per"));
				try {
					objPacientes.setNombre2Per(rs.getString("nombre2Per"));
				}catch (Exception n2) {
					objPacientes.setNombre2Per("");
				}
				objPacientes.setApellido1Per(rs.getString("apellido1Per"));
				try {
					objPacientes.setApellido2Per(rs.getString("apellido2Per"));
				} catch (Exception a2) {
					objPacientes.setApellido2Per("");
				}
				try {
					objPacientes.setCorreoPer(rs.getString("correoPer"));
				}catch (Exception co) {
					objPacientes.setCorreoPer("");
				}
				objPacientes.setCedulaPer(rs.getString("cedulaPer"));
				objPacientes.setTelefonoPer(rs.getString("telefonoPer"));
				objPacientes.setHclPac(rs.getString("hclPac"));
				objPacientes.setSexoPac(rs.getString("sexoPac"));
				objPacientes.setDireccionPac(rs.getString("direccionPac"));
				objPacientes.setEstadoCivilPer(rs.getString("estadoCivilPac"));
				try {
					objPacientes.setFechaNacPac(formato.parse(rs.getString("fechaNacPac")));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				objPacientes.setIdLugGeoNacePac(Integer.parseInt(rs.getString("idLugGeoNacePac")));
				objPacientes.setIdLugGeoResPac(Integer.parseInt(rs.getString("idLugGeoResidPac")));
				objPacientes.setLugarNac(rs.getString(18));
				objPacientes.setLugarRes(rs.getString(19));
				objPacientes.setIdPersona(Integer.parseInt(rs.getString("idPersona")));


				fechaNacimiento = LocalDate.parse(rs.getString("fechaNacPac"), formatoFechaNac);
				fechaActual = LocalDate.now();
				periodo = Period.between(fechaNacimiento, fechaActual);
				objPacientes.setEdad(String.valueOf(periodo.getYears()));


				listaPac.add(objPacientes); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e+"\n"+"No se pudo llenar lista");
		}

		for (pacienteBean e: listaPac) {

			arregloDatos[0]=e.getNombre1Per();
			if(e.getNombre2Per()=="") {
				arregloDatos[1]="";
			}else {
				arregloDatos[1]=e.getNombre2Per();
			}
			arregloDatos[2]=e.getApellido1Per();
			if(e.getApellido2Per()=="") {
				arregloDatos[3]="";
			}else {
				arregloDatos[3]=e.getApellido2Per();
			}
			if(e.getCorreoPer()=="") {
				arregloDatos[4]="";
			}else {
				arregloDatos[4]=e.getCorreoPer();	
			}
			arregloDatos[5]=e.getCedulaPer();
			arregloDatos[6]=e.getTelefonoPer();
			arregloDatos[7]=e.getHclPac();
			arregloDatos[8]=e.getSexoPac();
			arregloDatos[9]=e.getDireccionPac();
			arregloDatos[10]=e.getEstadoCivilPer();
			arregloDatos[11]=formato.format(e.getFechaNacPac());
			arregloDatos[12]=String.valueOf(e.getIdLugGeoNacePac());
			arregloDatos[13]=String.valueOf(e.getIdLugGeoResPac());
			arregloDatos[14]=e.getLugarNac();
			arregloDatos[15]=e.getLugarRes();
			arregloDatos[16]=e.getEdad();
			arregloDatos[17]=String.valueOf(e.getIdPersona());

			modelo.addRow(arregloDatos);

			//System.out.println(e.getNombre1Per()+","+e.getNombre2Per()+","+e.getApellido1Per()+","+e.getApellido2Per()+" "+e.getCorreoPer()+" "+e.getDireccionPac()+" "+e.getEstadoCivilPer());

		}	
	} 

	public void llenarProv(JComboBox comboProv) {

		listaProv= new ArrayList<String>();
		listaProv.add("Provincia");

		String cadena = "SELECT * FROM lugargeo WHERE idLugarGeoPadre is null ORDER BY codigoLugGeo";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs.next()) {

				listaProv.add(rs.getString(3));
			}
		} catch (Exception u) {}


		for (String e: listaProv) {
			comboProv.addItem(e);

		}
	}

	public void llenarCanton (JComboBox comboCant, JComboBox comboProv) {


		comboCant.removeAllItems();
		listaCant = new ArrayList<String>();
		listaCant.add("Canton");
		int aux=0;
		String cadena="";

		cadena = "SELECT * FROM lugargeo WHERE descripcionLugGeo = '"+comboProv.getSelectedItem()+"' ";	
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

		try {
			while (rs.next()) {
				aux = Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo"));				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo obtner");
		}


		cadena = "SELECT * FROM lugargeo WHERE idLugarGeoPadre = "+aux+" ORDER BY descripcionLugGeo" ;
		ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs1.next()) {
				listaCant.add(rs1.getString(3));
			}
		} catch (Exception u) {}


		for (String e: listaCant) {
			comboCant.addItem(e);
		}

	}



	public void llenarParroquia(JComboBox comboCant ,JComboBox comboParr) {

		comboParr.removeAllItems();
		listaParr = new ArrayList<String>();
		listaParr.add("Parroquia");
		String cadena="";
		int auxiliar=0;

		cadena = "SELECT * FROM lugargeo WHERE descripcionLugGeo='"+comboCant.getSelectedItem()+"' ";	
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

		try {
			while (rs.next()) {
				auxiliar= Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo"));				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo obtner");
		}


		cadena = "SELECT * FROM lugargeo WHERE idLugarGeoPadre='"+auxiliar+"' ORDER BY descripcionLugGeo";
		ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			while (rs1.next()) {
				listaParr.add(rs1.getString(3));
			}
		} catch (Exception u) {}	

		for (String e: listaParr) {
			comboParr.addItem(e);
			//	System.out.println("Parroquias "+e);
		}

	}

	public void recuperarId (JComboBox comboProv, JComboBox comboCant, JComboBox comboParr, String txtid) {

		String parroquia="", canton="",provincia="";
		int idCant=0, idProv=0;

		String cadena = "SELECT * FROM lugargeo WHERE idLugarGeo = "+Integer.parseInt(txtid)+" ";
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

		while (variablesGlobales.conexionBaseMySql.siguiente(rs)) {
			idCant = Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeoPadre"));		
			parroquia = variablesGlobales.conexionBaseMySql.getString(rs, "descripcionLugGeo");
		}

		String cadena2 = "SELECT * FROM lugargeo WHERE idLugarGeo = "+idCant+" ";
		ResultSet rs1 = variablesGlobales.conexionBaseMySql.consulta(cadena2);

		while (variablesGlobales.conexionBaseMySql.siguiente(rs1)) {
			idProv = Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs1, "idLugarGeoPadre"));		
			canton = variablesGlobales.conexionBaseMySql.getString(rs1, "descripcionLugGeo");
		}

		String cadena3 = "SELECT * FROM lugargeo WHERE idLugarGeo = "+idProv+" ";
		ResultSet rs2 = variablesGlobales.conexionBaseMySql.consulta(cadena3);

		while (variablesGlobales.conexionBaseMySql.siguiente(rs2)) {
			provincia = variablesGlobales.conexionBaseMySql.getString(rs2, "descripcionLugGeo");
		}

		comboProv.setSelectedItem(provincia);
		comboCant.setSelectedItem(canton);
		comboParr.setSelectedItem(parroquia);

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

			txtNom1.setText("");
			txtNom2.setText("");
			txtApe1.setText("");
			txtApe2.setText("");
			txtTelf.setText("");
			txtMail.setText("");
			txtCi.setText("");
			txtHist.setText("");
			txtDirec.setText("");
			comboEstaCiv.setSelectedIndex(0);
			btnGr.clearSelection();
			comboProvNac.setSelectedIndex(0);
			comboCantNac.removeAllItems();
			comboParrNac.removeAllItems();
			comboProvRes.setSelectedIndex(0);
			comboCantRes.removeAllItems();
			comboParrRes.removeAllItems();
			txtidLugNac.setText("");
			txtidLugRes.setText("");
			calendario.setDate(new Date());
			txtFecha.setText("");
			txtidPac.setText("");
			tblPacientes.clearSelection();

		}



		if(evento.getSource()==btnGrabar) {

			boolean correcto=false;
			while (correcto==false) {

				correcto = true;
				String error="";

				//Validar nombre1
				if(txtNom1.getText().trim().length()==0) {
					error+="ERROR Nombre1: No ha ingresado datos.\n";
					correcto=false;
				}else if(!validarPaciente.validarLetras(txtNom1.getText())) {
					error+="ERROR Nombre1: Debe ingresar solo letras.\n";
					correcto=false;
				}else if (txtNom1.getText().trim().length()<3 || txtNom1.getText().trim().length()>15) {
					error+="ERROR Nombre1: Debe ingresar datos de 3 a 15 letras.\n";
					correcto=false;
				}
				//Validar nombre2
				if(txtNom2.getText().trim().length()==0) {
				}else if(!validarPaciente.validarLetras(txtNom2.getText())) {
					error+="ERROR Nombre2: Debe ingresar solo letras.\n";
					correcto=false;
				}else if (txtNom2.getText().trim().length()<3 || txtNom2.getText().trim().length()>15) {
					error+="ERROR Nombre2: Debe ingresar datos de 3 a 15 letras.\n";
					correcto=false;
				}
				//Validar apellido1
				if(txtApe1.getText().trim().length()==0) {
					error+="ERROR Apellido1: No ha ingresado datos.\n";
					correcto=false;
				}else if(!validarPaciente.validarLetras(txtApe1.getText())) {
					error+="ERROR Apellido1: Debe ingresar solo letras.\n";
					correcto=false;
				}else if (txtApe1.getText().trim().length()<3 || txtApe1.getText().trim().length()>15) {
					error+="ERROR Apellido1: Debe ingresar datos de 3 a 15 letras.\n";
					correcto=false;
				}
				//Validar apellido2
				if(txtApe2.getText().trim().length()==0) {
				}else if(!validarPaciente.validarLetras(txtApe2.getText())) {
					error+="ERROR Apellido2: Debe ingresar solo letras.\n";
					correcto=false;
				}else if (txtApe2.getText().trim().length()<3 || txtApe2.getText().trim().length()>15) {
					error+="ERROR Apellido2: Debe ingresar datos de 3 a 15 letras.\n";
					correcto=false;
				}

				//Validar E-mail
				if(txtMail.getText().trim().length()==0) {

				} else if(!validarPaciente.validarEmail(txtMail.getText())) {
					error += "ERROR Correo: Correo ingresado no valido.\n";
					correcto=false;
				}

				//Validar cedula
				if(txtCi.getText().trim().length()==0) {
					error+="ERROR Cedula: No ha ingresado datos.\n";
					correcto=false;
				} else if(!validarPaciente.validarCedula(txtCi.getText())) {
					error += "ERROR Cedula: Cedula ingresada no valida.\n";
					correcto=false;
				}

				//Validar Telefono
				if(txtTelf.getText().trim().length()==0) {
					error+="ERROR Telefono: No ha ingresado datos.\n";
					correcto=false;
				}else if(!validarPaciente.validarNumero(txtTelf.getText())) {
					error+="ERROR Telefono: Debe ingresar solo números.\n";
					correcto=false;
				}else if(!validarPaciente.validarTelefono(txtTelf.getText())) {
					error += "ERROR Telefono: Número telefonico no valido.\n";
					correcto = false;
				}

				//Historia clinica
				if(txtHist.getText().trim().length()==0) {
					error+="ERROR Historia Clínica: No ha ingresado datos.\n";
					correcto=false;
				}else if(txtHist.getText().length()!=8) {
					error+="ERROR Historia Clínica: Número incorrecto.\n";
					correcto=false;
				}

				//Validar sexo
				if (!(masculino.isSelected() || femenino.isSelected())) {
					error +="ERROR: No a seleccionadp sexo\n";
					correcto=false;
				}

				if(masculino.isSelected()) {
					sexo = "M";
				} else if (femenino.isSelected()) {
					sexo = "F";
				}

				//Direccion
				if(txtDirec.getText().trim().length()==0) {
					error+="ERROR Direccion: No ha ingresado datos.\n";
					correcto=false;
				} 

				//Validar Estado Civil
				if (comboEstaCiv.getSelectedIndex()==0) {
					error += "ERROR: No ha seleccionado su estado civil.\n";
					correcto=false;
				} 
				if(comboEstaCiv.getSelectedIndex()==1) {
					estCivil = "C";
				} else if(comboEstaCiv.getSelectedIndex()==2) {
					estCivil = "S";
				} else if(comboEstaCiv.getSelectedIndex()==3) {
					estCivil = "V";
				} else if(comboEstaCiv.getSelectedIndex()==4) {
					estCivil = "D";
				}

				//Fecha de Nacimiento
				if(txtFecha.getText().equals("")) {
					error+="ERROR Fecha de Nacimiento: No ha llenado campo.\n";
					correcto=false;
				}

				//Lugar de nacimiento-residencia
				if(txtidLugNac.getText().equals("")) {
					error+="ERROR Lugar de Nacimiento: No ha seleccionado datos.\n";
					correcto=false;
				}

				if(txtidLugRes.getText().equals("")) {
					error+="ERROR Lugar de Residencia: No ha seleccionado datos.\n";
					correcto=false;
				} 


				if (!error.equals("")){
					JOptionPane.showMessageDialog(null, error , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}

			objPacientes = new pacienteBean();
			objPacientes.setNombre1Per(txtNom1.getText().toUpperCase());
			objPacientes.setNombre2Per(txtNom2.getText().toUpperCase());
			objPacientes.setApellido1Per(txtApe1.getText().toUpperCase());
			objPacientes.setApellido2Per((txtApe2.getText().toUpperCase()));
			objPacientes.setCorreoPer(txtMail.getText());
			objPacientes.setCedulaPer(txtCi.getText());
			objPacientes.setTelefonoPer(txtTelf.getText());
			objPacientes.setHclPac(txtHist.getText());
			objPacientes.setSexoPac(sexo);
			objPacientes.setDireccionPac(txtDirec.getText().toUpperCase());
			objPacientes.setEstadoCivilPer(estCivil);
			try {
				objPacientes.setFechaNacPac(formato.parse(txtFecha.getText().trim()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			objPacientes.setIdLugGeoNacePac(Integer.parseInt(txtidLugNac.getText()));
			objPacientes.setIdLugGeoResPac(Integer.parseInt(txtidLugRes.getText()));

			if(txtidPac.getText().trim().length()==0) {

				boolean ced=false;
				while (ced==false) {
					String error="";
					ced=true;
					if(validarPaciente.cedulaRepetida(txtCi.getText())) {
						error+="CEDULA ingresda ya existe";
						ced=false;
					}
					if (!error.equals("")){
						JOptionPane.showMessageDialog(null, error , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
						return;
					} 
				}

				String cadena3="";

				cadena3 = "INSERT INTO persona (nombre1Per, nombre2Per, apellido1Per,apellido2Per,correoPer,cedulaPer,telefonoPer) VALUES ('"+objPacientes.getNombre1Per()+"','"+objPacientes.getNombre2Per()+"','"+objPacientes.getApellido1Per()+"','"+objPacientes.getApellido2Per()+"','"+objPacientes.getCorreoPer()+"','"+objPacientes.getCedulaPer()+"','"+objPacientes.getTelefonoPer()+"')";
				variablesGlobales.conexionBaseMySql.insertar(cadena3);

				cadena3 = "SELECT * FROM persona WHERE cedulaPer = '"+objPacientes.getCedulaPer()+"' ";
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena3);

				try {
					while (rs.next()) {
						objPacientes.setIdPersona(Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se pudo obtner ID");
				}
				cadena3 = "INSERT INTO paciente (hclPac, sexoPac, direccionPac, estadoCivilPac, fechaNacPac, idLugGeoNacePac, idLugGeoResidPac, idPersona) VALUES ('"+objPacientes.getHclPac() +"','"+objPacientes.getSexoPac()+"','"+objPacientes.getDireccionPac()+"','"+objPacientes.getEstadoCivilPer()+"','"+formato.format(objPacientes.getFechaNacPac())+"',"+objPacientes.getIdLugGeoNacePac()+","+objPacientes.getIdLugGeoResPac()+","+objPacientes.getIdPersona()+")";
				variablesGlobales.conexionBaseMySql.insertar(cadena3);
				System.out.println(cadena3);

				JOptionPane.showMessageDialog(null, "Se agrego correctamente el nuevo paciente");

			} else {
				objPacientes.setIdPersona(Integer.parseInt(txtidPac.getText()));
				String cadena="";
				cadena = "UPDATE persona SET nombre1Per = '"+objPacientes.getNombre1Per()+"' , nombre2Per = '"+objPacientes.getNombre2Per()+"' , apellido1Per = '"+objPacientes.getApellido1Per()+"',apellido2Per = '"+objPacientes.getApellido2Per()+"',correoPer = '"+objPacientes.getCorreoPer()+"',"+"telefonoPer = '"+objPacientes.getTelefonoPer()+"' WHERE idPersona = "+objPacientes.getIdPersona()+"";
				//System.out.println(cadena);
				System.out.println("Valore de persona:  "+objPacientes.getIdPersona());
				variablesGlobales.conexionBaseMySql.insertar(cadena);

				cadena ="SELECT * FROM persona WHERE cedulaPer = '"+objPacientes.getCedulaPer()+"'";
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

				try {
					while(rs.next()) {
						objPacientes.setIdPersona(Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
						//System.out.println("Id paciente: "+Integer.parseInt(variablesGlobales.conexionBaseMySql.getString(rs, "idPersona")));
					}
				} catch (NumberFormatException | SQLException e) {	}

				cadena= "UPDATE paciente SET  hclPac = '"+objPacientes.getHclPac()+"', sexoPac= '"+objPacientes.getSexoPac()+"', direccionPac= '"+objPacientes.getDireccionPac()+"',estadoCivilPac = '"+objPacientes.getEstadoCivilPer()+"', fechaNacPac= '"+formato.format(objPacientes.getFechaNacPac())+"',"
						+ "idLugGeoNacePac = "+objPacientes.getIdLugGeoNacePac()+" ,idLugGeoResidPac = "+objPacientes.getIdLugGeoResPac()+"  WHERE idPersona = '"+objPacientes.getIdPersona()+"'";
				//System.out.println(cadena);
				variablesGlobales.conexionBaseMySql.insertar(cadena);

				JOptionPane.showMessageDialog(null, "Se actualizo correctamente paciente");
			}

			modelo.setRowCount(0);
			mostrarTablaPac();
			btnCancelar.doClick(); 


		}
	} 

	public static void main (String []args) {

		RegistrarPacienteGUI paciente = new RegistrarPacienteGUI();
		paciente.setBounds(0,0,1325,690);
		paciente.setVisible(true);
		//paciente.setLocationRelativeTo(null); //Aparezaca al centro de pantalla
		paciente.setResizable(false); //no deja modificar el tamaño de la interfaz

	}
}

