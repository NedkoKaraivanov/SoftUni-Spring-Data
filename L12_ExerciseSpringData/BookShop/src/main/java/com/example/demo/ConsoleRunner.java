package com.example.demo;

import com.example.demo.services.author.AuthorService;
import com.example.demo.services.book.BookService;
import com.example.demo.services.category.CategoryService;
import com.example.demo.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000, 1, 1);
    final private LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990, 1, 1);

    private final SeedService seedService;

    private final AuthorService authorService;

    private final BookService bookService;

    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(SeedService seedService, AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.seedService = seedService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws IOException {
        this.seedService.seedAllData();
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
}
