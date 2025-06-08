package adapters.database.csv;

import adapters.mappers.InscricaoMapper;
import domain.entities.Inscricao;
import domain.repositories.IInscricaoRepository;

public class InscricaoCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Inscricao> implements IInscricaoRepository{

	public InscricaoCSVRepositoryAdapter(String filePath) {
		super(filePath, InscricaoMapper::toString, InscricaoMapper::toEntity);
	}
}
