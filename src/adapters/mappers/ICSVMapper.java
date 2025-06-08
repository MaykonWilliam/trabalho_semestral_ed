package adapters.mappers;

public interface ICSVMapper<T> {
	public String toString(T entity);
	public T toEntity(String record);
}
