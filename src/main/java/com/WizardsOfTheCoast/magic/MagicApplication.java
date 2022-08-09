package com.WizardsOfTheCoast.magic;
import com.WizardsOfTheCoast.magic.entity.*;
import com.WizardsOfTheCoast.magic.service.UserService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.ArrayList;

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

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userservice){
		return args -> {
			userservice.saveRole(new Role(null, "ROLE_USER"));
			userservice.saveRole(new Role(null, "ROLE_ADMIN"));
			userservice.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userservice.createNewUser(new User(null,"Christian Lace","chris",
					"asd@asd.com","asd125",null,
					new ArrayList<>(), null, null));

			userservice.addRoleToUser("chris", "ROLE_SUPER_ADMIN");
		};
	}

}
