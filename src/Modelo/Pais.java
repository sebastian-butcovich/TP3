package Modelo;

public class Pais {
	private String nombre;
	private String idioma;
	private int idPais;
	public Pais()
	{
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public int getIdPais()
	{
		return this.idPais;
	}
	public void setIdePais(int id)
	{
		this.idPais = id;
	}

}
