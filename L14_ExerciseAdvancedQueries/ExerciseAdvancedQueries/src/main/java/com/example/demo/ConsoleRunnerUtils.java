package com.example.demo;

import com.example.demo.domain.entities.Book;
import com.example.demo.domain.enums.AgeRestriction;
import com.example.demo.domain.enums.EditionType;
import com.example.demo.services.author.AuthorService;
import com.example.demo.services.book.BookService;
import com.example.demo.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

@Component
public class ConsoleRunnerUtils {

    private final LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000, 1, 1);
    final private LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990, 1, 1);
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunnerUtils(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    private void getAllBooksAfterGivenYear() {
        this.bookService
                .findAllByReleaseDateAfter(BOOK_YEAR_AFTER)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void getAllAuthorsWithBooksReleaseDateBefore() {
        this.authorService
                .findDistinctByBooksBefore(BOOK_YEAR_BEFORE)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }


    private void findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc() {
        this.bookService.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
                .forEach(b -> System.out.println(b.getTitle() + " "
                        + b.getReleaseDate() + " "
                        +b.getCopies()));
    }

    public void booksTitlesByAgeRestriction(String ageRestrictionType) {

        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionType.toUpperCase());

        this.bookService.findAllBooksByAgeRestriction(ageRestriction)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    public void goldenBookWithLessThan5000Copies() {
        this.bookService.findAllBooksByEditionTypeAndCopies(EditionType.GOLD, 5000)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    public void booksWithPriceLessAndPriceOver() {
        this.bookService.findAllBooksWithPriceLessThanAndPriceOver(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .forEach(book -> System.out.println(book.getTitle() + " - " + book.getPrice()));
    }

    public void findBooksNotReleasedIn() {
        this.bookService.findAllBooksNotReleasedIn(LocalDate.of(2000, 1, 1))
                .forEach(book -> System.out.println(book.getTitle()));
    }

    public void booksReleasedBeforeDate() {
        this.bookService.findAllByReleaseDateBefore(LocalDate.of(1992, 4, 12))
                .stream()
                .map(Book::getBookTitleEditionTypeAndPriceFormat)
                .forEach(System.out::println);
    }

    public void authorsByFirstNameEndingWith(String textToMatch) {

        this.authorService.findAllByFirstNameEndsWith(textToMatch)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    public void findBooksByTitleContaining(String textToMatch) {
        this.bookService.findAllByTitleContaining(textToMatch)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    public void findBooksWithAuthorLastNameStartingWith(String textToMatch) {
        this.bookService.findAllByAuthorLastNameStartingWith(textToMatch)
                .forEach(book -> System.out.println(book.getTitle() + " (" + book.getAuthorFullName() + ")"));
    }

    public void findCountOfBooksWithTitleNameLongerThan(Integer length) {
        System.out.println(this.bookService.findAllBooksWithTitleNameLongerThan(length));
    }

    public void getCountCopiesOfBooksFromAuthor(String firstName, String lastName) {
        int countCopies = this.authorService.findAuthorByFirstNameAndLastName(firstName, lastName)
                .getBooks().stream().mapToInt(Book::getCopies).sum();

        System.out.println(countCopies);
    }

    public void increaseBookCopiesAndPrintTotalCopies(String dateInfo, int copies) {

        String date = dateInfo.replaceAll(" ", "-");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);

        int countBooksAffected = this.bookService.increaseBookCopies(parsedDate, copies);

        System.out.println(countBooksAffected * copies);
    }

    public void deleteBooksWithCopiesLessThan(int copies) {
        System.out.println(this.bookService.deleteAllByCopiesLessThan(copies));
    }
}
