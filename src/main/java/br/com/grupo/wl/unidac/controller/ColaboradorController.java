package br.com.grupo.wl.unidac.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.grupo.wl.unidac.abstraction.UpdateColaborador;
import br.com.grupo.wl.unidac.dto.ColaboradorRQ;
import br.com.grupo.wl.unidac.dto.ColaboradorRS;
import br.com.grupo.wl.unidac.service.ColaboradorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/colaborador")
@Api(value = "API REST grupo WL")
@CrossOrigin(origins = "*") // Mudar para os domínios específicos
public class ColaboradorController {

	// Instanciando ColaboradorService

	private final ColaboradorService colaboradorService;

	@Autowired
	public ColaboradorController(ColaboradorService colaboradorService) {

		this.colaboradorService = colaboradorService;
	}

	// ---------------------------------------------------------------------------------//

	// Chamando um método dentro do serviço Colaborador
	@ApiOperation("Acessa a tabela colaborador")
	@Transactional
	@GetMapping()
	public List<ColaboradorRS> getColaboradores() {

		return colaboradorService.getColaboradores();
	}

	// Chamando um método dentro do serviço Colaborador
	@ApiOperation("Adiciona um objeto na tabela colaborador")
	@Transactional
	@PostMapping()
	public ResponseEntity<?> addCafes(@RequestBody ColaboradorRQ colaboradorRQ, UriComponentsBuilder uriBuilder,
			HttpServletResponse responsehttp) {
		return colaboradorService.addCafes(colaboradorRQ, uriBuilder, responsehttp);
	}

	// Chamando um método dentro do serviço Colaborador
	@ApiOperation("Atualiza um objeto na tabela colaborador")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ColaboradorRS> updateColaborador(@PathVariable("id") Long id,
			@RequestBody UpdateColaborador updateColaborador) {

		return colaboradorService.updateColaborador(id, updateColaborador);
	}

	// Chamando um método dentro do serviço Colaborador
	@ApiOperation("Deleta um objeto na tabela colaborador")
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteColaborador(@PathVariable("id") Long id) {

		return colaboradorService.deleteColaborador(id);
	}
}
