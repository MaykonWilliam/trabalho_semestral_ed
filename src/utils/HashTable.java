package utils;

import domain.interfaces.IEntity;

public class HashTable<T> {

	private List<T>[] entities;

	@SuppressWarnings("unchecked")
	public HashTable() {
		entities = new List[1000];
		initializeHashTable();
	}

	private void initializeHashTable() {
		int size = entities.length;
		for (int i = 0; i < size; i++) {
			entities[i] = new List<T>();
		}
	}

	public void addEntity(T entity) {
		int position = entity.hashCode();
		position = Math.abs(position) % 1000;
		entities[position].addFirst(entity);
	}

	public void removeEntity(T entity) throws Exception {
		int hash = Math.abs(entity.hashCode()) % 1000;
		List<T> l = entities[hash];
		if (!l.isEmpty()) {
			int size = l.size();
			for (int i = 0; i < size; i++) {
				T f = l.get(i);
				if (((IEntity) entity).getPrimaryKey().equals(((IEntity) f).getPrimaryKey())) {
					l.remove(i);
					break;
				}
			}
		}
	}

	public T searchEntity(T entity) throws Exception {
		int hash = Math.abs(entity.hashCode()) % 1000;
		List<T> l = entities[hash];
		if (!l.isEmpty()) {
			int size = l.size();
			for (int i = 0; i < size; i++) {
				T f = l.get(i);
				if (((IEntity) entity).getPrimaryKey().equals(((IEntity) f).getPrimaryKey())) {
					return f;
				}
			}
		}
		return null;
	}

	
	public List<T> getList(int hash) throws Exception {
		if (hash < 0 || hash >= entities.length) {
			throw new Exception("Posição inválida na hash table");
		}
		return entities[hash];
	}

	public List<T> getAllEntities() throws Exception {
		List<T> allEntities = new List<T>();
		int length = entities.length;

		for (int i = 0; i < length; i++) {
			List<T> l = entities[i];
			if (!l.isEmpty()) {
				int size = l.size();
				for (int j = 0; j < size; j++) {
					T entity = l.get(j);
					allEntities.addLast(entity);
				}
			}
		}

		return allEntities;
	}

	
	public int getTotalSize() throws Exception {
		int total = 0;
		int length = entities.length;

		for (int i = 0; i < length; i++) {
			List<T> l = entities[i];
			if (!l.isEmpty()) {
				total += l.size();
			}
		}

		return total;
	}

	public boolean isEmpty() throws Exception {
		return getTotalSize() == 0;
	}

	
	
	public void clear() {
		initializeHashTable();
	}

	
	public boolean contains(T entity) {
		try {
			return searchEntity(entity) != null;
		} catch (Exception e) {
			return false;
		}
	}
}