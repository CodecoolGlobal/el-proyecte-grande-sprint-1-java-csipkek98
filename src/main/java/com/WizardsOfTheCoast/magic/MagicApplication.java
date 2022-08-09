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
	public static void main(String[] args) {
		SpringApplication.run(MagicApplication.class, args);
	}
}
