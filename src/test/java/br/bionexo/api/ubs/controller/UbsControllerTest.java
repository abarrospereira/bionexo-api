package br.bionexo.api.ubs.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.bionexo.api.ubs.controller.builder.UbsBuilder;
import br.bionexo.api.ubs.controller.filter.SearchUbsParameters;
import br.bionexo.api.ubs.domain.Ubs;
import br.bionexo.api.ubs.service.UbsService;
import br.bionexo.api.util.JSONParser;

@RunWith(MockitoJUnitRunner.class)
public class UbsControllerTest {

	private static final Long ID = 1L;

	private static final String NAME = "Name Teste";

	private static final String ADDRESS = "Endereço Teste";

	private static final String CITY = "São José dos Campos";

	private static final String PHONE = "12996394956";

	private static final Long SCORES_ADAPTATIONS_SENIORS = 1L;

	private static final Long SCORES_MEDICAL_EQUIPMENT = 2L;

	private static final Long SCORES_MEDICINE = 3L;

	private static final double LATITUDE = -23.2230;

	private static final double LONGITUDE = -43-12321;
	
	
	private static final Integer PAGE = 0;

	private static final Integer SIZE = 15;
	
	public static final String ENDPOINT = "/rest/ubs";

	private MockMvc mockMvc;
	
	@InjectMocks
	private UbsController controller;
	
	@Mock
	private UbsService ubsService; 
	
	
	@Before
	public void onInit() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void shouldFindUbsAndReturnUbs() throws Exception {
		when(ubsService.find(any(SearchUbsParameters.class), any(Pageable.class))).thenReturn(new PageImpl<Ubs>(Arrays.asList(getUbs()), new PageRequest(0, 15), 1));

		mockMvc.perform(get(ENDPOINT)
				.param("page", PAGE.toString()).param("size", SIZE.toString())
				.param("latitude", String.valueOf(LATITUDE)) 
				.param("longitude", String.valueOf(LONGITUDE))
				.param("name", NAME)
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].name", equalTo(NAME.toString())))
				.andExpect(jsonPath("$.size", equalTo(SIZE)))
				.andExpect(jsonPath("$.number", equalTo(PAGE)));

		verify(ubsService).find(any(SearchUbsParameters.class),any(Pageable.class));
	}
	
	@Test
	public void shouldFindUbsById() throws Exception {
		when(ubsService.find(Mockito.anyLong())).thenReturn(getUbs());

		mockMvc.perform(get(ENDPOINT +"/{id}", 10L)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		verify(ubsService).find(any(Long.class));
	}
	
	@Test
	public void shouldSaveUbs() throws Exception {
		Ubs ubs = getUbs();

		when(ubsService.save(any(Ubs.class))).thenReturn(ubs);

		mockMvc.perform(post(ENDPOINT)
				.contentType(APPLICATION_JSON)
				.content(JSONParser.toJSON(ubs)))
				.andExpect(status().isOk());

		verify(ubsService).save(any(Ubs.class));
	}
	
	private Ubs getUbs() {
		return UbsBuilder.init()
				  .withId(ID)
				  .withName(NAME)
				  .withAddress(ADDRESS)
				  .withCity(CITY)
				  .withPhone(PHONE)
				  .withGeoCodeLate(LATITUDE)
				  .withGeoCodeLong(LONGITUDE)
				  .withScoresAdaptationSeniors(SCORES_ADAPTATIONS_SENIORS)
				  .withscoresMedicalEquipment(SCORES_MEDICAL_EQUIPMENT)
				  .withscoresScoresMedicine(SCORES_MEDICINE)
				  .build();
	}
	
}
