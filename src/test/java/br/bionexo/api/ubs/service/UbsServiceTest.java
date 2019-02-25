package br.bionexo.api.ubs.service;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;

import br.bionexo.api.exception.NotFoundException;
import br.bionexo.api.ubs.controller.builder.UbsBuilder;
import br.bionexo.api.ubs.controller.filter.SearchUbsParameters;
import br.bionexo.api.ubs.domain.Ubs;
import br.bionexo.api.ubs.repository.UbsRepository;

@RunWith(MockitoJUnitRunner.class)
public class UbsServiceTest {

	
	private static final Long ID = 1L;

	private static final String NAME = "Name Teste";

	private static final String ADDRESS = "Endereço Teste";

	private static final String CITY = "São José dos Campos";

	private static final String PHONE = "12996394956";

	private static final Long SCORES_ADAPTATIONS_SENIORS = 1L;

	private static final Long SCORES_MEDICAL_EQUIPMENT = 2L;

	private static final Long SCORES_MEDICINE = 3L;

	private static final double LATITUDE = -23.621542;

	private static final double LONGITUDE = -46.679222;

	private static final Object ERROR_NOTFOUND = "error.not.found";
	
	private static final PageRequest PAGEABLE = PageRequest.of(1, 15);
	
	@InjectMocks
	private UbsService ubsService;
	
	@Mock
	private UbsRepository ubsRepository;
	

	
	@Test
	public void shouldFindById() {
		Ubs ubs = getUbs(LATITUDE, LONGITUDE);
		Mockito.when(ubsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ubs));
		
		Ubs resultUbs = ubsService.find(ID);
		
		Mockito.verify(ubsRepository).findById(Mockito.anyLong());

		Assert.assertEquals(ID, resultUbs.getId());
		Assert.assertEquals(NAME, resultUbs.getName());
	}

	@Test
	public void shouldThrowExceptionWhenUbsNotFound() {
		Mockito.when(ubsRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		try {
			ubsService.find(ID);
		}catch(NotFoundException e) {
			assertEquals(ERROR_NOTFOUND, e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldFindUbsByLocation2500() {
		
		setParametersDistanceMeters(2500);
		List<Ubs> listUbs = getListUbs();
		
		Mockito.when(ubsRepository.findAll(Mockito.any(Specification.class))).thenReturn(listUbs);
		
		SearchUbsParameters searchUbsParameters = getSearchUbsParameters();
		Page<Ubs> ubs = ubsService.find(searchUbsParameters, PAGEABLE);
		
		Mockito.verify(ubsRepository).findAll(Mockito.any(Specification.class));
		
		assertEquals(2, ubs.getContent().size());
		assertEquals(15, ubs.getSize());
		assertEquals(1, ubs.getNumber());
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldFindUbsByLocation5000Meters() {
		
		setParametersDistanceMeters(5000);
		List<Ubs> listUbs = getListUbs();
		
		Mockito.when(ubsRepository.findAll(Mockito.any(Specification.class))).thenReturn(listUbs);
		
		SearchUbsParameters searchUbsParameters = getSearchUbsParameters();
		Page<Ubs> ubs = ubsService.find(searchUbsParameters, PAGEABLE);
		
		Mockito.verify(ubsRepository).findAll(Mockito.any(Specification.class));
		
		assertEquals(3, ubs.getContent().size());
		assertEquals(15, ubs.getSize());
		assertEquals(1, ubs.getNumber());
		
	}
	
	@Test
	public void shouldSaveUbs() {
		Ubs ubs = getUbs(LATITUDE, LONGITUDE);
		
		Mockito.when(ubsRepository.save(Mockito.any(Ubs.class))).thenReturn(ubs);
		Ubs resulUbs = ubsService.save(ubs);
		
		Mockito.verify(ubsRepository).save(Mockito.any());
		
		assertEquals(ID, resulUbs.getId());
		assertEquals(NAME, resulUbs.getName());
	}
	
	
	private SearchUbsParameters getSearchUbsParameters() {
		SearchUbsParameters searchUbsParameters = new SearchUbsParameters();
		
		
		searchUbsParameters.setLatitude(LATITUDE);
		searchUbsParameters.setLongitude(LONGITUDE);
		searchUbsParameters.setName(NAME);
		
		return searchUbsParameters;
	}
	
	private Ubs getUbs(double latitude, double longitude) {
		return UbsBuilder.init()
				  .withId(ID)
				  .withName(NAME)
				  .withAddress(ADDRESS)
				  .withCity(CITY)
				  .withPhone(PHONE)
				  .withGeoCodeLate(latitude)
				  .withGeoCodeLong(longitude)
				  .withScoresAdaptationSeniors(SCORES_ADAPTATIONS_SENIORS)
				  .withscoresMedicalEquipment(SCORES_MEDICAL_EQUIPMENT)
				  .withscoresScoresMedicine(SCORES_MEDICINE)
				  .build();
	}
	
	
	private void setParametersDistanceMeters(double size) {
		Field field = ReflectionUtils.findField(UbsService.class, "parametersDistanceMeters");
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, ubsService, size);
	}
	
	private List<Ubs> getListUbs() {
		return Arrays.asList(
				getUbs(-23.617338, -46.674593), //distance 663 meters
				getUbs(-23.616041, -46.687210), //distance 1017 meters
				getUbs(-23.615516, -46.630666)); //distance 4999 meters
	}
}
