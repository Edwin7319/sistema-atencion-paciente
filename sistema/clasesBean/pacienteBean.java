package clasesBean;

import java.util.Date;

import clasesBean.personaBean;


public class pacienteBean extends personaBean {

	private int idPaciente=0;
	private String sexoPac="";
	private String direccionPac="";
	private Date fechaNacPac=null;
	private String hclPac="";
	private int idLugGeoNacePac=0;
	private int idLugGeoResPac=0;
	private String lugarNac="";
	private String lugarRes="";
	private String edad="";
	

	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getLugarRes() {
		return lugarRes;
	}
	public void setLugarRes(String lugarRes) {
		this.lugarRes = lugarRes;
	}
	public String getLugarNac() {
		return lugarNac;
	}
	public void setLugarNac(String lugarNac) {
		this.lugarNac = lugarNac;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getSexoPac() {
		return sexoPac;
	}
	public void setSexoPac(String sexoPac) {
		this.sexoPac = sexoPac;
	}
	public String getDireccionPac() {
		return direccionPac;
	}
	public void setDireccionPac(String direccionPac) {
		this.direccionPac = direccionPac;
	}
	public Date getFechaNacPac() {
		return fechaNacPac;
	}
	public void setFechaNacPac(Date fechaNacPac) {
		this.fechaNacPac = fechaNacPac;
	}
	public String getHclPac() {
		return hclPac;
	}
	public void setHclPac(String hclPac) {
		this.hclPac = hclPac;
	}
	public int getIdLugGeoNacePac() {
		return idLugGeoNacePac;
	}
	public void setIdLugGeoNacePac(int idLugGeoNacePac) {
		this.idLugGeoNacePac = idLugGeoNacePac;
	}
	public int getIdLugGeoResPac() {
		return idLugGeoResPac;
	}
	public void setIdLugGeoResPac(int idLugGeoResPac) {
		this.idLugGeoResPac = idLugGeoResPac;
	}
	

}
