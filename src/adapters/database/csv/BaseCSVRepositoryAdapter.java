package adapters.database.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

	protected void salvarTodos(List<T> lista) {

		// TODO Auto-generated method stub

	}

	@Override
	public void save(T entity) {
		
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<T> list() {

		// Variavel que irá arma
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
