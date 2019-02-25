package br.bionexo.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = { "test" })
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class ApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
