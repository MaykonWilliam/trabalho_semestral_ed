package controllers;

import adapters.database.csv.InscricaoCSVRepositoryAdapter;
import domain.constants.CSVFiles;
import domain.entities.Inscricao;
import utils.List;
import utils.QuickSort;

public class ConsultaInscricaoController {

	private InscricaoCSVRepositoryAdapter repository = new InscricaoCSVRepositoryAdapter(CSVFiles.INSCRICAO);

	public ConsultaInscricaoController() {
		super();
	}

	public List<Inscricao> getAll() {
		try {
			List<Inscricao> list = repository.list();

			if (list != null && !list.isEmpty()) {
				list = sortByScore(list);
			}
			return list;
		} catch (Exception e) {
			return new List<Inscricao>();
		}
	}

	private List<Inscricao> sortByScore(List<Inscricao> list) {
		try {
			QuickSort.sort(list);
			return list;
		} catch (Exception e) {
			return list;
		}
	}
}
