package com.example.demo.services.book;

import com.example.demo.domain.dto.BookInformation;
import com.example.demo.domain.entities.Book;
import com.example.demo.domain.enums.AgeRestriction;
import com.example.demo.domain.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks(List<Book> books);

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllBooksByEditionTypeAndCopies(EditionType editionType, int copies);

    List<Book> findAllBooksWithPriceLessThanAndPriceOver(BigDecimal priceUnder, BigDecimal priceOver);

    List<Book> findAllBooksNotReleasedIn(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByTitleContaining(String textToMatch);

    List<Book> findAllByAuthorLastNameStartingWith(String textToMatch);

    Integer findAllBooksWithTitleNameLongerThan(Integer length);

    Integer findTotalCopiesOfBookByAuthor(String firstName, String lastName);

    BookInformation findFirstByTitle(String title);

    int increaseBookCopies(LocalDate date, int amount);

    int deleteAllByCopiesLessThan(Integer copies);
}
