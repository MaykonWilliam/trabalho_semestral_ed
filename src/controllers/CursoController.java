package controllers;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import domain.entities.Curso;
import utils.List;

public class CursoController {

	private CursoCSVRepositoryAdapter repository = new CursoCSVRepositoryAdapter("cursos.csv");

	public CursoController() {
		super();
	}

	public List<Curso> getAll() {
		try {
			List<Curso> list = repository.list();
			return list;
		} catch (Exception e) {
			return new List<Curso>();
		}
	}

	public ValidationResponse validForm(String nome, String area) throws Exception {

		boolean isValid = true;
		List<String> listaErros = new List<String>();

		if (nome == null || nome.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("Nome inválido.");
			} else {
				listaErros.addLast("Nome inválido.");
			}
			isValid = false;
		}

		if (nome == null || area.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("Área inválido.");
			} else {
				listaErros.addLast("Área inválido.");
			}
			isValid = false;
		}

		StringBuffer messages = new StringBuffer();

		for (int i = 0; i < listaErros.size(); i++) {
			messages.append(listaErros.get(i));
			messages.append("\n");
		}

		ValidationResponse response = new ValidationResponse();
		response.isValid = isValid;

		if (isValid == false) {

			String errorMessage = "Corrija os erros abaixo para continuar:\n\n" + messages.toString();
			response.message = errorMessage;

		}

		return response;
	}

	public Curso show(int codigo) throws Exception {
		return repository.show(codigo);
	}

	public int getSequencePrimaryKey() throws Exception {
		return repository.getSequencePrimaryKey();
	}

	public Curso save(int codigo, String nome, String area) throws Exception {
		Curso curso = new Curso(codigo, nome, area);
		repository.save(curso);
		return curso;
	}

	public void update(Curso curso) throws Exception {
		repository.update(curso);
	}

	public void delete(Curso curso) throws Exception {
		repository.delete(curso);
	}
}
