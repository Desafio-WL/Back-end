package br.com.grupo.wl.unidac.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.grupo.wl.unidac.abstraction.UpdateColaborador;
import br.com.grupo.wl.unidac.dto.ColaboradorRQ;
import br.com.grupo.wl.unidac.dto.ColaboradorRS;
import br.com.grupo.wl.unidac.model.Colaborador;
import br.com.grupo.wl.unidac.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	// criando o método para listar todos os Cafés
	public List<ColaboradorRS> getColaboradores() {

		List<Colaborador> colaborador = colaboradorRepository.findAll();

		return ColaboradorRS.converter(colaborador);
	}

	// criando o método para adicionar todos os Cafés
	@SuppressWarnings("rawtypes")
	public ResponseEntity addCafes(@RequestBody ColaboradorRQ colaboradorRQ, UriComponentsBuilder uriBuilder,
			HttpServletResponse responsehttp) {

		Colaborador colaborador = colaboradorRQ.converter();

		boolean alimentoExiste = colaboradorRepository.existsByAlimento(colaboradorRQ.getAlimento());
		boolean cpfExiste = colaboradorRepository.existsByCpf(colaboradorRQ.getCpf());
		boolean nome = colaborador.getNomeColaborador().isBlank();
		boolean alimento = colaborador.getAlimento().isBlank();
		boolean cpf = colaborador.getCpf().isBlank();

		try {

			if (nome) {
				
				responsehttp.sendError(400, "nome nulo ou invalido");
				HttpHeaders headers = new HttpHeaders();
				headers.set("nulo ou invalido", "Erro");
				return ResponseEntity.status(400)
						.headers(headers).body(null);
					

			} else if (alimento) {

				responsehttp.sendError(400, "Alimento nulo ou invalido");
				HttpHeaders headers = new HttpHeaders();
				headers.set("Alimento nulo ou invalido", "Erro");
				return ResponseEntity.status(400)
						.headers(headers).body(null);

			}

			else if (cpf) {

				responsehttp.sendError(400, "cpf nulo ou invalido");
				HttpHeaders headers = new HttpHeaders();
				headers.set("cpf nulo ou invalido", "Erro");
				return ResponseEntity.status(400)
						.headers(headers).body(null);

			}

			else if (cpfExiste) {
				responsehttp.sendError(400, "cpf ja cadastrado");
				HttpHeaders headers = new HttpHeaders();
				headers.set("cpf ja cadastrado", "Erro");
				return ResponseEntity.status(400)
						.headers(headers).body(null);

			}

			else if (alimentoExiste) {
				responsehttp.sendError(400, "alimento ja cadastrado");
				HttpHeaders headers = new HttpHeaders();
				headers.set("alimento ja cadastrado", "Erro");
				return ResponseEntity.status(400)
						.headers(headers).body(null);


			}

			else {
				colaboradorRepository.save(colaborador);
				colaboradorRepository.save(colaborador);

				URI uri = uriBuilder.path("api/v1/colaborador/{id}").buildAndExpand(colaborador.getId()).toUri();
				return ResponseEntity.created(uri).body(new ColaboradorRS(colaborador));

			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();

		}

	}

	// Criando o método para atualizar um Colaborador
	public ResponseEntity<ColaboradorRS> updateColaborador(@PathVariable("id") Long id,
			@RequestBody UpdateColaborador updateColaborador) {
		Optional<Colaborador> optional = colaboradorRepository.findById(id);

		if (optional.isPresent()) {
			Colaborador colaborador = updateColaborador.atualizarColaborador(id, colaboradorRepository);
			return ResponseEntity.ok(new ColaboradorRS(colaborador));

		} else {

			return ResponseEntity.notFound().build();
		}
	}

	// Criando o método para deletar um café

	public ResponseEntity<?> deleteColaborador(@PathVariable("id") Long id) {
		Optional<Colaborador> optional = colaboradorRepository.findById(id);
		if (optional.isPresent()) {
			colaboradorRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
