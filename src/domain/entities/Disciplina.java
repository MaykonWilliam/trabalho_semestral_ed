package domain.entities;

public class Disciplina implements IEntity{
	private String codigo_disciplina;
	private String nome_disciplina;
	private String dia_semana;
	private String hora_inicial;
	private Float hora_diaria;

	public Disciplina(String codigo, String nome, String dia_semana, String hora_inicial, Float hora_diaria) {
		this.codigo_disciplina = codigo;
	    this.nome_disciplina = nome;
        this.dia_semana = dia_semana;
        this.hora_inicial = hora_inicial;
        this.hora_diaria = hora_diaria;
    }
	
	@Override
	public Object getPrimaryKey() {
		return codigo_disciplina;
	}

	public String getCodigo_disciplina() {
		return codigo_disciplina;
	}

	public void setCodigo_disciplina(String codigo_disciplina) {
		this.codigo_disciplina = codigo_disciplina;
	}

	public String getNome_disciplina() {
		return nome_disciplina;
	}

	public void setNome_disciplina(String nome_disciplina) {
		this.nome_disciplina = nome_disciplina;
	}

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public String getHora_inicial() {
		return hora_inicial;
	}

	public void setHora_inicial(String hora_inicial) {
		this.hora_inicial = hora_inicial;
	}

	public Float getHora_diaria() {
		return hora_diaria;
	}

	public void setHora_diaria(Float hora_diaria) {
		this.hora_diaria = hora_diaria;
	}	
}