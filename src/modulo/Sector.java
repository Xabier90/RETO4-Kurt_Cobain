package modulo;

public class Sector {

	private int id_sec;
	private int id_centro;
	private String nombre;
	private String tipo;
	private int m2;
	private float temp_media;

	public Sector(int id_centro, String nombre, String tipo, int m2, float temp_media) {
		super();
		this.id_centro = id_centro;
		this.nombre = nombre;
		this.tipo = tipo;
		this.m2 = m2;
		this.temp_media = temp_media;
	}

	public int getId_sec() {
		return id_sec;
	}

	public void setId_sec(int id_sec) {
		this.id_sec = id_sec;
	}

	public int getId_centro() {
		return id_centro;
	}

	public void setId_centro(int id_centro) {
		this.id_centro = id_centro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getM2() {
		return m2;
	}

	public void setM2(int m2) {
		this.m2 = m2;
	}

	public float getTemp_media() {
		return temp_media;
	}

	public void setTemp_media(float temp_media) {
		this.temp_media = temp_media;
	}

}
