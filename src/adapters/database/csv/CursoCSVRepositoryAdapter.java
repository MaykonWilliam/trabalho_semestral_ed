package adapters.database.csv;

import adapters.mappers.CursoMapper;
import domain.constants.CSVFiles;
import domain.entities.Curso;
import domain.entities.Disciplina;
import domain.repositories.ICursoRepository;

import utils.List;

public class CursoCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Curso> implements ICursoRepository {

	private DisciplinaCSVRepositoryAdapter disciplinaCSVRepository = new DisciplinaCSVRepositoryAdapter(CSVFiles.DISCIPLINA);

	public CursoCSVRepositoryAdapter(String filePath) {
		super(filePath, CursoMapper::toString, CursoMapper::toEntity);

	}

	@Override
	public int getSequencePrimaryKey() throws Exception {
		List<Curso> lista = this.list();
		int lastPrimaryKey = 0;

		for (int i = 0; i < lista.size(); i++) {
			Curso curso = lista.get(i);
			lastPrimaryKey = (int) curso.getPrimaryKey();
		}

		return lastPrimaryKey + 1;
	}

	@Override
	protected BaseCSVRepositoryAdapter<?> getRepositoryFor(Class<?> entityClass) {
		if (entityClass == Disciplina.class) {
			return this.disciplinaCSVRepository;
		}
		return null;
	}

}
