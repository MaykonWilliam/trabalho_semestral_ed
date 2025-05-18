package adapters.database.csv;

import adapters.mappers.CursoMapper;
import domain.entities.Curso;
import domain.repositories.ICursoRepository;

public class CursoCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Curso> implements ICursoRepository {

	public CursoCSVRepositoryAdapter(String filePath) {
		super(filePath, CursoMapper::toString, CursoMapper::toEntity);
	}

}
