package com.WizardsOfTheCoast.magic;
import com.WizardsOfTheCoast.magic.Data_sample.SampleDataLoader;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
@SpringBootApplication
public class MagicApplication {

	@Bean
	public DataSource getDataSource()
	{
		Dotenv dotenv = Dotenv.load();
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(dotenv.get("DB_DRIVER"));
		dataSourceBuilder.url(dotenv.get("DB_URL"));
       	dataSourceBuilder.username(dotenv.get("DB_USERNAME"));
	   	dataSourceBuilder.password(dotenv.get("DB_PASSWORD"));
		return dataSourceBuilder.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(MagicApplication.class, args);
	}
}
