package adapters.mappers;

import domain.entities.Inscricao;

public class InscricaoMapper {

	static public String toString(Inscricao entity) {
		return entity.getCodigo() + ";" + entity.getCodigo_disciplina() + ";" + entity.getCpf_professor() + ";" + entity.getStatus();
	}

	static public Inscricao toEntity(String record) {
		String[] data = record.split(";");

		String codigo = data[0];
		String codigo_disciplina = data[1];
		String cpf_professor = data[2];
		String status = data[3];

		return new Inscricao(codigo, codigo_disciplina, cpf_professor, status);
	}

}
