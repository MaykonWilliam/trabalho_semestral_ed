package controllers;

import domain.entities.Disciplina;
import domain.entities.Inscricao;
import domain.entities.Professor;

import adapters.database.csv.DisciplinaCSVRepositoryAdapter;
import adapters.database.csv.InscricaoCSVRepositoryAdapter;
import adapters.database.csv.ProfessorCSVRepositoryAdapter;
import domain.constants.CSVFiles;
import utils.HashTable;
import utils.List;

public class ConsultaDisciplinasProcessosController {
	
	private DisciplinaCSVRepositoryAdapter disciplinaRepository;
	private InscricaoCSVRepositoryAdapter inscricaoRepository;
	private ProfessorCSVRepositoryAdapter professorRepository;

	public ConsultaDisciplinasProcessosController() {
		
		this.disciplinaRepository = new DisciplinaCSVRepositoryAdapter(CSVFiles.DISCIPLINA);
		this.inscricaoRepository = new InscricaoCSVRepositoryAdapter(CSVFiles.INSCRICAO);
		this.professorRepository = new ProfessorCSVRepositoryAdapter(CSVFiles.PROFESSOR);
	}

	public List<Inscricao> getAll() {
		try {

			HashTable<Disciplina> disciplinasComProcessos = getDisciplinasComProcessosAbertos();

			List<Inscricao> inscricoesProcessosAbertos = convertHashTableToInscricoesList(disciplinasComProcessos);

			return inscricoesProcessosAbertos;
		} catch (Exception e) {
			System.err.println("Erro ao buscar disciplinas com processos abertos: " + e.getMessage());
			return new List<Inscricao>();
		}
	}

	private HashTable<Disciplina> getDisciplinasComProcessosAbertos() throws Exception {
		HashTable<Disciplina> disciplinasComProcessosHash = new HashTable<>();

		List<Inscricao> inscricoesAbertas = getInscricoesProcessosAbertos();
		List<Disciplina> todasDisciplinas = disciplinaRepository.list();

		int sizeInscricoes = inscricoesAbertas.size();
		for (int i = 0; i < sizeInscricoes; i++) {
			Inscricao inscricao = inscricoesAbertas.get(i);
			String codigoDisciplina = inscricao.getCodigo_disciplina();

			Disciplina disciplina = findDisciplinaByCodigo(todasDisciplinas, codigoDisciplina);
			if (disciplina != null) {

				if (!disciplinasComProcessosHash.contains(disciplina)) {
					disciplinasComProcessosHash.addEntity(disciplina);
				}
			}
		}

		return disciplinasComProcessosHash;
	}

	private List<Inscricao> convertHashTableToInscricoesList(HashTable<Disciplina> hashTable) throws Exception {
		List<Inscricao> resultado = new List<>();
		List<Inscricao> todasInscricoes = inscricaoRepository.list();

		List<Disciplina> disciplinasComProcessos = hashTable.getAllEntities();

		int sizeDisciplinas = disciplinasComProcessos.size();
		for (int i = 0; i < sizeDisciplinas; i++) {
			Disciplina disciplina = disciplinasComProcessos.get(i);

			List<Inscricao> inscricoesDaDisciplina = getInscricoesByDisciplina(todasInscricoes, disciplina.getCodigo());

			int sizeInscricoes = inscricoesDaDisciplina.size();
			for (int j = 0; j < sizeInscricoes; j++) {
				Inscricao inscricao = inscricoesDaDisciplina.get(j);

				inscricao = carregarDadosCompletos(inscricao);
				resultado.addLast(inscricao);
			}
		}

		return resultado;
	}

	private List<Inscricao> getInscricoesProcessosAbertos() throws Exception {
		List<Inscricao> todasInscricoes = inscricaoRepository.list();
		List<Inscricao> inscricoesAbertas = new List<>();

		int size = todasInscricoes.size();
		for (int i = 0; i < size; i++) {
			Inscricao inscricao = todasInscricoes.get(i);

			if (inscricao.getStatus() != null && !inscricao.getStatus().equalsIgnoreCase(Inscricao.INATIVO) && !inscricao.getStatus().equalsIgnoreCase("CONCLUIDO")) {
				inscricoesAbertas.addLast(inscricao);
			}
		}

		return inscricoesAbertas;
	}

	private List<Inscricao> getInscricoesByDisciplina(List<Inscricao> todasInscricoes, String codigoDisciplina) throws Exception {
		List<Inscricao> inscricoesDaDisciplina = new List<>();

		int size = todasInscricoes.size();
		for (int i = 0; i < size; i++) {
			Inscricao inscricao = todasInscricoes.get(i);

			if (inscricao.getCodigo_disciplina().equals(codigoDisciplina) && inscricao.getStatus() != null && !inscricao.getStatus().equalsIgnoreCase(Inscricao.INATIVO)
					&& !inscricao.getStatus().equalsIgnoreCase("CONCLUIDO")) {
				inscricoesDaDisciplina.addLast(inscricao);
			}
		}

		return inscricoesDaDisciplina;
	}

	private Inscricao carregarDadosCompletos(Inscricao inscricao) throws Exception {

		List<Disciplina> disciplinas = disciplinaRepository.list();
		Disciplina disciplina = findDisciplinaByCodigo(disciplinas, inscricao.getCodigo_disciplina());
		inscricao.setDisciplina(disciplina);

		List<Professor> professores = professorRepository.list();
		Professor professor = findProfessorByCpf(professores, inscricao.getCpf_professor());
		inscricao.setProfessor(professor);

		return inscricao;
	}

	private Disciplina findDisciplinaByCodigo(List<Disciplina> disciplinas, String codigo) throws Exception {
		int size = disciplinas.size();
		for (int i = 0; i < size; i++) {
			Disciplina disciplina = disciplinas.get(i);
			if (disciplina.getCodigo().equals(codigo)) {
				return disciplina;
			}
		}
		return null;
	}

	private Professor findProfessorByCpf(List<Professor> professores, String cpf) throws Exception {
		int size = professores.size();
		for (int i = 0; i < size; i++) {
			Professor professor = professores.get(i);
			if (professor.getCpf().equals(cpf)) {
				return professor;
			}
		}
		return null;
	}

}
