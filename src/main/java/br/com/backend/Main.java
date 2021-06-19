package br.com.backend;

import java.util.Date;

import br.com.backend.entidades.Categoria;
import br.com.backend.entidades.Exame;
import br.com.backend.persistencia.DynamoDBConfig;

import java.text.DateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DynamoDBConfig.class})
public class Main {
	
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		
		
		Date data = new Date();
		DateFormat dfm = DateFormat.getDateInstance(DateFormat.SHORT);
		String dataAtual = dfm.format(data);

//		Exame ex = new Exame("1", "Exame contra o COVID 19", false, Categoria.ADMICIONAL, dataAtual);
		
		log.info("Inicializando...");
		System.setProperty("server.servlet.context-path", "/workMed-exame");
		new SpringApplicationBuilder(Main.class).web(WebApplicationType.SERVLET).run(args);

	}

}
