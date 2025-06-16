package domain.entities;

import domain.interfaces.IEntity;

public class Professor implements IEntity {
	private String cpf;
	private String nome;
	private String areaConhecimento;
	private int pontuacao;

	public Professor(String cpf, String nome, String areaConhecimento, int pontuacao) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.areaConhecimento = areaConhecimento;
		this.pontuacao = pontuacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nome = nomeProfessor;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public Object getPrimaryKey() {
		return cpf;
	}
}