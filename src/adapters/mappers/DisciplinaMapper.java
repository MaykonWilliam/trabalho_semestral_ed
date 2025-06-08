package adapters.mappers;

import domain.entities.Disciplina;

public class DisciplinaMapper{


	static public String toString(Disciplina entity) {
		return entity.getCodigo_disciplina() + ";" + entity.getNome_disciplina() + ";" + entity.getDia_semana() + ";" + entity.getHora_inicial() + ";" + entity.getHora_diaria() + ";";
	}


	static public Disciplina toEntity(String record) {
		String[] data = record.split(";");
		
		String codigo_disciplina = data[0];
		String nome_disciplina = data[1];
		String dia_semana = data[2];
		String hora_inicial = data[3];
		Float hora_diaria = Float.parseFloat(data[4]);
		
		return new Disciplina(codigo_disciplina, nome_disciplina, dia_semana, hora_inicial, hora_diaria);
	}

}
