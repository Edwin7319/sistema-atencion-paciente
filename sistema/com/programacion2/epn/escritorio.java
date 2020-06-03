package com.programacion2.epn;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;


public class escritorio extends JDesktopPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JInternalFrame[] ventanas;

	
	public escritorio() {
		super();
		this.putClientProperty("JDesktopPane.dragMode", "outline");
	}
	public void adicionar(JInternalFrame comp) {
		add(comp, null, 0);
	}
}
