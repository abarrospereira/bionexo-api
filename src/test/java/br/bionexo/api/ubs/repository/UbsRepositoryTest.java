package br.bionexo.api.ubs.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.bionexo.api.ubs.controller.builder.UbsBuilder;
import br.bionexo.api.ubs.domain.Ubs;
import br.bionexo.api.ubs.repository.specification.QUbs;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = { "test" })
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
public class UbsRepositoryTest {


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
	
	
	public static final String ENDPOINT = "/rest/ubs";

	@Autowired
	private UbsRepository ubsRepository;
	
	@Before
	public void onInit() {
		ubsRepository.save(getUbs());
	}

	@After
	public void cleanDB() {
		this.ubsRepository.deleteAll();
	}
	
	@Test
	public void shouldFindAllUbs() {
		Integer number = this.ubsRepository.findAll().size();
		Assert.assertEquals(new Integer(1), number);
	}
	
	@Test
	public void shouldFindAllUbsByName() {
		Integer number = this.ubsRepository.findAll(Specification.where(QUbs.nameLike(NAME))).size();
		Assert.assertEquals(new Integer(1), number);
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
