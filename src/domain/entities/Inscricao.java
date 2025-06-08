package domain.entities;


public class Inscricao implements IEntity{
	private int codigo;
	private int codigo_disciplina;
	private String cpf_professor;
	private String status;

	public Inscricao(int codigo, int codigo_disciplina, String cpf_professor, String status) {
		this.codigo = codigo;
		this.codigo_disciplina = codigo_disciplina;
		this.cpf_professor = cpf_professor;
		this.status = status;
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
	
	public String getStatus() {
		return this.status;
	}
	
	@Override
	public Object getPrimaryKey() {
		return this.codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setCodigo_disciplina(int codigo_disciplina) {
		this.codigo_disciplina = codigo_disciplina;
	}

	public void setCpf_professor(String cpf_professor) {
		this.cpf_professor = cpf_professor;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
