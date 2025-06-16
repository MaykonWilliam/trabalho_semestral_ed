package adapters.database.csv;

import adapters.mappers.InscricaoMapper;
import domain.entities.Inscricao;
import domain.entities.Professor;
import domain.constants.CSVFiles;
import domain.entities.Disciplina;
import domain.repositories.IInscricaoRepository;

public class InscricaoCSVRepositoryAdapter extends BaseCSVRepositoryAdapter<Inscricao> implements IInscricaoRepository {

    public InscricaoCSVRepositoryAdapter(String filePath) {
        super(filePath, InscricaoMapper::toString, InscricaoMapper::toEntity);
    }

    @Override
    protected BaseCSVRepositoryAdapter<?> getRepositoryFor(Class<?> entityClass) {
        if (entityClass == Professor.class) {
            return new ProfessorCSVRepositoryAdapter(CSVFiles.PROFESSOR);
        }
        if (entityClass == Disciplina.class) {
            return new DisciplinaCSVRepositoryAdapter(CSVFiles.DISCIPLINA);
        }
        return null;
    }
}
