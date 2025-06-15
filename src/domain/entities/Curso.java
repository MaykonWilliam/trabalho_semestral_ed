package domain.entities;

import domain.interfaces.IEntity;
import domain.interfaces.IHasMany;
import utils.List;

public class Curso implements IEntity, IHasMany<Disciplina> {

	private int codigo;
	private String nome;
	private String area;
	private List<Disciplina> disciplinas;

	public Curso(int codigo, String nome, String area) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setArea(area);
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

	@Override
	public Object getPrimaryKey() {
		return this.codigo;
	}

	@Override
	public void setRelatedEntities(List<Disciplina> listEntities) {
		this.disciplinas = listEntities;
	}

	@Override
	public List<Disciplina> getRelatedEntities() {
		return this.disciplinas;
	}

	@Override
	public Class<Disciplina> getRelatedEntityClass() {
		return Disciplina.class;
	}

	@Override
	public String getForeignKeyFieldName() {
		return "codigoCurso";
	}
}
