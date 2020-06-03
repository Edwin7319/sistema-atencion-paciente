package com.programacion2.epn.gui;



import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.*;

import com.programacion2.epn.variablesGlobales;
import com.programacion2.epn.utilitarios.LanzadorReportes;


public class claseSuma extends JInternalFrame implements ActionListener{
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JLabel membrete1, membrete2, membrete3;
   private JButton boton1;
   private JButton boton2;
   private JButton boton3;
   private JButton boton4;
   private JTextField valor1, valor2, valor3;


	int numeroValor1=0;
	int numeroValor2=0;
	int resultado=0;

   // set up GUI (constructor)
   public claseSuma()
   {
     super( "Mi Primera Interfase",true,true,true,true );
     
      // get content pane and set its layout
      Container contenedor = getContentPane();
      contenedor.setLayout( new FlowLayout() );
      contenedor.setBounds(10, 200, 100, 100);
      contenedor.setBackground(Color.lightGray);
      // JLabel
      membrete1 = new JLabel( "primer valor");
      membrete1.setToolTipText( "ayuda rápida primer valor" );
      
      contenedor.add( membrete1);
      membrete1.setBounds(30, 5, 10, 20);
      
      // JTextField de tamaño 3
      valor1 = new JTextField( 3 );
      contenedor.add( valor1);
      

      // JLabel 
      Icon bug = new ImageIcon( "bug1.gif" );
      membrete2 = new JLabel( "segundo valor", bug, SwingConstants.LEFT );
      membrete2.setToolTipText( "ayuda rápida segundo valor" );
      contenedor.add( membrete2 );
      
      
      // JTextField de tamaño 3
      valor2 = new JTextField( 3 );
      contenedor.add( valor2);

      // JLabel 
      membrete3 = new JLabel();
      membrete3.setText( "RESULTADOS" );
      
      membrete3.setIcon( bug );
      membrete3.setHorizontalTextPosition( SwingConstants.CENTER );
      membrete3.setVerticalTextPosition( SwingConstants.BOTTOM );
      membrete3.setToolTipText( "ayuda rápida resultado" );
      contenedor.add( membrete3 );
      
      // JTextField de tamaño 3
      valor3 = new JTextField( 3 );
      contenedor.add( valor3);
    
      	      
      //boton de comandos
      boton1= new JButton();
      boton1.setText("Calcular");
      boton1.setBackground(Color.cyan);
      
      contenedor.add(boton1);
		boton1.setActionCommand("boton1");
		boton1.addActionListener(this);

		//boton de comandos
	      boton2= new JButton();
	      boton2.setText("A consola");
	      boton2.setBackground(Color.cyan);
	      
	      contenedor.add(boton2);
	      boton2.setActionCommand("boton2");
	      boton2.addActionListener(this);
	      
	      
	      //boton de comandos
	      boton3= new JButton();
	      boton3.setText("Reporte");
	      boton3.setBackground(Color.cyan);
	      
	      contenedor.add(boton3);
	      boton3.setActionCommand("boton3");
	      boton3.addActionListener(this);
	      
	      //boton de comandos
	      boton4= new JButton();
	      boton4.setText("Reporte Mejorado");
	      boton4.setBackground(Color.cyan);
	      
	      contenedor.add(boton4);
	      boton4.setActionCommand("boton4");
	      boton4.addActionListener(this);

     // setSize( 400, 170 );
      //setVisible( true );
      super.setBounds(100, 200, 200, 500);
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
	numeroValor1=Integer.parseInt(valor1.getText());
	numeroValor2=Integer.parseInt(valor2.getText());
	if(a.equals("boton1"))
	{

		resultado=numeroValor1+numeroValor2;
		valor3.setText(String.valueOf(resultado));
		
	}
	if(a.equals("boton2"))
	{	
		
		String cad="SELECT * FROM TIPOPRODUCTO WHERE idtipoproducto="+numeroValor1;
		ResultSet rs = variablesGlobales.conexionBaseMySql.consulta(cad);
		while(variablesGlobales.conexionBaseMySql.siguiente(rs)) 
		{
				System.out.println("IDTIPOPRODUCTO:  "+variablesGlobales.conexionBaseMySql.getString(rs, "idtipoproducto")
						+" Descripcion: "+variablesGlobales.conexionBaseMySql.getString(rs, "descripcionTipPro"));
		}
		
		

	}
	
	if(a.equals("boton3"))
	{
		HashMap parametros= new HashMap();
		parametros.put("valor", "esto es pasado desde el action de java Sheila");
		LanzadorReportes lanzadorReportes=new LanzadorReportes(new JFrame(),"Reporte");
		lanzadorReportes.cargarReporte("reportes/listaPacientes.jasper",parametros,variablesGlobales.conexionBaseMySql.getConeccion() );
		lanzadorReportes.setSize(new Dimension(800,600));
		lanzadorReportes.show(true);

	}
	
	if(a.equals("boton4"))
	{
	
		HashMap parametros= new HashMap();
		parametros.put("valor", "TITULO REPORTE: lista de usuarios");
		LanzadorReportes lanzadorReportes=new LanzadorReportes(new JFrame(),"Reporte");
		lanzadorReportes.cargarReporte("reportes/listaPacientes.jasper",parametros,variablesGlobales.conexionBaseMySql.getConeccion() );
		lanzadorReportes.setSize(new Dimension(800,600));
		lanzadorReportes.show(true);

	}
	
	
}

} // FIN CLASE PrimeraInterfase


