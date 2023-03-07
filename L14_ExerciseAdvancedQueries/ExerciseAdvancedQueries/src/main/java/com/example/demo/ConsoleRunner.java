package com.example.demo;

import com.example.demo.domain.entities.Book;
import com.example.demo.services.author.AuthorService;
import com.example.demo.services.book.BookService;
import com.example.demo.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final BookService bookService;
    private final AuthorService authorService;
    private final Scanner scanner;
    private final SeedService seedService;

    private final ConsoleRunnerUtils consoleRunnerUtils;

    @Autowired
    public ConsoleRunner(BookService bookService, AuthorService authorService, SeedService seedService, ConsoleRunnerUtils consoleRunnerUtils) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.seedService = seedService;
        this.consoleRunnerUtils = consoleRunnerUtils;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws IOException {

//        this.seedService.seedAllData();

        String dateInfo = scanner.nextLine();
        int copies = scanner.nextInt();

        this.consoleRunnerUtils.increaseBookCopiesAndPrintTotalCopies(dateInfo, copies);

    }


}
