
import java.util.Random;
import views.MainView;
import javax.swing.text.MaskFormatter;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import adapters.database.csv.DisciplinaCSVRepositoryAdapter;
import adapters.database.csv.InscricaoCSVRepositoryAdapter;
import domain.entities.Curso;
import domain.entities.Disciplina;
import domain.entities.Inscricao;

import utils.List;

public class Main {

	public Main() {
		super();
	}

	public static void main(String[] args) throws Exception {
		new MainView().setVisible(true);
		// runAllTests();

	}

	private static void runAllTests() throws Exception {
		disciplinaTeste();
		inscricaoTeste();
		cursoTeste();
	}

	private static void disciplinaTeste() throws Exception {
		System.out.println("_______________________________________");
		System.out.println("_____________TESTE DISCIPLINA _________");
		System.out.println("_______________________________________");
		DisciplinaCSVRepositoryAdapter repository = new DisciplinaCSVRepositoryAdapter("disciplina.csv");
		Disciplina disciplina = new Disciplina("4002", "S0II", "terça-feira", "19:20", "4 horas");
		repository.save(disciplina);
		System.out.println("fim");

	}

	private static void inscricaoTeste() throws Exception {

		System.out.println("_______________________________________");
		System.out.println("_____________TESTE INSCRIÇÃO_ _________");
		System.out.println("_______________________________________");
		Random rnd = new Random();
		StringBuilder resultado = new StringBuilder();
		InscricaoCSVRepositoryAdapter repository = new InscricaoCSVRepositoryAdapter("inscricao.csv");
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
			inscricao = new Inscricao(i, i + 100, mask.valueToString(cpf), "pendente");
			repository.save(inscricao);
		}

		// LIST
		List<Inscricao> inscricoes = repository.list();
		for (int i = 0; i < inscricoes.size(); i++) {
			Inscricao inscricaoItem = inscricoes.get(i);
			System.out.println(inscricaoItem.getCodigo() + " - " + inscricaoItem.getCpfProfessor());
		}
		System.out.println("----------------Fim list");

		// READ
		inscricao = repository.show(5);
		if (inscricao != null) {
			System.out.println(inscricao.getCpfProfessor());
		}
		System.out.println("----------------Fim Read");

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
			System.out.println(inscricaoItem.getCpfProfessor());
		}
		System.out.println("----------------Fim list");

	}

	private static void cursoTeste() throws Exception {

		System.out.println("_______________________________________");
		System.out.println("_____________TESTE CURSO_____ _________");
		System.out.println("_______________________________________");
		CursoCSVRepositoryAdapter repository = new CursoCSVRepositoryAdapter("cursos.csv");

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
	}

}
