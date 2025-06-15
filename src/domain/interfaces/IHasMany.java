package domain.interfaces;

import utils.List;

public interface IHasMany<T> {
	public void setRelatedEntities(List<T> listEntities);

	public List<T> getRelatedEntities();

	Class<T> getRelatedEntityClass();

	String getForeignKeyFieldName();
}
