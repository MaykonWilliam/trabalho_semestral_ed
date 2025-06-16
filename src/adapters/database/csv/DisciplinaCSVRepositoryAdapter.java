package adapters.database.csv;

import adapters.mappers.DisciplinaMapper;
import domain.constants.CSVFiles;
import domain.entities.Disciplina;
import domain.entities.Inscricao;
import domain.repositories.IDisciplinaRepository;

public class DisciplinaCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Disciplina> implements IDisciplinaRepository {
	private InscricaoCSVRepositoryAdapter inscricaoCSVRepository = new InscricaoCSVRepositoryAdapter(CSVFiles.INSCRICAO);

	public DisciplinaCSVRepositoryAdapter(String filePath) {
		super(filePath, DisciplinaMapper::toString, DisciplinaMapper::toEntity);
	}

	@Override
	protected BaseCSVRepositoryAdapter<?> getRepositoryFor(Class<?> entityClass) {
		if (entityClass == Inscricao.class) {
			return this.inscricaoCSVRepository;
		}
		return null;
	}

}