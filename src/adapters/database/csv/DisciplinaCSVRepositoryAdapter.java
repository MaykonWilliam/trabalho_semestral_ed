package adapters.database.csv;

import adapters.mappers.DisciplinaMapper;
import domain.entities.Disciplina;
import domain.repositories.IDisciplinaRepository;

public class DisciplinaCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Disciplina> implements IDisciplinaRepository {

	public DisciplinaCSVRepositoryAdapter(String filePath) {
		super(filePath, DisciplinaMapper::toString, DisciplinaMapper::toEntity);
	}

	@Override
	protected BaseCSVRepositoryAdapter<?> getRepositoryFor(Class<?> entityClass) {
		return null;
	}

}