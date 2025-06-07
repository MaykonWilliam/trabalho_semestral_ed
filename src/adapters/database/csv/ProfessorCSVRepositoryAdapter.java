package adapters.database.csv;

import adapters.mappers.ProfessorMapper;
import domain.entities.Professor;
import domain.repositories.IProfessorRepository;

public class ProfessorCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Professor> implements IProfessorRepository {

	public ProfessorCSVRepositoryAdapter(String filePath) {
		super(filePath, ProfessorMapper::toString, ProfessorMapper::toEntity);
	}

}
