package clasesBean;

import clasesBean.personaBean;

public class empleadoBean extends personaBean {

	private int idEmpleado=0;
	private String horarioEmp="";
	private String tipoEmp="";
	
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getHorarioEmp() {
		return horarioEmp;
	}
	public void setHorarioEmp(String horarioEmp) {
		this.horarioEmp = horarioEmp;
	}
	public String getTipoEmp() {
		return tipoEmp;
	}
	public void setTipoEmp(String tipoEmp) {
		this.tipoEmp = tipoEmp;
	}
}
