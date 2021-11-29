package br.com.grupo.wl.unidac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
@Table
public class Colaborador {

	public Colaborador(String alimento, String nomeColaborador, String cpf) {

		this.alimento = alimento;
		this.nomeColaborador = nomeColaborador;
		this.cpf = cpf;
	}

	// Construtor default para o Spring
	@Deprecated
	public Colaborador() {
		super();
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@CPF(message = "Cpf inválido")
	@Column(unique = true)
	@NotBlank(message="Cpf nulo ou inválido")
	private String cpf;

	@Column(unique = true)
	@NotBlank(message="Opção nula ou inválida")
	private String alimento;

	@NotBlank(message="Nome nulo ou inválido")
	@Column(unique = false)
	private String nomeColaborador;

}
