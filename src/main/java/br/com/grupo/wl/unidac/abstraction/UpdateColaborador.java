package br.com.grupo.wl.unidac.abstraction;

import br.com.grupo.wl.unidac.model.Colaborador;
import br.com.grupo.wl.unidac.repository.ColaboradorRepository;
import lombok.Data;


@Data
public class UpdateColaborador {

	
	
	private String cpf;

	private String alimento;
	
	private String nomeColaborador;


	// atualizando o objeto
		public Colaborador atualizarColaborador(Long id, ColaboradorRepository colaboradorRepository) {
			Colaborador colaborador = colaboradorRepository.getById(id);

			colaborador.setNomeColaborador(this.nomeColaborador);

			colaborador.setAlimento(this.alimento);
			
			colaborador.setCpf(this.cpf);
			
			
			
			return colaborador;
		}
	
	
	
}
