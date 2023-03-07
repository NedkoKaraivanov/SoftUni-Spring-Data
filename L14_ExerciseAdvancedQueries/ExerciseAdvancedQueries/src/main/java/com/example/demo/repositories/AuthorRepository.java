package com.example.demo.repositories;

import com.example.demo.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorById(long randomAuthorId);

    Optional<List<Author>> findDistinctByBooksReleaseDateBefore(LocalDate date);

    Optional<List<Author>> findAllByFirstNameEndsWith(String textToMatch);

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
