package domain.entities;

import domain.interfaces.IEntity;

public class Inscricao  implements IEntity {
	private String codigo;
	private String codigo_disciplina;
	private String cpf_professor;
	private String status;

	public Inscricao(String codigo, String codigo_disciplina, String cpf_professor, String status) {
		this.codigo = codigo;
		this.codigo_disciplina = codigo_disciplina;
		this.cpf_professor = cpf_professor;
		this.status = status;
	}
	
	
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getCodigoDisciplina() {
		return this.codigo_disciplina;
	}
	
	public String getCpfProfessor() {
		return this.cpf_professor;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	@Override
	public Object getPrimaryKey() {
		return this.codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setCodigo_disciplina(String codigo_disciplina) {
		this.codigo_disciplina = codigo_disciplina;
	}

	public void setCpf_professor(String cpf_professor) {
		this.cpf_professor = cpf_professor;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
