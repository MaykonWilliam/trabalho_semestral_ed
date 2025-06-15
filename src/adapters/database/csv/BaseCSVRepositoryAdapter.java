package adapters.database.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

import br.edu.fateczl.Lista;
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

	protected void deleteFileIfExists(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	protected void saveAll(Lista<T> lista) throws Exception {

		try {

			this.deleteFileIfExists(filePath);
			this.createFileIfNotExists(this.filePath);

			FileWriter fileWriter = new FileWriter(this.filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for(int i = 0; i < lista.size(); i++) {
				T entity = lista.get(i);
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
		Lista<T> entities = lista();
		entities.addLast(entity);
		this.saveAll(entities);
	}

	@Override
	public void update(T entity) throws Exception {
		Lista<T> lista = this.lista();
		Object primaryKey = ((IEntity) entity).getPrimaryKey();

		for (int i = 0; i < lista.size(); i++) {
			T entityList = lista.get(i);

			if (((IEntity) entityList).getPrimaryKey().equals(primaryKey)) {

				lista.remove(i);
				lista.add(entity, i);
				break;

			}
		}

		this.saveAll(lista);
	}

	@Override
	public void delete(T entity) throws Exception {
		Lista<T> lista = this.lista();

		Object entityCode = ((IEntity) entity).getPrimaryKey();

		for (int i = 0; i < lista.size(); i++) {
			T entityList = lista.get(i);

			if (((IEntity) entityList).getPrimaryKey().equals(entityCode)) {

				lista.remove(i);
				this.saveAll(lista);
				break;

			}
		}
	}

	@Override
	public T show(Object entityCode) throws Exception {
		Lista<T> lista = this.lista();
		
		for(int i = 0; i < lista.size(); i++) {
			T entity = lista.get(i);
			if (((IEntity) entity).getPrimaryKey().equals(entityCode)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public Lista<T> lista() throws Exception {

		this.createFileIfNotExists(this.filePath);

		Lista<T> lista = new Lista<T>();

		try {
			FileReader fileReader = new FileReader(this.filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			while (line != null) {

				T entity = this.fromString.apply(line);
				if(lista.isEmpty()) {
					lista.addFirst(entity);
				} else {
					lista.addLast(entity);
				}
				line = bufferedReader.readLine();
			}

			bufferedReader.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return lista;
	}

}
