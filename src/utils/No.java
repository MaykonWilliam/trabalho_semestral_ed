package utils;

public class No<T> {

	T data;
	No<T> next;

	@Override
	public String toString() {
		return "No [data=" + data + "]";
	}

}