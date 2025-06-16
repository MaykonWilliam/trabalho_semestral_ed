package controllers;

import adapters.database.csv.ProfessorCSVRepositoryAdapter;
import domain.entities.Professor;
import utils.List;

public class ProfessorController {

	private ProfessorCSVRepositoryAdapter repository = new ProfessorCSVRepositoryAdapter("professor.csv");

	public ProfessorController() {
		super();
	}

	public List<Professor> getAll() {
		try {
			List<Professor> list = repository.list();
			return list;
		} catch (Exception e) {
			return new List<Professor>();
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

	public Professor show(int codigo) throws Exception {
		return repository.show(codigo);
	}

	public Professor save(String cpf, String nomeProfessor, String areaConhecimento, int pontuacao) throws Exception {
		Professor record = new Professor(cpf, nomeProfessor, areaConhecimento, pontuacao);
		repository.save(record);
		return record;
	}

	public void update(Professor record) throws Exception {
		repository.update(record);
	}

	public void delete(Professor record) throws Exception {
		repository.delete(record);
	}
}
