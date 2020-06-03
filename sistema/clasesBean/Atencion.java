package clasesBean;

public class Atencion {

	private int idAtencion=0;
	private int idPaciente=0;
	private int idEmpleado=0;
	private int numeroFacutraAte=0;
	private double valorCobradoAte=0;
	private String fechaAte="";
	private String diagnosticoDescAte="";
	private String prescripcionAte="";
	private String signosVitales="";
	
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public String getSignosVitales() {
		return signosVitales;
	}
	public void setSignosVitales(String signosVitales) {
		this.signosVitales = signosVitales;
	}
	public String getFechaAte() {
		return fechaAte;
	}
	public void setFechaAte(String fechaAte) {
		this.fechaAte = fechaAte;
	}
	public String getDiagnosticoDescAte() {
		return diagnosticoDescAte;
	}
	public void setDiagnosticoDescAte(String diagnosticoDescAte) {
		this.diagnosticoDescAte = diagnosticoDescAte;
	}
	public String getPrescripcionAte() {
		return prescripcionAte;
	}
	public void setPrescripcionAte(String prescripcionAte) {
		this.prescripcionAte = prescripcionAte;
	}
	public double getValorCobradoAte() {
		return valorCobradoAte;
	}
	public void setValorCobradoAte(double valorCobradoAte) {
		this.valorCobradoAte = valorCobradoAte;
	}
	public int getNumeroFacutraAte() {
		return numeroFacutraAte;
	}
	public void setNumeroFacutraAte(int numeroFacutraAte) {
		this.numeroFacutraAte = numeroFacutraAte;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}	
}
