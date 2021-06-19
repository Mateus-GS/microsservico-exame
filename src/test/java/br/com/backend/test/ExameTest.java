package br.com.backend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.com.backend.entidades.Exame;
import br.com.backend.persistencia.ExameRepository;



/**
 * Classe de testes para a entidade Exame.
 *  <br>
 * Para rodar, antes sete a seguinte variável de ambiente: -Dspring.config.location=C:/Users/Pichau/microsservicos/
 *  <br>
 * Neste diretório, criar um arquivo application.properties contendo as seguitnes variáveis:
 * <br>
 * amazon.aws.accesskey=<br>
 * amazon.aws.secretkey=<br>
 * @author Mateus
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, ExameTest.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExameTest {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ExameTest.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    
    @Configuration
	@EnableDynamoDBRepositories(basePackageClasses = { ExameRepository.class })
	public static class DynamoDBConfig {

		@Value("${amazon.aws.accesskey}")
		private String amazonAWSAccessKey;

		@Value("${amazon.aws.secretkey}")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}
    
	@Autowired
	private ExameRepository repository;
	
	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");;
		Exame c1 = new Exame("COVID", true, "COVID", "20/10/2021");
		Exame c2 = new Exame("Exame demicional", false, "DEMISIONAL","20/10/2021");
		Exame c3 = new Exame("Exame PCR", false, "COVID", "20/10/2021");
		Exame c4 = new Exame("Exame Sorologia", false, "COVID", "20/10/2021");
		repository.save(c1);
		repository.save(c2);
		repository.save(c3);
		repository.save(c4);

		Iterable<Exame> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Exame Exame : lista) {
			LOGGER.info(Exame.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Exame> result = repository.findByNome("Exame PCR");
		LOGGER.info("Encontrado: {}");
	}
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		List<Exame> result = repository.findByNome("COVID");
		for (Exame exame : result) {
			LOGGER.info("Excluindo Exame id = "+exame.getId());
			repository.delete(exame);
		}
		result = repository.findByNome("COVID");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}

}
