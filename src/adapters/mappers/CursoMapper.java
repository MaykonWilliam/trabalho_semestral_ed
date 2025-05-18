package adapters.mappers;

import domain.entities.Curso;

public class CursoMapper {

	static public String toString(Curso entity) {

		return entity.getCodigo() + ";" + entity.getNome() + ";" + entity.getArea();
	}

	static public Curso toEntity(String record) {

		String[] data = record.split(";");

		return new Curso(Integer.parseInt(data[0]), data[1], data[2]);
	}

}
