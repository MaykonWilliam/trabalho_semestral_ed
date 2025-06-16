
import java.util.Random;

import views.LoginView;

import javax.swing.text.MaskFormatter;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import adapters.database.csv.DisciplinaCSVRepositoryAdapter;
import adapters.database.csv.InscricaoCSVRepositoryAdapter;
import domain.constants.CSVFiles;
import domain.entities.Curso;
import domain.entities.Disciplina;
import domain.entities.Inscricao;

import utils.List;

public class Main {

	public Main() {
		super();
	}

	public static void main(String[] args) throws Exception {
		 new LoginView().setVisible(true);
		// runAllTests();
		
	}

	@SuppressWarnings("unused")
	private static void runAllTests() throws Exception {
		cursoTeste();
		inscricaoTeste();
	}

	private static void disciplinaTeste() throws Exception {
		DisciplinaCSVRepositoryAdapter repository = new DisciplinaCSVRepositoryAdapter(CSVFiles.DISCIPLINA);
		Disciplina disciplina = new Disciplina("4002", "S0II", "terça-feira", "19:20", "4 horas", 1);
		repository.save(disciplina);

	}

	@SuppressWarnings("unused")
	private static void inscricaoTeste() throws Exception {

		Random rnd = new Random();
		StringBuilder resultado = new StringBuilder();
		InscricaoCSVRepositoryAdapter repository = new InscricaoCSVRepositoryAdapter(CSVFiles.INSCRICAO);
		Inscricao inscricao;

		// CREATE
		MaskFormatter mask = new MaskFormatter("###.###.###-##");
		mask.setValueContainsLiteralCharacters(false); // permite passar só os números

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 11; j++) {
				int digito = rnd.nextInt(10); // Gera número de 0 a 9
				resultado.append(digito);
			}

			String cpf = resultado.toString();
			inscricao = new Inscricao(Integer.toString(i), Integer.toString(i) + 100, mask.valueToString(cpf), "pendente");
			repository.save(inscricao);
		}

		// LIST
		List<Inscricao> inscricoes = repository.list();
		for (int i = 0; i < inscricoes.size(); i++) {
			Inscricao inscricaoItem = inscricoes.get(i);

		}

		// READ
		inscricao = repository.show(5);
		if (inscricao != null) {

		}

		// UPDATE
		inscricao = repository.show(5);
		inscricao.setCpf_professor("123.123.123-00");
		repository.update(inscricao);

		// DELETE
		inscricao = repository.show(9);
		repository.delete(inscricao);

		// LIST
		inscricoes = repository.list();
		for (int i = 0; i < inscricoes.size(); i++) {

			Inscricao inscricaoItem = inscricoes.get(i);

		}

	}

	private static void cursoTeste() throws Exception {

		System.out.println("_______________________________________");
		System.out.println("_____________TESTE CURSO_____ _________");
		System.out.println("_______________________________________");
		CursoCSVRepositoryAdapter repository = new CursoCSVRepositoryAdapter(CSVFiles.CURSO);

		// CREATE
		Curso curso = new Curso(1, "ADS", "T.I");
		repository.save(curso);

		curso = new Curso(2, "Teste", "T.I");
		repository.save(curso);
		System.out.println("--------------FIM CREATE");

		// LIST
		List<Curso> cursos = repository.list();
		for (int i = 0; i < cursos.size(); i++) {
			Curso cursoItem = cursos.get(i);
			System.out.println(cursoItem.getNome());
		}
		System.out.println("--------------FIM LIST");

		// READ
		curso = repository.show(1);
		if (curso != null) {
			System.out.println(curso.getNome());
		}
		System.out.println("--------------FIM READ");

		// Update
		curso = repository.show(1);
		System.out.println(curso.getNome());
		if (curso != null) {
			curso.setNome("ADS NOVO");
			repository.update(curso);

			Curso newCurso = repository.show(1);
			System.out.println(newCurso.getNome());
		}
		System.out.println("--------------FIM UPDATE");

		// Delete
		curso = repository.show(2);
		System.out.println(curso.getNome());
		if (curso != null) {
			repository.delete(curso);
		}
		System.out.println("--------------FIM DELETE");

		cursos = repository.list();

		for (int i = 0; i < cursos.size(); i++) {
			Curso cursoItem = cursos.get(i);
			System.out.println(cursoItem.getNome());
		}

		disciplinaTeste();

		curso = repository.show(1);
		if (curso != null) {
			List<Disciplina> disciplinasCurso = curso.getRelatedEntities();
			System.out.println("Relação Encontradas");
			for (int d = 0; d < disciplinasCurso.size(); d++) {
				Disciplina disciplinaCurso = disciplinasCurso.get(d);
				System.out.println("Relação Main" + disciplinaCurso.getNome());
			}
			repository.delete(curso);
		}
	}

}
