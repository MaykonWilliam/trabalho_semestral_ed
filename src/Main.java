import java.util.List;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import domain.entities.Curso;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

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
