package adapters.mappers;

import domain.entities.Professor;

public class ProfessorMapper {
	static public String toString(Professor entity) {

		return entity.getCpf() + ";" + entity.getNomeProfessor() + ";" + entity.getAreaConhecimento() + ";" + entity.getPontuacao();
	}

	static public Professor toEntity(String record) {

		String[] data = record.split(";");

		return new Professor(data[0], data[1], data[2], Integer.parseInt(data[4]));
	}
}
