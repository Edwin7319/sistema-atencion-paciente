package com.programacion2.epn.gui;



import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.*;

import com.programacion2.epn.variablesGlobales;
import com.programacion2.epn.utilitarios.LanzadorReportes;


public class ClaseGrabar extends JInternalFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel membrete1, membrete2, membrete3;
	private JButton boton1;
	private JTextField codigo, descripcion, idPadre;


	String strCodigo="";
	String strDescripcion="";
	int intIdPadre=0;

	// set up GUI (constructor) 
	public ClaseGrabar()
	{
		super( "Grabar datos en la tabla LUGARGEO del esquema de base de datos",true,true,true,true );

		// get content pane and set its layout
		Container contenedor = getContentPane();
		contenedor.setLayout( new FlowLayout() );
		contenedor.setBounds(10, 200, 100, 100);
		contenedor.setBackground(Color.lightGray);
		// JLabel
		membrete1 = new JLabel( "codigo lugar");
		membrete1.setToolTipText( "Formato: 99[99[99]]" );

		contenedor.add( membrete1);
		membrete1.setBounds(30, 5, 10, 20);

		// JTextField de tamaño 3
		codigo = new JTextField( 8 );
		contenedor.add( codigo);


		// JLabel 

		membrete2 = new JLabel( "descripcion",null, SwingConstants.LEFT );
		membrete2.setToolTipText( "ayuda rápida segundo valor" );
		contenedor.add( membrete2 );


		// JTextField de tamaño 3
		descripcion = new JTextField( 30 );
		contenedor.add( descripcion);


		// JLabel 

		membrete3 = new JLabel( "idPadre", null, SwingConstants.LEFT );
		membrete3.setToolTipText( "ayuda rápida tercer valor" );
		contenedor.add( membrete3 );


		// JTextField de tamaño 3
		idPadre = new JTextField( 30 );
		contenedor.add( idPadre);



		//boton de comandos
		boton1= new JButton();
		boton1.setText("Grabar");
		boton1.setBackground(Color.cyan);

		contenedor.add(boton1);
		boton1.setActionCommand("grabar");
		boton1.addActionListener(this);


		// setSize( 400, 170 );
		//setVisible( true );
		super.setBounds(100, 200, 200, 200);
		this.setMaximumSize(new Dimension(100,100));
		this.setVisible(true);
		this.pack();

	} // FIN constructor PrimeraInterfase

	public static void main( String args[] )
	{ 
		claseSuma application = new claseSuma();
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String a = e.getActionCommand();
		String cad;
		if(a.equals("grabar"))
		{
			strCodigo=(codigo.getText().trim());
			strDescripcion=(descripcion.getText().trim());
			
			if(idPadre.getText().trim().length()==0){
				cad = "INSERT INTO LUGARGEO (DESCRIPCIONLUGGEO, CODIGOLUGGEO) VALUES ('"+strDescripcion+"','"+strCodigo+"')";
			}else{
				intIdPadre=Integer.parseInt(idPadre.getText().trim());
				cad = "INSERT INTO LUGARGEO (DESCRIPCIONLUGGEO, CODIGOLUGGEO, IDLUGARGEOPADRE) VALUES ('"+strDescripcion+"','"+strCodigo+"',"+intIdPadre+")";
			}
			System.out.println(cad);
			variablesGlobales.conexionBaseMySql.insertar(cad);


		}

	}

} // FIN CLASE PrimeraInterfase
