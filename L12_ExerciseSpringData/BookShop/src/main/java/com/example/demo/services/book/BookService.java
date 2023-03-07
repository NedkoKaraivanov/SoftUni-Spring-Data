package com.example.demo.services.book;


import com.example.demo.domain.entities.Book;
import com.example.demo.domain.enums.AgeRestriction;

import java.time.LocalDate;
import java.util.List;


public interface BookService {

    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);
}
