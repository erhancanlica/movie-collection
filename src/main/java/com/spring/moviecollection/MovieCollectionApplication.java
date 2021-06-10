package com.spring.moviecollection;

import com.spring.moviecollection.model.Category;
import com.spring.moviecollection.model.LanguageOption;
import com.spring.moviecollection.repository.CategoryRepository;
import com.spring.moviecollection.repository.LanguageOptionRepository;
import com.spring.moviecollection.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCollectionApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run(CategoryRepository categoryRepository, LanguageOptionRepository languageOptionRepository) throws Exception {
//		return (String[] args) -> {
//
//			Category category1 = Category.builder()
//					.categoryName("Korku").build();
//			Category category2 = Category.builder()
//					.categoryName("Gerilim").build();
//			Category category3 = Category.builder()
//					.categoryName("Bilim Kurgu").build();
//			Category category4 = Category.builder()
//					.categoryName("Macera").build();
//			Category category5 = Category.builder()
//					.categoryName("Aksiyon").build();
//			Category category6 = Category.builder()
//					.categoryName("Komedi").build();
//
//			categoryRepository.save(category1);
//			categoryRepository.save(category2);
//			categoryRepository.save(category3);
//			categoryRepository.save(category4);
//			categoryRepository.save(category5);
//			categoryRepository.save(category6);
//
//			LanguageOption languageOption1 = LanguageOption.builder()
//					.language("TR").build();
//			LanguageOption languageOption2  = LanguageOption.builder()
//					.language("EN").build();
//			LanguageOption languageOption3  = LanguageOption.builder()
//					.language("AR Kurgu").build();
//			LanguageOption languageOption4  = LanguageOption.builder()
//					.language("FR").build();
//
//
//			languageOptionRepository.save(languageOption1);
//			languageOptionRepository.save(languageOption2);
//			languageOptionRepository.save(languageOption3);
//			languageOptionRepository.save(languageOption4);
//
//
//		};
//	}
}
