package br.com.grupo.wl.unidac.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.grupo.wl.unidac.model.Colaborador;
import lombok.Data;

@Data
public class ColaboradorRS {

	private Long id;

	private String cpf;

	private String alimento;

	private String nomeColaborador;

	// Construtor para fazer a conversão
	public ColaboradorRS(Colaborador colaborador) {

		this.cpf = colaborador.getCpf();

		this.id = colaborador.getId();

		this.nomeColaborador = colaborador.getNomeColaborador();

		this.alimento = colaborador.getAlimento();

	}

	// Método que faz a conversão
	public static List<ColaboradorRS> converter(List<Colaborador> colaborador) {

		return colaborador.stream().map(ColaboradorRS::new).collect(Collectors.toList());

	}
}
