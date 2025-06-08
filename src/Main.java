import java.text.ParseException;
import java.util.List;
import java.util.Random;

import javax.swing.text.MaskFormatter;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import adapters.database.csv.DisciplinaCSVRepositoryAdapter;
import adapters.database.csv.InscricaoCSVRepositoryAdapter;
import domain.entities.Curso;
import domain.entities.Disciplina;
import domain.entities.Inscricao;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	

	public static void main(String[] args) {
//		cursoTeste();
//		try {
//			inscricaoTeste();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		DisciplinaTeste();
	}
	
	private static void DisciplinaTeste() {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		DisciplinaCSVRepositoryAdapter repositoryD = new DisciplinaCSVRepositoryAdapter("Disicplina.csv");
		Disciplina disciplina;
		
		
	}



	private static void inscricaoTeste() throws Exception {
		
		
		Random rnd = new Random();
		StringBuilder resultado = new StringBuilder();
		InscricaoCSVRepositoryAdapter repository = new InscricaoCSVRepositoryAdapter("inscricao.csv");
		Inscricao inscricao;
		
		//CREATE
		MaskFormatter mask = new MaskFormatter("###.###.###-##");
        mask.setValueContainsLiteralCharacters(false); // permite passar só os números
		
		for(int i = 0; i < 10; i++) {
			 for (int j = 0; j < 11; j++) {
		            int digito = rnd.nextInt(10); // Gera número de 0 a 9
		            resultado.append(digito);
		     }

		    String cpf = resultado.toString();
			inscricao = new Inscricao(i, i+100, mask.valueToString(cpf), "pendente");
			repository.save(inscricao);
		}
		
		//LIST
		List<Inscricao> inscricoes = repository.list();
		for (Inscricao inscricaoItem : inscricoes) {
			System.out.println(inscricaoItem.getCodigo() + " - " + inscricaoItem.getCpfProfessor());
		}
		System.out.println("----------------Fim list");
		
		//READ
		inscricao = repository.show(5);
		if(inscricao != null) {
			System.out.println(inscricao.getCpfProfessor());
		}
		System.out.println("----------------Fim Read");
		
		//UPDATE
		inscricao = repository.show(5);
		inscricao.setCpf_professor("123.123.123-00");
		repository.update(inscricao);
		
		//DELETE
		inscricao = repository.show(9);
		repository.delete(inscricao);
		
		
		//LIST
		inscricoes = repository.list();
		for (Inscricao inscricaoItem : inscricoes) {
			System.out.println(inscricaoItem.getCpfProfessor());
		}
		System.out.println("----------------Fim list");
		
	}



	private static void cursoTeste() {

		CursoCSVRepositoryAdapter repository = new CursoCSVRepositoryAdapter("cursos.csv");

		// CREATE
		Curso curso = new Curso(1, "ADS", "T.I");
		repository.save(curso);

		curso = new Curso(2, "Teste", "T.I");
		repository.save(curso);
		System.out.println("--------------FIM CREATE");

		// LIST
		List<Curso> cursos = repository.list();
		for (Curso cursoItem : cursos) {
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
		for (Curso cursoItem : cursos) {
			System.out.println(cursoItem.getNome());
		}
	}
	

}
