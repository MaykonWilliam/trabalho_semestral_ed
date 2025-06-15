package domain.entities;

public class Professor implements IEntity {
	private String cpf;
	private String nomeProfessor;
	private String areaConhecimento;
	private int pontuacao;

	public Professor(String cpf, String nomeProfessor, String areaConhecimento, int pontuacao) {
		super();
		this.cpf = cpf;
		this.nomeProfessor = nomeProfessor;
		this.areaConhecimento = areaConhecimento;
		this.pontuacao = pontuacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
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