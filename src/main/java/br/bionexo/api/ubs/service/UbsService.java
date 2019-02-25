package br.bionexo.api.ubs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.bionexo.api.exception.NotFoundException;
import br.bionexo.api.ubs.controller.filter.SearchUbsParameters;
import br.bionexo.api.ubs.domain.Ubs;
import br.bionexo.api.ubs.repository.UbsRepository;
import br.bionexo.api.ubs.repository.specification.QUbs;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
import net.sf.geographiclib.GeodesicMask;


/**
 * Classe UbsService responsável por forneceder os métodos de consultas para controller
 * retornando as unidades basicas de saúde
 * @author André Pereira
 * @version 1.0
 *
 */
@Service
@Transactional
public class UbsService {
	
	@Value("${parameter.distance.meters}")
	private double parametersDistanceMeters;
	
	@Autowired
	private UbsRepository ubsRepository;
	
	/**
	 * Método que retorna uma Unidade basica de saúde
	 * @param id - Representa o identificador da tabela ubs
	 * @return - retorna ubs
	 */
	public Ubs find(Long id) {
		Optional<Ubs> ubs = ubsRepository.findById(id);
		
		if(!ubs.isPresent())
			throw new NotFoundException("error.not.found");
		
		return ubs.get();
	}
	
	
	/**
	 * 
	 * @param searchUbsParameters - parametros utilizados na busca que representa posição do usuário como latitude e longitude
	 * @param pageable - Objeto de paginação onde é passado a pagina que que se encontra, quantidade de registros e ordenação;
	 * @return - O Retorno da consulta irá se basear nos parametros de localização que o usuário passou.
	 * Calculando a distância(longitude e latitude) dos registros encontrados 
	 *  - O calculo se baseia lib GeographicLib implementada em Java calculando distancia entre os pontos e retornandos em metros
	 */
	public Page<Ubs> find(SearchUbsParameters searchUbsParameters, Pageable pageable){
		List<Ubs> ubs = ubsRepository.findAll(Specification.where(QUbs.nameLike(searchUbsParameters.getName())));
		
		List<Ubs> ubsFilterd = ubs.stream().filter(x ->  applyFilterDistance(searchUbsParameters.getLatitude(), searchUbsParameters.getLongitude(), x, parametersDistanceMeters))
										   .collect(Collectors.toList());
		
		return new PageImpl<Ubs>(ubsFilterd, pageable, ubsFilterd.size());
	}

	public Ubs save(Ubs ubs) {
		return ubsRepository.save(ubs);
	}
	
	
	private boolean applyFilterDistance(double latitude, double longitude, Ubs ubs, double distance) {
		GeodesicData g = Geodesic.WGS84.Inverse(latitude,longitude ,ubs.getGeocodeLate() ,  ubs.getGeocodeLong(), GeodesicMask.DISTANCE);
		return g.s12 <= distance;
	}
	
}
