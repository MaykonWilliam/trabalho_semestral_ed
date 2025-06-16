package adapters.mappers;

import domain.entities.Disciplina;

public class DisciplinaMapper {

	static public String toString(Disciplina entity) {
		return entity.getCodigo() + ";" + entity.getNome() + ";" + entity.getDiaSemana() + ";" + entity.getHorarioInicial() + ";" + entity.getHorasDiarias() + ";" + entity.getCodigoCurso() + ";";
	}

	static public Disciplina toEntity(String record) {
		String[] data = record.split(";");

		String codigo = data[0];
		String nome = data[1];
		String diaSemana = data[2];
		String horarioInicial = data[3];
		String horasDiarias = data[4];
		Object codigoCurso = Integer.parseInt(data[5]);

		return new Disciplina(codigo, nome, diaSemana, horarioInicial, horasDiarias, codigoCurso);
	}
}