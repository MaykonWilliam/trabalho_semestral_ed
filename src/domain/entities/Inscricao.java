package domain.entities;

public class Inscricao implements IEntity {
	private int codigo;
	private int codigo_disciplina;
	private String cpf_professor;

	public Inscricao(int codigo, int codigo_disciplina, String cpf_professor) {
		this.codigo = codigo;
		this.codigo_disciplina = codigo_disciplina;
		this.cpf_professor = cpf_professor;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public int getCodigoDisciplina() {
		return this.codigo_disciplina;
	}
	
	public String getCpfProfessor() {
		return this.cpf_professor;
	}

	@Override
	public Object getPrimaryKey() {
		return this.codigo;
	}

}
