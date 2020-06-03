package clasesBean;

public class CIE {

	private int idCie=0;
	private String codigoEnfermedad="";
	private String descripcionEnfermedad="";
	private int idCIEpadre=0;
	
	
	public int getIdCIEpadre() {
		return idCIEpadre;
	}
	public void setIdCIEpadre(int idCIEpadre) {
		this.idCIEpadre = idCIEpadre;
	}
	public int getIdCie() {
		return idCie;
	}
	public void setIdCie(int idCie) {
		this.idCie = idCie;
	}
	public String getCodigoEnfermedad() {
		return codigoEnfermedad;
	}
	public void setCodigoEnfermedad(String codigoEnfermedad) {
		this.codigoEnfermedad = codigoEnfermedad;
	}
	public String getDescripcionEnfermedad() {
		return descripcionEnfermedad;
	}
	public void setDescripcionEnfermedad(String descripcionEnfermedad) {
		this.descripcionEnfermedad = descripcionEnfermedad;
	}
	
}
