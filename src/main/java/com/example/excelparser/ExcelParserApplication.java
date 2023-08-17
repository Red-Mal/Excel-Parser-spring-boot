package com.example.excelparser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class ExcelParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelParserApplication.class, args);
	}

	/*@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder().name("Reda ELmallahi").email("reda@gmail.com").dateDebut(new Date()).dateFin(new Date()).build());
			customerRepository.save(Customer.builder().name("Achraf AINANE").email("achraf@gmail.com").dateDebut(new Date()).dateFin(new Date()).build());
			customerRepository.save(Customer.builder().name("Nabil ELMEHDY").email("nabil@gmail.com").dateDebut(new Date()).dateFin(new Date()).build());
		};
	}*/

}
