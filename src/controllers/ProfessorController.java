package controllers;

import adapters.database.csv.ProfessorCSVRepositoryAdapter;
import domain.constants.CSVFiles;
import domain.entities.Professor;
import utils.List;

public class ProfessorController {

	private ProfessorCSVRepositoryAdapter repository = new ProfessorCSVRepositoryAdapter(CSVFiles.PROFESSOR);

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

	public ValidationResponse validForm(String cpf, String nome, String area, String pontuacao) throws Exception {

		boolean isValid = true;
		List<String> listaErros = new List<String>();

		if (cpf == null || cpf.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("CPF inválido.");
			} else {
				listaErros.addLast("CPF inválido.");
			}
			isValid = false;
		}

		if (nome == null || nome.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("Nome do Professor inválido.");
			} else {
				listaErros.addLast("Nome do Professor inválido.");
			}
			isValid = false;
		}

		if (area == null || area.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("Área de Conhecimento inválida.");
			} else {
				listaErros.addLast("Área de Conhecimento inválida.");
			}
			isValid = false;
		}

		if (pontuacao == null || pontuacao.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("Pontuação inválida.");
			} else {
				listaErros.addLast("Pontuação inválida.");
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

	public Professor show(String cpf) throws Exception {
		return repository.show(cpf);
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
