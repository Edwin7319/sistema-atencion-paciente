package GUI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.jdbc.PreparedStatement;
import com.programacion2.epn.conexionBaseMySql;
import com.programacion2.epn.variablesGlobales;

import ServiciosGenerales.validarPaciente;
import clasesBean.Lugar_Geo;
import java.awt.Color;

public class RegistrarLugarGeoGUI extends JInternalFrame implements ActionListener{


	private String [] arregloDatos = new String [4]; //{"ID LugarGeo","Codigo", "Descripcion", "ID LugarGeo Padre"};

	private JLabel lblIDLugGeo, lblCodigo, lblDescripcion, lblIDLugGeoPad, lblFondo;
	private JButton btnGuardarTbl, btnCancelar, btnSalir, btnGuardarArr;
	private JScrollPane desplazar ;
	private JTextArea areaTextoTabla;
	private JTable tblLugGeo;
	private JTextField txtIDLugGeo, txtCodigoLugGeo, txtDescripcion, txtLugGeoPad;
	private DefaultTableModel modelo;

	Lugar_Geo objLugGeo = new Lugar_Geo();
	String texto;

	private ArrayList <Lugar_Geo> listaGeo = new ArrayList<Lugar_Geo>();

	public RegistrarLugarGeoGUI() {

		super ("REGISTRAR LUGAR GEOGRAFICO");
		setBorder(null);
		getContentPane().setLayout(null); //Indica al programa que se usa elementos con coordenadas
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

		lblIDLugGeo = new JLabel("ID Lugar Geografico :");
		lblIDLugGeo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIDLugGeo.setForeground(Color.WHITE);
		lblIDLugGeo.setBounds(20,10,142,30);
		getContentPane().add(lblIDLugGeo);
		lblCodigo = new JLabel ("Codigo :");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(20,41,142,30);
		getContentPane().add(lblCodigo);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescripcion.setBounds(20, 71, 142, 30);
		getContentPane().add(lblDescripcion);

		lblIDLugGeoPad = new JLabel("ID Lugar Geografico Padre :");
		lblIDLugGeoPad.setForeground(Color.WHITE);
		lblIDLugGeoPad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIDLugGeoPad.setBounds(20, 99, 197, 30);
		getContentPane().add(lblIDLugGeoPad);

		btnGuardarTbl = new JButton("GRABAR");
		btnGuardarTbl.setBackground(new Color(255, 255, 255));
		btnGuardarTbl.setBounds(75, 158, 100, 23);
		getContentPane().add(btnGuardarTbl);
		btnGuardarTbl.addActionListener(this);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setBounds(206, 158, 100, 23);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(this);

		btnSalir = new JButton("SALIR");
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBounds(338, 158, 100, 23);
		getContentPane().add(btnSalir);
		btnSalir.addActionListener(this);

		txtIDLugGeo = new JTextField();
		txtIDLugGeo.setBounds(227, 15, 105, 20);
		txtIDLugGeo.setBackground(new Color(255, 255, 255));
		txtIDLugGeo.setEditable(false);
		getContentPane().add(txtIDLugGeo);
		txtIDLugGeo.setColumns(10);

		txtCodigoLugGeo = new JTextField();
		txtCodigoLugGeo.setBounds(227, 46, 105, 20);
		getContentPane().add(txtCodigoLugGeo);
		txtCodigoLugGeo.setColumns(10);
		txtCodigoLugGeo.addActionListener(this);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(227, 76, 150, 20);
		txtDescripcion.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				buscaId (txtCodigoLugGeo, txtLugGeoPad, txtCodigoLugGeo);
			}

		});
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtLugGeoPad = new JTextField();
		txtLugGeoPad.setBounds(227, 109, 108, 20);
		txtLugGeoPad.setBackground(new Color(255, 255, 255));
		getContentPane().add(txtLugGeoPad);
		txtLugGeoPad.setColumns(10);
		txtLugGeoPad.setEditable(false);


		areaTextoTabla = new JTextArea();
		desplazar = new JScrollPane(areaTextoTabla);
		desplazar.setBounds(20, 192, 450, 232);
		getContentPane().add(desplazar);


		modelo = new DefaultTableModel(){
			public boolean isCellEditable (int fila, int columna) {
				return false;
			}
		};

		tblLugGeo = new JTable(modelo);
		desplazar.setViewportView(tblLugGeo);
		desplazar.setViewportView(tblLugGeo);

		modelo.addColumn("ID LugarGeo");
		modelo.addColumn("Codigo");
		modelo.addColumn("Descripción");
		modelo.addColumn("ID LugarGeo Padre");

		tblLugGeo.getTableHeader().setReorderingAllowed(false) ;

		tblLugGeo.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int rec = tblLugGeo.getSelectedRow();

				txtIDLugGeo.setText(tblLugGeo.getValueAt(rec, 0).toString());
				txtCodigoLugGeo.setText(tblLugGeo.getValueAt(rec, 1).toString());
				txtDescripcion.setText(tblLugGeo.getValueAt(rec, 2).toString());
				txtLugGeoPad.setText(tblLugGeo.getValueAt(rec, 3).toString());
			}
		});
		mostrarTablaLugGeo();
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(RegistrarLugarGeoGUI.class.getResource("/Imagenes/9.jpg")));
		lblFondo.setBounds(0, 0, 500, 500);
		getContentPane().add(lblFondo);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/9.jpg"));
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}


	public void mostrarTablaLugGeo() {

		String cadena = "SELECT * FROM lugargeo ORDER BY codigoLugGeo";	
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
		try {
			listaGeo.clear();
			while(rs.next()) {
				objLugGeo = new Lugar_Geo();
				objLugGeo.setIdLugarGeo(Integer.parseInt(rs.getString(1)));
				objLugGeo.setCodigoLugarGeo(rs.getString(2));
				objLugGeo.setDescripcionLugarGeo(rs.getString(3));
				try {
					objLugGeo.setIdLugarGeoPadre(Integer.parseInt(rs.getString(4)));
				} catch (Exception d) {
					objLugGeo.setIdLugarGeoPadre(0);
				}

				listaGeo.add(objLugGeo);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo llenar lista");
		}

		for (Lugar_Geo e: listaGeo) {

			arregloDatos[0] = String.valueOf(e.getIdLugarGeo());
			arregloDatos[1] = e.getCodigoLugarGeo();
			arregloDatos[2] = e.getDescripcionLugarGeo();
			if(e.getIdLugarGeoPadre()==0) {
				arregloDatos[3] = "";
			}else {
				arregloDatos[3] = String.valueOf(e.getIdLugarGeoPadre());
			}
			modelo.addRow(arregloDatos);
		}
	}

	public void buscaId (JTextField codigo,  JTextField idLugGeoPad, JTextField codigoLug) {

		try {
			String auxiliar = codigo.getText();
			String vacio="";
			String cadena="";
			String idcanton="";
			String idprovincia="";

			if(auxiliar.trim().length()==6) {

				vacio = auxiliar.substring(0, 4);
				cadena = "SELECT * FROM lugargeo WHERE codigoLugGeo = '"+vacio+"'";
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);

				try {
					while(rs.next()) {
						idcanton = variablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo");
					}
				} catch (SQLException e) {
				}
				idLugGeoPad.setText(idcanton);

				if(auxiliar.trim().length()==6 && idLugGeoPad.getText().trim().length()==0) {
					codigoLug.setText("");
					JOptionPane.showMessageDialog(null, "No existe Canton para la Parroquia que desa ingresar." , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
				}

			} else if(auxiliar.trim().length()==4) {

				vacio = auxiliar.substring(0,2);
				cadena = "SELECT * FROM lugargeo WHERE codigoLugGeo = '"+vacio+"'";
				ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cadena);
				try {
					while(rs.next()) {
						idprovincia = variablesGlobales.conexionBaseMySql.getString(rs, "idLugarGeo");
					}
				} catch (SQLException e) {
				}
				idLugGeoPad.setText(idprovincia);
				
				if(auxiliar.trim().length()==4 && idLugGeoPad.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "No existe Provincia para el Canton que desa ingresar." , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
					codigoLug.setText("");
				}else if(codigo.getText().trim().length()==0) {
					
				}

			}else if(auxiliar.trim().length()==2) {

				idLugGeoPad.setText("");

			}else if(auxiliar.trim().length()==0 ) {
				
			}else if(auxiliar.trim().length()!=2 || auxiliar.trim().length()!=4 || auxiliar.trim().length()!=6 ) {
				codigoLug.setText("");
				JOptionPane.showMessageDialog(null, "Codigo de Lugar Geografico no valido" , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
			}
		}catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Codigo ingresado no valido" , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {


		if(e.getSource()==btnSalir){
			try {
				this.setClosed(true);
			} catch (PropertyVetoException error) {
				error.printStackTrace();
			}
		}

		if(e.getSource()==btnCancelar) {           
			txtIDLugGeo.setText("");
			txtCodigoLugGeo.setText("");
			txtDescripcion.setText("");
			txtLugGeoPad.setText("");
			tblLugGeo.clearSelection();

		}

		if(e.getSource()==txtCodigoLugGeo) {
			String auxiliar = txtCodigoLugGeo.getText().substring(4,5);

			System.out.println("ultimos dos: "+auxiliar);
			String cadena="";
			cadena = "SELECT * FROM lugargeo WHERE  ";
		}


		if(e.getSource()==btnGuardarTbl) {


			objLugGeo = new Lugar_Geo();
			try {
				objLugGeo.setIdLugarGeo(Integer.parseInt(txtIDLugGeo.getText()));
			} catch (Exception idLug) {
				objLugGeo.setIdLugarGeo(0);
			}
			objLugGeo.setCodigoLugarGeo(txtCodigoLugGeo.getText().trim()); 
			objLugGeo.setDescripcionLugarGeo(txtDescripcion.getText().trim().toUpperCase());

			if(txtIDLugGeo.getText().equals("")) {
				boolean correcto=false;
				while (correcto==false) {
					correcto = true;
					String error="";
					if(validarPaciente.codigoLugGeo(txtCodigoLugGeo.getText())) {
						error+="El codigo geografico ya existe.\n";
						correcto=false;
					}
					if (!error.equals("")){
						JOptionPane.showMessageDialog(null, error , "ERROR AL DIGITAR", JOptionPane.ERROR_MESSAGE);
						return;
					} 
				}
				
				String cadena="";
				try {
					objLugGeo.setIdLugarGeoPadre(Integer.parseInt(txtLugGeoPad.getText()));
					cadena = "INSERT INTO lugargeo (codigoLugGeo, descripcionLugGeo, idLugarGeoPadre) VALUES ('"+objLugGeo.getCodigoLugarGeo()+"','"+objLugGeo.getDescripcionLugarGeo()+"', "+objLugGeo.getIdLugarGeoPadre()+" )";

				}catch(Exception provincia) {
					cadena = "INSERT INTO lugargeo (codigoLugGeo, descripcionLugGeo) VALUES ('"+objLugGeo.getCodigoLugarGeo()+"','"+objLugGeo.getDescripcionLugarGeo()+"' )";
				}
				variablesGlobales.conexionBaseMySql.insertar(cadena);
			}else {
				String cadena2="";
				cadena2 ="UPDATE lugargeo SET codigoLugGeo = '"+objLugGeo.getCodigoLugarGeo()+"', descripcionLugGeo = '"+objLugGeo.getDescripcionLugarGeo()+"' WHERE idLugarGeo = "+objLugGeo.getIdLugarGeo()+" "; 
				variablesGlobales.conexionBaseMySql.insertar(cadena2);
			}

			modelo.setRowCount(0);
			btnCancelar.doClick();
			mostrarTablaLugGeo();

		}

		if(e.getSource()==btnGuardarArr) {
		}
	}
	public static void main(String[] args) {

		RegistrarLugarGeoGUI capturar = new RegistrarLugarGeoGUI();
		capturar.setBounds(0,0,500,500);
		capturar.setVisible(true);
		//capturar.setLocationRelativeTo(null); 
		capturar.setResizable(false); 
	}

}
