package utils;

public class List<T> {

	No<T> first;

	public List() {
		first = null;
	}

	public boolean isEmpty() {
		if (first == null) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		int cont = 0;
		if (!isEmpty()) {
			No<T> aux = first;
			while (aux != null) {
				cont++;
				aux = aux.next;
			}
		}
		return cont;
	}

	private No<T> getNo(int position) throws Exception {
		if (isEmpty()) {
			throw new Exception("List is Empty");
		}
		int ListSize = size();
		if (position < 0 || position > ListSize - 1) {
			throw new Exception("Invalid Position");
		}
		int count = 0;
		No<T> aux = first;
		while (count < position) {
			count++;
			aux = aux.next;
		}
		return aux;
	}

	public void addFirst(T data) {
		No<T> element = new No<>();
		element.data = data;
		element.next = first;
		first = element;
	}

	public void addLast(T data) throws Exception {
		if (isEmpty()) {
//			throw new Exception("Lista Vazia");
			addFirst(data);
			return;
		}
		int listSize = size();
		No<T> element = new No<>();
		element.data = data;
		element.next = null;
		No<T> last = getNo(listSize - 1);
		last.next = element;
	}

	public void add(T data, int position) throws Exception {
		int listSize = size();
		if (position < 0 || position > listSize) {
			throw new Exception("Invalid Position");
		}
		if (position == 0) {
			addFirst(data);
		} else if (position == listSize) {
			addLast(data);
		} else {
			No<T> previous = getNo(position - 1);
			No<T> element = new No<>();
			element.data = data;
			element.next = previous.next;
			previous.next = element;
		}
	}

	public void removeFirst() throws Exception {
		if (isEmpty()) {
			throw new Exception("List is Empty");
		}
		first = first.next;
	}

	public void removeLast() throws Exception {
		if (isEmpty()) {
			throw new Exception("List is Empty");
		}
		int listSize = size();
		if (listSize == 1) {
			removeFirst();
		} else {
			No<T> penultimate = getNo(listSize - 2);
			penultimate.next = null;
		}
	}

	public void remove(int position) throws Exception {
		if (isEmpty()) {
			throw new Exception("List is Empty");
		}
		int listSize = size();
		if (position < 0 || position > listSize - 1) {
			throw new Exception("Invalid Position");
		}
		if (position == 0) {
			removeFirst();
		} else if (position == listSize - 1) {
			removeLast();
		} else {
			No<T> previous = getNo(position - 1);
			No<T> current = getNo(position);
			previous.next = current.next;
		}
	}

	public T get(int pos) throws Exception {
		if (isEmpty()) {
			throw new Exception("List is Empty");
		}
		int listSize = size();
		if (pos < 0 || pos > listSize - 1) {
			throw new Exception("Invalid Position");
		}
		No<T> aux = getNo(pos);
		return aux.data;
	}
}