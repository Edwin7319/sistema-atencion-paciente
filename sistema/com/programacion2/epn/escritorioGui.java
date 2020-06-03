package com.programacion2.epn;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.programacion2.epn.gui.claseSuma;
import com.programacion2.epn.gui.ClaseGrabar;
import com.programacion2.epn.utilitarios.LanzadorReportes;

public class escritorioGui extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	protected escritorio escritorio=new escritorio();
	public static void main(String[] args) {
		escritorioGui main=new escritorioGui();
		main.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public escritorioGui() {
		super("Clases Java");
		this.setGui();
		this.setBounds(0, 0, 800, 600);
		this.setVisible(true);
	}

	private void setGui() {
        Container contenedor = this.getContentPane();
        contenedor.add(escritorio);
        
		 JMenuBar menuBarra=new JMenuBar();
		 this.setJMenuBar(menuBarra);
		
		  JMenu archivo = new JMenu("Primer submenu");
	      JMenuItem nueva = new JMenuItem("primera OPCION del primer submenu (CALCULAR)");	
	      nueva.setIcon(new ImageIcon("imagenes/new.png"));
	      nueva.setActionCommand("nueva");
	      nueva.addActionListener(this);
	      archivo.add(nueva);
	      menuBarra.add(archivo);
	      
	     
	      JMenuItem grabar = new JMenuItem("Segunda OPCION del primer submenu (Grabar)");	
	      grabar.setIcon(new ImageIcon("imagenes/new.png"));
	      grabar.setActionCommand("grabar");
	      grabar.addActionListener(this);
	      archivo.add(grabar);
	      menuBarra.add(archivo);
	      
	      JMenu edicion = new JMenu("segundo submenu (CALCULAR");
	      JMenuItem salir = new JMenuItem("primera OPCION del segundo submenu (REPORTE)");
	      salir.setActionCommand("edicion");
	      salir.addActionListener(this);
	      edicion.add(salir);
	      edicion.addSeparator();
	      menuBarra.add(edicion);
	}

	public void actionPerformed(ActionEvent evento) {
		String comando=evento.getActionCommand();
		if(comando.equals("nueva")) {
			claseSuma cs=new claseSuma();
			escritorio.add(cs);
		}else if(comando.equals("edicion")){
			 HashMap parametros= new HashMap();
			 parametros.put("valor", "xxxxvalorxxxx");
			LanzadorReportes lanzadorReportes=new LanzadorReportes(new JFrame(),"Reporte");
			lanzadorReportes.cargarReporte("reportes/listaEmpleado.jasper",parametros,variablesGlobales.conexionBaseMySql.getConeccion() );
			lanzadorReportes.setSize(new Dimension(800,600));
			lanzadorReportes.show(true);
		}
		else if(comando.equals("grabar")) {
			ClaseGrabar cg=new ClaseGrabar();
			escritorio.add(cg);
			
		}
	}
}
