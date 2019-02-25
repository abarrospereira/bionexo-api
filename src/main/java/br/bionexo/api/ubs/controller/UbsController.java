package br.bionexo.api.ubs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.bionexo.api.exception.NotFoundException;
import br.bionexo.api.ubs.controller.filter.SearchUbsParameters;
import br.bionexo.api.ubs.domain.Ubs;
import br.bionexo.api.ubs.service.UbsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/ubs")
public class UbsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UbsController.class);

	@Autowired
	private UbsService ubsService;

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Esta operação retorna unidade basica de saúde por id", response = Ubs.class),
			@ApiResponse(code = 404, message = "Ubs não encontrada", response = NotFoundException.class)})
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Ubs find(@PathVariable(name = "id", required = true) final Long id) {
		LOGGER.info(String.format("find by id: {id:%s}", id));
		Ubs ubs = ubsService.find(id);
		return ubs;
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Listar unidades basicas com base posição atual e filtrada por nome", response = Ubs.class)})
	@RequestMapping(method = RequestMethod.GET)
	public Page<Ubs> find(SearchUbsParameters searchUbsParameters, Pageable pageable) {
		LOGGER.info(String.format("find by latitude: {longitude:%s} and longitude: {latitude:%s} and {name: %s}",
				searchUbsParameters.getLatitude(), searchUbsParameters.getLongitude(), searchUbsParameters.getName()));
		return ubsService.find(searchUbsParameters, pageable);
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Salvar unidade basica", response = Ubs.class),
			@ApiResponse(code = 404, message = "Erro de negócio ou validação", response = NotFoundException.class)})
	@RequestMapping(method = RequestMethod.POST)
	public Ubs save(@RequestBody @Validated  Ubs ubs ) {
		LOGGER.info(String.format("Create ubs: {name : %s}", ubs.getName()));
		return ubsService.save(ubs);
	}

}
