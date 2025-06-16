package adapters.database.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Function;

import domain.interfaces.IEntity;
import domain.interfaces.IHasMany;
import domain.interfaces.IHasOne;
import domain.relationships.OneToOneRelationship;
import domain.repositories.IBaseRepository;
import utils.List;

public abstract class BaseCSVRepositoryAdapter<T> implements IBaseRepository<T> {

	protected final String filePath;
	protected final Function<T, String> toString;
	protected final Function<String, T> toEntity;

	protected abstract BaseCSVRepositoryAdapter<?> getRepositoryFor(Class<?> entityClass);

	public BaseCSVRepositoryAdapter(String filePath, Function<T, String> toString, Function<String, T> fromString) {
		this.filePath = filePath;
		this.toString = toString;
		this.toEntity = fromString;
	}

	protected void createFileIfNotExists(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.err.println("createFileIfNotExists:" + e.getMessage());
		}
	}

	protected void deleteFileIfExists(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	protected void saveAll(List<T> list) throws Exception {

		try {

			this.deleteFileIfExists(filePath);
			this.createFileIfNotExists(this.filePath);

			FileWriter fileWriter = new FileWriter(this.filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < list.size(); i++) {
				T entity = list.get(i);
				bufferedWriter.write(toString.apply(entity));
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}

			bufferedWriter.close();

		} catch (IOException e) {
			System.err.println("saveAll: " + e.getMessage());
		}

	}

	@Override
	public void save(T entity) throws Exception {
		List<T> entities = list();
		entities.addLast(entity);
		this.saveAll(entities);
	}

	@Override
	public void update(T entity) throws Exception {
		List<T> list = this.list();
		Object primaryKey = ((IEntity) entity).getPrimaryKey();

		for (int i = 0; i < list.size(); i++) {
			T entityList = list.get(i);

			if (((IEntity) entityList).getPrimaryKey().equals(primaryKey)) {

				list.remove(i);
				list.add(entity, i);
				break;

			}
		}

		this.saveAll(list);
	}

	@Override
	public void delete(T entity) throws Exception {
		List<T> list = this.list();

		Object entityCode = ((IEntity) entity).getPrimaryKey();

		for (int i = 0; i < list.size(); i++) {
			T entityList = list.get(i);

			if (((IEntity) entityList).getPrimaryKey().equals(entityCode)) {

				if (entityList instanceof IHasMany) {

					if (entityList instanceof IHasMany) {
						deleteRelatedEntities(entityList);
					}

				}
				list.remove(i);
				this.saveAll(list);
				break;

			}
		}
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T show(Object entityCode) throws Exception {
		List<T> list = this.list();

		for (int i = 0; i < list.size(); i++) {
			T entity = list.get(i);
			if (((IEntity) entity).getPrimaryKey().equals(entityCode)) {
				if (entity instanceof IHasMany) {
					List<T> relatedEntities = loadRelationships(entity);
					((IHasMany) entity).setRelatedEntities(relatedEntities);
				}
				if (entity instanceof IHasOne) {
					loadOneToOneRelationships(entity);
				}
				return entity;
			}
		}
		return null;
	}

	private T showWithOutRelashionship(Object entityCode) throws Exception {
		List<T> list = this.list();

		for (int i = 0; i < list.size(); i++) {
			T entity = list.get(i);
			if (((IEntity) entity).getPrimaryKey().equals(entityCode)) {

				return entity;
			}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> list() throws Exception {

		this.createFileIfNotExists(this.filePath);

		List<T> list = new List<T>();

		try {
			FileReader fileReader = new FileReader(this.filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			while (line != null) {

				T entity = this.toEntity.apply(line);

				if (entity instanceof IHasMany) {
					List<T> relatedEntities = loadRelationships(entity);
					((IHasMany) entity).setRelatedEntities(relatedEntities);
				}
				if (entity instanceof IHasOne) {
					loadOneToOneRelationships(entity);
				}

				if (list.isEmpty()) {
					list.addFirst(entity);
				} else {
					list.addLast(entity);
				}

				line = bufferedReader.readLine();
			}

			bufferedReader.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return list;
	}

	private List<T> listWithOutRelashionship() throws Exception {

		this.createFileIfNotExists(this.filePath);

		List<T> list = new List<T>();

		try {
			FileReader fileReader = new FileReader(this.filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			while (line != null) {

				T entity = this.toEntity.apply(line);

				if (list.isEmpty()) {
					list.addFirst(entity);
				} else {
					list.addLast(entity);
				}

				line = bufferedReader.readLine();
			}

			bufferedReader.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return list;
	}

	protected List<T> loadRelationships(T entity) {
		if (!(entity instanceof IHasMany)) {
			return new List<>();
		}

		IHasMany<?> hasMany = (IHasMany<?>) entity;
		Class<?> relatedClass = hasMany.getRelatedEntityClass();
		String foreignKeyField = hasMany.getForeignKeyFieldName();
		Object primaryKey = ((IEntity) entity).getPrimaryKey();

		try {
			BaseCSVRepositoryAdapter<?> relatedRepository = getRepositoryFor(relatedClass);

			if (relatedRepository != null) {
				List<?> allRelatedEntities = relatedRepository.listWithOutRelashionship();
				List<T> filteredEntities = new List<T>();

				for (int i = 0; i < allRelatedEntities.size(); i++) {
					Object relatedEntity = allRelatedEntities.get(i);
					Object foreignKeyValue = getFieldValue(relatedEntity, foreignKeyField);

					if (primaryKey.equals(foreignKeyValue)) {
						@SuppressWarnings("unchecked")
						T castEntity = (T) relatedEntity;
						filteredEntities.addLast(castEntity);
					}
				}

				return filteredEntities;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return new List<>();
	}

	private Object getFieldValue(Object entity, String fieldName) {
		try {

			String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method getter = entity.getClass().getMethod(getterName);
			return getter.invoke(entity);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	private void deleteRelatedEntities(T entity) throws Exception {
		if (!(entity instanceof IHasMany)) {
			return;
		}

		IHasMany<?> hasMany = (IHasMany<?>) entity;
		Class<?> relatedClass = hasMany.getRelatedEntityClass();
		String foreignKeyField = hasMany.getForeignKeyFieldName();
		Object primaryKey = ((IEntity) entity).getPrimaryKey();

		try {

			BaseCSVRepositoryAdapter<?> relatedRepository = getRepositoryFor(relatedClass);

			if (relatedRepository != null) {
				List<?> allRelatedEntities = relatedRepository.list();
				List<Object> entitiesToDelete = new List<>();

				for (int i = 0; i < allRelatedEntities.size(); i++) {
					Object relatedEntity = allRelatedEntities.get(i);
					Object foreignKeyValue = getFieldValue(relatedEntity, foreignKeyField);

					if (primaryKey.equals(foreignKeyValue)) {
						entitiesToDelete.addLast(relatedEntity);
					}
				}

				for (int i = 0; i < entitiesToDelete.size(); i++) {
					Object entityToDelete = entitiesToDelete.get(i);
					relatedRepository.deleteEntity(entityToDelete);
				}
			}

		} catch (Exception e) {
			System.err.println("Erro ao deletar entidades relacionadas: " + e.getMessage());

		}
	}

	@SuppressWarnings("unchecked")
	private void deleteEntity(Object entity) throws Exception {
		List<Object> list = (List<Object>) this.list();
		Object entityCode = ((IEntity) entity).getPrimaryKey();

		for (int i = 0; i < list.size(); i++) {
			Object entityList = list.get(i);

			if (((IEntity) entityList).getPrimaryKey().equals(entityCode)) {
				list.remove(i);
				this.saveAll((List<T>) list);
				break;
			}
		}
	}

	protected void loadOneToOneRelationships(T entity) throws Exception {

		if (entity instanceof IHasOne) {
			IHasOne entityWithRelationships = (IHasOne) entity;
			List<OneToOneRelationship<?>> relationships = entityWithRelationships.getOneToOneRelationships();

			for (int i = 0; i < relationships.size(); i++) {
				OneToOneRelationship<?> rel = relationships.get(i);

				loadRelationship(entity, rel);
			}
		} else {

		}
	}

	private <R> void loadRelationship(T entity, OneToOneRelationship<R> relationship) {
		try {
			BaseCSVRepositoryAdapter<?> relatedRepository = getRepositoryFor(relationship.getEntityClass());
			if (relatedRepository != null) {
				Object foreignKeyValue = getFieldValue(entity, relationship.getForeignKeyFieldName());

				Object relatedEntity = relatedRepository.showWithOutRelashionship(foreignKeyValue);

				if (relatedEntity != null && relationship.getEntityClass().isInstance(relatedEntity)) {
					@SuppressWarnings("unchecked")
					R typedEntity = (R) relatedEntity;
					relationship.setEntity(typedEntity);

				}
			} else {

			}
		} catch (Exception e) {
			System.err.println("Erro ao carregar relacionamento: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
