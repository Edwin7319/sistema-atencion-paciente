package com.programacion2.epn.utilitarios;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JRViewer;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.sql.Connection;
import java.util.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author area
 *
 */
public class LanzadorReportes extends JDialog{

	private static final long serialVersionUID = 308251665095677784L;
	/**
	 * 
	 */
	JasperPrint archivoImprimir;
	JRViewer panelVisor;
	protected HashMap parametros;
	protected String nombreArchivo; 
	public LanzadorReportes(final Frame padre, final String titulo){
		super(padre, titulo,true);
		this.setResizable(true);
	}
	public LanzadorReportes(){
		super();
	}


	public void cargarReporte(final String nombreArchivo,final HashMap parametros, final JRResultSetDataSource datos){				
		try {
			this.archivoImprimir = JasperFillManager.fillReport(nombreArchivo, parametros,datos );
			this.panelVisor= new JRViewer(this.archivoImprimir);
			this.getContentPane().add(this.panelVisor,BorderLayout.CENTER);
		} catch (final JRException e) {		
			e.printStackTrace();
		}
	}
	
	public void cargarReporte(final String nombreArchivo,final HashMap parametros, final JREmptyDataSource datos){
		
	
	
	
	
	}
	public void cargarReporte(final String nombreArchivo,final HashMap parametros, final JRTableModelDataSource  datos){
		try {
			this.archivoImprimir = JasperFillManager.fillReport(nombreArchivo, parametros,datos );
			this.panelVisor= new JRViewer(this.archivoImprimir);
			this.getContentPane().add(this.panelVisor,BorderLayout.CENTER);
			
		} catch (final JRException e) {
		
			e.printStackTrace();
		}		
	}

	@SuppressWarnings("unchecked")
	public void cargarReporte(final String nombreArchivo,final HashMap parametros, final Connection datos){
		
		try {
			this.parametros=parametros;
			System.out.println("parametros reporte "+parametros);
			System.out.println("nombreArchivo reporte "+nombreArchivo);
			this.archivoImprimir = JasperFillManager.fillReport(nombreArchivo, this.parametros,datos );
			this.panelVisor= new JRViewer(this.archivoImprimir);
			this.getContentPane().add(this.panelVisor,BorderLayout.CENTER);
		} catch (final JRException e) {		
			e.printStackTrace();
		}
	}

	
	public void cargarReporte(final String nombreArchivo,final HashMap parametros, final JRBeanCollectionDataSource datos){
		
		try {
			this.archivoImprimir = JasperFillManager.fillReport(nombreArchivo, parametros,datos );
			this.panelVisor= new JRViewer(this.archivoImprimir);
			this.getContentPane().add(this.panelVisor,BorderLayout.CENTER);
			
		} catch (final JRException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	public void setVisible(final boolean ver){
		
		if(this.panelVisor!=null)
			super.setVisible(ver);
		else{
			
			JOptionPane.showMessageDialog(this, // padre
		               "Se debe llenar el reporte antes de llamar al visor", // mensajito
		                "Reporte Vacio", //titulo
		                JOptionPane.ERROR_MESSAGE);
			
			this.dispose();
		}
	}


}

