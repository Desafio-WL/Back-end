package br.com.grupo.wl.unidac.dto;

import br.com.grupo.wl.unidac.model.Colaborador;
import lombok.Data;

@Data
public class ColaboradorRQ {
	private String alimento;

	private String nomeColaborador;

	private String cpf;

	public Colaborador converter() {

		return new Colaborador(alimento, nomeColaborador, cpf);
	}

}
