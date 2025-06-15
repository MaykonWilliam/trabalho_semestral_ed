package domain.repositories;

import domain.entities.Curso;

public interface ICursoRepository extends IBaseRepository<Curso> {
	public int getSequencePrimaryKey() throws Exception;
}
