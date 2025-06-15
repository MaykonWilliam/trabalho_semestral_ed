package adapters.mappers;

import domain.entities.Disciplina;

public class DisciplinaMapper {

	static public String toString(Disciplina entity) {
		return entity.getCodigo() + ";" + entity.getNome() + ";" + entity.getDiaSemana() + ";" + entity.getHorarioInicial() + ";" + entity.getHorasDiarias() + ";"+ entity.getCodigoCurso() + ";";
	}

	static public Disciplina toEntity(String record) {
		String[] data = record.split(";");
		
		String codigo_disciplina = data[0];
		String nome_disciplina = data[1];
		String dia_semana = data[2];
		String hora_inicial = data[3];
		String hora_diaria = data[4];
		int codigo_curso = Integer.parseInt(data[5]);

		return new Disciplina(codigo_disciplina, nome_disciplina, dia_semana, hora_inicial, hora_diaria, codigo_curso);
	}
}