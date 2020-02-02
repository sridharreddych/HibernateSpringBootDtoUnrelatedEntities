package com.bookstore;

import com.bookstore.dto.BookstoreDto;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
    
    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }        

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {            
            List<BookstoreDto> result = bookstoreService.fetchAuthorNameBookTitleWithPrice(42);
            
            result.forEach(r -> System.out.println("Author: " + r.getName() + "  Title: " + r.getTitle()));
        };
    }
}

/*
*
*How To Exploit Spring Projections(DTO) And Join Unrelated Entities In Hibernate 5.1+

Description: This application is a proof of concept for using Spring Projections (DTO) and join unrelated entities. Hibernate 5.1 introduced explicit joins on unrelated entities and the syntax and behaviour are similar to SQL JOIN statements.

Key points:

define serveral entities (e.g., Author and Book unrelated entities)
populate the database with some test data (e.g., check the file resources/data-mysql.sql)
write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., BookstoreDto)
write joins queries using JPQL/SQL (e.g., queries all authors names and book titles of the given price)
*/