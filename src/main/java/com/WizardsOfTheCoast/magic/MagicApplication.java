package com.WizardsOfTheCoast.magic;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Map;

@SpringBootApplication
public class MagicApplication {

	@Bean
	public DataSource getDataSource()
	{
		Dotenv dotenv = Dotenv.load();
		Map<String, String> env = System.getenv();
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url("jdbc:postgresql://localhost:5432/hogwarts");
       	dataSourceBuilder.username(dotenv.get("DB_USERNAME"));
	   	dataSourceBuilder.password(dotenv.get("DB_PASSWORD"));
		return dataSourceBuilder.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(MagicApplication.class, args);
	}

}
