package domain.entities;

import domain.interfaces.IEntity;
import domain.interfaces.IHasMany;
import utils.List;

public class Disciplina implements IEntity, IHasMany<Inscricao> {
	private String codigo;
	private String nome;
	private String diaSemana;
	private String horarioInicial;
	private String horasDiarias;
	private Object codigoCurso;
	private List<Inscricao> inscricoes;

	public Disciplina(String codigo, String nome, String diaSemana, String horarioInicial, String horasDiarias, Object codigoCurso) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setDiaSemana(diaSemana);
		this.setHorarioInicial(horarioInicial);
		this.setHorasDiarias(horasDiarias);
		this.setCodigoCurso(codigoCurso);
	}

	@Override
	public Object getPrimaryKey() {
		return codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public String getHorasDiarias() {
		return horasDiarias;
	}

	public void setHorasDiarias(String horasDiarias) {
		this.horasDiarias = horasDiarias;
	}

	public Object getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(Object codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	@Override
	public void setRelatedEntities(List<Inscricao> listEntities) {
		this.inscricoes = listEntities;

	}

	@Override
	public List<Inscricao> getRelatedEntities() {
		return this.inscricoes;
	}

	@Override
	public Class<Inscricao> getRelatedEntityClass() {
		return Inscricao.class;
	}

	@Override
	public String getForeignKeyFieldName() {
		return "codigo_disciplina";
	}
}