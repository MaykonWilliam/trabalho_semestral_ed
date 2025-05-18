package adapters.database.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import domain.entities.IEntity;
import domain.repositories.IBaseRepository;

public abstract class BaseCSVRepositoryAdapter<T> implements IBaseRepository<T> {

	protected final String filePath;
	protected final Function<T, String> toString;
	protected final Function<String, T> fromString;

	public BaseCSVRepositoryAdapter(String filePath, Function<T, String> toString, Function<String, T> fromString) {
		this.filePath = filePath;
		this.toString = toString;
		this.fromString = fromString;
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

	protected void saveAll(List<T> list) {

		try {

			this.createFileIfNotExists(this.filePath);

			FileWriter fileWriter = new FileWriter(this.filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (T entity : list) {

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
	public void save(T entity) {
		List<T> entities = list();
		entities.add(entity);
		this.saveAll(entities);
	}

	@Override
	public void update(T entity) {
		List<T> list = this.list();
		Object primaryKey = ((IEntity) entity).getPrimaryKey();

		for (int i = 0; i < list.size(); i++) {
			T entityList = list.get(i);

			if (((IEntity) entityList).getPrimaryKey().equals(primaryKey)) {

				list.set(i, entity);
				break;

			}
		}

		this.saveAll(list);
	}

	@Override
	public void delete(T entity) {
		List<T> list = this.list();

		Object entityCode = ((IEntity) entity).getPrimaryKey();
		
		for (int i = 0; i < list.size(); i++) {
			T entityList = list.get(i);

			if (((IEntity) entityList).getPrimaryKey().equals(entityCode)) {

				list.remove(i);
				this.saveAll(list);
				break;

			}
		}
	}

	@Override
	public T show(Object entityCode) {
		List<T> list = this.list();
		for (T entity : list) {
			if (((IEntity) entity).getPrimaryKey() == entityCode) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public List<T> list() {

		this.createFileIfNotExists(this.filePath);

		List<T> list = new ArrayList<T>();

		try {
			FileReader fileReader = new FileReader(this.filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			while (line != null) {

				T entity = this.fromString.apply(line);
				list.add(entity);

				line = bufferedReader.readLine();
			}

			bufferedReader.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return list;
	}

}
