package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S05T01N02CompanyValletErnestApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N02CompanyValletErnestApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
