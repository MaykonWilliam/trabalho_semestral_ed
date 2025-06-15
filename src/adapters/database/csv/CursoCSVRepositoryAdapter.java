package adapters.database.csv;

import adapters.mappers.CursoMapper;
import br.edu.fateczl.Lista;
import domain.entities.Curso;
import domain.repositories.ICursoRepository;

public class CursoCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Curso> implements ICursoRepository {

	public CursoCSVRepositoryAdapter(String filePath) {
		super(filePath, CursoMapper::toString, CursoMapper::toEntity);
	}

	@Override
	public int getSequencePrimaryKey() throws Exception {
		Lista<Curso> lista = this.lista();
		int lastPrimaryKey = 0;
		
		for(int i = 0; i < lista.size(); i++) {
			Curso curso = lista.get(i);
			lastPrimaryKey = (int) curso.getPrimaryKey();
		}

		return lastPrimaryKey + 1;
	}

}
