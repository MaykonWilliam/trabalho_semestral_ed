package controllers;

import adapters.database.csv.InscricaoCSVRepositoryAdapter;
import domain.constants.CSVFiles;
import domain.entities.Inscricao;
import utils.List;

public class InscricaoController {

	private InscricaoCSVRepositoryAdapter repository = new InscricaoCSVRepositoryAdapter(CSVFiles.INSCRICAO);

	public InscricaoController() {
		super();
	}

	public List<Inscricao> getAll() {
		try {
			List<Inscricao> list = repository.list();
			return list;
		} catch (Exception e) {
			return new List<Inscricao>();
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

	public Inscricao show(int codigo) throws Exception {
		return repository.show(codigo);
	}

	public Inscricao save(String codigo, String codigo_disciplina, String cpf_professor, String status) throws Exception {
		Inscricao record = new Inscricao(codigo, codigo_disciplina, cpf_professor, status);
		repository.save(record);
		return record;
	}

	public void update(Inscricao record) throws Exception {
		repository.update(record);
	}

	public void delete(Inscricao record) throws Exception {
		repository.delete(record);
	}
}
