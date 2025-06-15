package adapters.database.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

import domain.entities.IEntity;
import domain.repositories.IBaseRepository;

import utils.List;

public abstract class BaseCSVRepositoryAdapter<T> implements IBaseRepository<T> {

	protected final String filePath;
	protected final Function<T, String> toString;
	protected final Function<String, T> toEntity;

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

				list.remove(i);
				this.saveAll(list);
				break;

			}
		}
	}

	@Override
	public T show(Object entityCode) throws Exception {
		List<T> list = this.list();

		for (int i = 0; i < list.size(); i++) {
			T entity = list.get(i);
			if (((IEntity) entity).getPrimaryKey().equals(entityCode)) {
				return entity;
			}
		}
		return null;
	}

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
				if (list.isEmpty()) {
					list.addFirst(entity);
				} else {
					list.addLast(entity);
				}
=======
				T entity = this.toEntity.apply(line);
				list.add(entity);

>>>>>>> 53120df (WIP)
				line = bufferedReader.readLine();
			}

			bufferedReader.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return list;
	}

}
