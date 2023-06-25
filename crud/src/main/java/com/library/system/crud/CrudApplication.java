package com.library.system.crud;

import com.library.system.crud.model.Author;
import com.library.system.crud.model.Book;
import com.library.system.crud.repository.AuthorRepository;
import com.library.system.crud.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

	private static final Logger log = LoggerFactory.getLogger(CrudApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner test (BookRepository bookRepository, AuthorRepository authorRepository) {
		return (args) -> {
			Author author = new Author();
			authorRepository.save(author);
			Book book1 = new Book("title1", author);
			Book book2 = new Book("title2", author);
			Book book3 = new Book("title3", author);

			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);

			log.info("Samples books saved");
		};
	}
}
