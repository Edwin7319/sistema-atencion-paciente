package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;

public class MenuGUI extends JFrame implements ActionListener{

	private JMenuBar  menuBar;
	private JMenu menRegistrar, menActualizaciones,menReportes,mnSalir;
	private JMenuItem menuItemSigVit, menuItemAten, menuItemPac,menuItemImpPac,menuItemLugGeo,menuItemSalir, menuItemReportes;

	static MenuGUI ventanaMenu = new MenuGUI();

	public MenuGUI() {

		super("Consulta Externa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuGUI.class.getResource("/Imagenes/Cruz.png")));
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 255, 255));

		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);

		menRegistrar = new JMenu("REGISTRAR");
		menuBar.add(menRegistrar);

		menuItemSigVit = new JMenuItem("Registrar Signos Vitales");
		menuItemSigVit.setBackground(new Color(255, 255, 255));
		menuItemSigVit.addActionListener(this);
		menRegistrar.add(menuItemSigVit);
		menuItemAten = new JMenuItem("Registrar Atención");
		menuItemAten.setBackground(new Color(255, 255, 255));
		menuItemAten.addActionListener(this);
		menRegistrar.add(menuItemAten);
		menuItemPac = new JMenuItem("Registrar Paciente");
		menuItemPac.setBackground(new Color(255, 255, 255));
		menuItemPac.addActionListener(this);
		menRegistrar.add(menuItemPac);

		menActualizaciones = new JMenu("MANTENIMIENTO");
		menuBar.add(menActualizaciones);

		menuItemImpPac = new JMenuItem("Importar Paciente");
		menuItemImpPac.setBackground(new Color(255, 255, 255));
		menuItemImpPac.addActionListener(this);
		menActualizaciones.add(menuItemImpPac);
		menuItemLugGeo = new JMenuItem("Lugar Geografico");
		menuItemLugGeo.setBackground(new Color(255, 255, 255));
		menuItemLugGeo.addActionListener(this);
		menActualizaciones.add(menuItemLugGeo);

		menReportes = new JMenu("REPORTES");
		menuBar.add(menReportes);

		menuItemReportes = new JMenuItem("Criterios de Reportes");
		menuItemReportes.setBackground(new Color(255, 255, 255));
		menuItemReportes.addActionListener(this);
		menReportes.add(menuItemReportes);

		mnSalir = new JMenu("SALIR");
		menuBar.add(mnSalir);
		menuItemSalir = new JMenuItem("Salir");
		menuItemSalir.setBackground(new Color(255, 255, 255));
		menuItemSalir.addActionListener(this);
		mnSalir.add(menuItemSalir);


	}

	public void actionPerformed(ActionEvent evento) {

		if(evento.getSource()==menuItemSigVit) {
			RegistrarSignosVitalesGUI registrarSignos = new RegistrarSignosVitalesGUI();
			registrarSignos.setBounds(0,0,525,500);
			registrarSignos.setVisible(true);
			registrarSignos.setResizable(false);
			getContentPane().add(registrarSignos);
			ventanaMenu.setBounds(0,0,525,510);
			ventanaMenu.setLocationRelativeTo(null);
			ventanaMenu.setResizable(false);
		}

		if(evento.getSource()==menuItemAten) {
			RegistrarAtencionGUI registrarAtencion=new RegistrarAtencionGUI();
			registrarAtencion.setBounds(0,0,1173,710);
			registrarAtencion.setVisible(true);
			registrarAtencion.setResizable(false); 			
			getContentPane().add(registrarAtencion);
			ventanaMenu.setBounds(0,0,1173,720);
			ventanaMenu.setLocationRelativeTo(null);
			ventanaMenu.setResizable(false);

		}

		if(evento.getSource()==menuItemPac) {
			RegistrarPacienteGUI registrarPaciente= new RegistrarPacienteGUI();
			registrarPaciente.setBounds(0,0,1325,690);
			registrarPaciente.setVisible(true);
			registrarPaciente.setResizable(false);
			getContentPane().add(registrarPaciente);
			ventanaMenu.setBounds(0,0,1325,715);
			ventanaMenu.setLocationRelativeTo(null);
			ventanaMenu.setResizable(false);
		}

		if(evento.getSource()==menuItemLugGeo) {
			RegistrarLugarGeoGUI lugGeo=new RegistrarLugarGeoGUI();
			lugGeo.setBounds(0,0,500,500);
			lugGeo.setVisible(true);
			lugGeo.setResizable(false); 
			getContentPane().add(lugGeo);
			ventanaMenu.setBounds(0,0,500,515);
			ventanaMenu.setLocationRelativeTo(null);
			ventanaMenu.setResizable(false);
		}

		if(evento.getSource()==menuItemImpPac) {
			ImportarPacientesGUI importarPaciente = new ImportarPacientesGUI();
			importarPaciente.setBounds(0,0,1000,700);
			importarPaciente.setVisible(true);
			importarPaciente.setResizable(false);
			getContentPane().add(importarPaciente);
			ventanaMenu.setBounds(0,0,1000,715);
			ventanaMenu.setLocationRelativeTo(null);
			ventanaMenu.setResizable(false);
		}

		if(evento.getSource()==menuItemReportes) {
			ReportesGUI reporte = new ReportesGUI();
			reporte.setBounds(0, 0, 460, 400);
			reporte.setVisible(true);
			reporte.setResizable(false);
			getContentPane().add(reporte);
			ventanaMenu.setBounds(0, 0, 460, 300);
			ventanaMenu.setLocationRelativeTo(null);
			ventanaMenu.setResizable(false);
		}
		if(evento.getSource()==menuItemSalir) {
			System.exit(0);
		}
	}


	public static void main(String[] args) {


		ventanaMenu.setVisible(true);
		ventanaMenu.setBounds(0, 0, 850, 500);
		ventanaMenu.setLocationRelativeTo(null);
		ventanaMenu.setResizable(false);


	}
}
