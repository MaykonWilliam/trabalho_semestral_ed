package domain.entities;

import domain.interfaces.IEntity;
import domain.interfaces.IHasOne;
import domain.relationships.OneToOneRelationship;
import utils.List;

public class Inscricao implements IEntity, IHasOne {
	private String codigo;
	private String codigo_disciplina;
	private String cpf_professor;
	private String status;

 	public static final String ATIVO = "ATIVO";
 	public static final String INATIVO = "INATIVO";
	 
	private final OneToOneRelationship<Professor> professorRelationship;
	private final OneToOneRelationship<Disciplina> disciplinaRelationship;

	public Inscricao(String codigo, String codigo_disciplina, String cpf_professor, String status) {
		this.codigo = codigo;
		this.codigo_disciplina = codigo_disciplina;
		this.cpf_professor = cpf_professor;
		this.status = status;

		this.professorRelationship = new OneToOneRelationship<>(Professor.class, "cpf_professor");
		this.disciplinaRelationship = new OneToOneRelationship<>(Disciplina.class, "codigo_disciplina");
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getCpf_professor() {
		return this.cpf_professor;
	}

	public String getCodigo_disciplina() {
		return this.codigo_disciplina;
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

	public Professor getProfessor() {
		return professorRelationship.getEntity();
	}

	public void setProfessor(Professor professor) {
		professorRelationship.setEntity(professor);
	}

	public Disciplina getDisciplina() {
		return disciplinaRelationship.getEntity();
	}

	public void setDisciplina(Disciplina disciplina) {
		disciplinaRelationship.setEntity(disciplina);
	}

	public OneToOneRelationship<Professor> getProfessorRelationship() {
		return professorRelationship;
	}

	public OneToOneRelationship<Disciplina> getDisciplinaRelationship() {
		return disciplinaRelationship;
	}

	@Override
	public List<OneToOneRelationship<?>> getOneToOneRelationships() throws Exception {

		List<OneToOneRelationship<?>> relationships = new List<>();

		relationships.addLast(professorRelationship);
		relationships.addLast(disciplinaRelationship);
		return relationships;
	}

	@Override
	public int hashCode() {

		double value;
		if (this.getPrimaryKey() != null && !((String) this.getPrimaryKey()).isEmpty()) {
			value = (double) ((String) this.getPrimaryKey()).charAt(0);
		} else {
			value = 0.0;
		}

		double sqrt2 = Math.sqrt(2);
		double result = (5 * sqrt2 * value) % 1;
		return (int) (1000 * result);
	}
}
