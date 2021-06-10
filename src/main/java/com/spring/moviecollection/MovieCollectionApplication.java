package com.spring.moviecollection;


import com.spring.moviecollection.model.Category;
import com.spring.moviecollection.model.LanguageOption;
import com.spring.moviecollection.repository.CategoryRepository;
import com.spring.moviecollection.repository.LanguageOptionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieCollectionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieCollectionApplication.class, args);
	}

	private final CategoryRepository categoryRepository;
	private final LanguageOptionRepository languageOptionRepository;

	public MovieCollectionApplication(CategoryRepository categoryRepository, LanguageOptionRepository languageOptionRepository) {
		this.categoryRepository = categoryRepository;
		this.languageOptionRepository = languageOptionRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Category category1 = Category.builder()
				.id(1L)
				.categoryName("Korku").build();
		Category category2 = Category.builder()
				.id(2L)
				.categoryName("Gerilim").build();
		Category category3 = Category.builder()
				.id(3L)
				.categoryName("Bilim Kurgu").build();
		Category category4 = Category.builder()
				.id(4L)
				.categoryName("Macera").build();
		Category category5 = Category.builder()
				.id(5L)
				.categoryName("Aksiyon").build();
		Category category6 = Category.builder()
				.id(6L)
				.categoryName("Komedi").build();

		categoryRepository.save(category1);
		categoryRepository.save(category2);
		categoryRepository.save(category3);
		categoryRepository.save(category4);
		categoryRepository.save(category5);
		categoryRepository.save(category6);

		LanguageOption languageOption1 = LanguageOption.builder()
				.id(1L)
				.language("TR").build();
		LanguageOption languageOption2  = LanguageOption.builder()
				.id(2L)
				.language("EN").build();
		LanguageOption languageOption3  = LanguageOption.builder()
				.id(3L)
				.language("AR").build();
		LanguageOption languageOption4  = LanguageOption.builder()
				.id(4L)
				.language("FR").build();


		languageOptionRepository.save(languageOption1);
		languageOptionRepository.save(languageOption2);
		languageOptionRepository.save(languageOption3);
		languageOptionRepository.save(languageOption4);
	}
}

