package adapters.database.csv;

import java.util.List;

import adapters.mappers.CursoMapper;
import domain.entities.Curso;
import domain.repositories.ICursoRepository;

public class CursoCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Curso> implements ICursoRepository {

	public CursoCSVRepositoryAdapter(String filePath) {
		super(filePath, CursoMapper::toString, CursoMapper::toEntity);
	}

	@Override
	public int getSequencePrimaryKey() {
		List<Curso> list = this.list();
		int lastPrimaryKey = 0;

		for (Curso curso : list) {
			lastPrimaryKey = (int) curso.getPrimaryKey();
		}

		return lastPrimaryKey + 1;
	}

}
