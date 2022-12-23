package model;

public class CiudadesModel {
	private String nombre;
	private int habitantes;
	private String pais;
	public CiudadesModel(String nombre, int habitantes, String pais) {
		super();
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.pais = pais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHabitantes() {
		return habitantes;
	}
	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
}
