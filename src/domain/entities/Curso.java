package domain.entities;

public class Curso implements IEntity {

	private int codigo;
	private String nome;
	private String area;

	public Curso(int codigo, String nome, String area) {
		this.codigo = codigo;
		this.nome = nome;
		this.area = area;
	}

	@Override
	public Object getPrimaryKey() {
		return this.codigo;
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

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
