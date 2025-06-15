package domain.repositories;

import utils.List;
import utils.List;

public interface IBaseRepository<T> {


	public void save(T entity) throws Exception;

	public void update(T entity) throws Exception;

	public void delete(T entity) throws Exception;

	public T show(Object entityPrimaryCode) throws Exception;
	public List<T> list() throws Exception;

}
