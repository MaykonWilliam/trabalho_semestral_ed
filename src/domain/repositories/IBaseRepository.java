package domain.repositories;

import java.util.List;

public interface IBaseRepository<T> {
	
	public void save(T entity);

	public void update(T entity);

	public void delete(T entity);

	public List<T> list();
}
