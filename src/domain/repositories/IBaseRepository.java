package domain.repositories;

import br.edu.fateczl.Lista;

public interface IBaseRepository<T> {
	
	public void save(T entity) throws Exception;

	public void update(T entity) throws Exception;

	public void delete(T entity) throws Exception;

	public T show(Object entityPrimaryCode) throws Exception;
	
	public Lista<T> lista() throws Exception;
}
