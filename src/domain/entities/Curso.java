package domain.entities;

public class Curso {
	
	private int codigo;
	private String nome;
	private String area;

	public Curso(int codigo, String nome, String area) {
		this.codigo = codigo;
		this.nome = nome;
		this.area = area;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getArea() {
		return area;
	}

}

