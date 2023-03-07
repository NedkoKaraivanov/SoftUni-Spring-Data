package com.example.demo.services.author;

import com.example.demo.domain.entities.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAll(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        final long count = this.authorRepository.count();

        if (count != 0) {
           final long randomAuthorId = new Random().nextLong(1L, count) + 1L;

            return this.authorRepository.findAuthorById(randomAuthorId).orElseThrow(NoSuchElementException::new);
        }
        throw new RuntimeException();
    }

    @Override
    public List<Author> findDistinctByBooksBefore(LocalDate date) {
         return this.authorRepository.findDistinctByBooksReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllByFirstNameEndsWith(String textToMatch) {
        return this.authorRepository.findAllByFirstNameEndsWith(textToMatch).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Author findAuthorByFirstNameAndLastName(String firstName, String lastName) {
        return this.authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName).orElseThrow(NoSuchElementException::new);
    }
}
