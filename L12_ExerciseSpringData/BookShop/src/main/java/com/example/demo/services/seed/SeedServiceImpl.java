package com.example.demo.services.seed;

import com.example.demo.domain.entities.Author;
import com.example.demo.domain.entities.Book;
import com.example.demo.domain.entities.Category;
import com.example.demo.domain.enums.AgeRestriction;
import com.example.demo.domain.enums.EditionType;
import com.example.demo.services.author.AuthorService;
import com.example.demo.services.book.BookService;
import com.example.demo.services.category.CategoryService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.Constants.FilePath.*;

@Component
public class SeedServiceImpl implements SeedService {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;


    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {

        if (!this.authorService.isDataSeeded()) {
            this.authorService.seedAuthors(Files.readAllLines(Path.of(RESOURCE_URL + AUTHOR_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(s -> {
                        String[] firstAndLastName = s.split(" ");
                        String firstName = firstAndLastName[0];
                        String lastName = firstAndLastName[1];
                        Author author = new Author(firstName, lastName);
                        return author;
                    })
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedBooks() throws IOException {
        if (!this.bookService.isDataSeeded()) {
            List<Book> books = Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(line -> {
                        String[] data = line.split("\\s+");

                        Author author = this.authorService.getRandomAuthor();

                        EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

                        LocalDate releaseDate = LocalDate.parse(data[1],
                                DateTimeFormatter.ofPattern("d/M/yyyy"));

                        int copies = Integer.parseInt(data[2]);

                        BigDecimal price = new BigDecimal(data[3]);

                        AgeRestriction ageRestriction = AgeRestriction
                                .values()[Integer.parseInt(data[4])];

                        String title = Arrays.stream(data)
                                .skip(5)
                                .collect(Collectors.joining(" "));

                        Set<Category> categories = categoryService.getRandomCategories();

                        Book book = new Book(title, editionType, price, releaseDate,
                                ageRestriction, author, categories, copies);

                        return book;
                    }).collect(Collectors.toList());

            this.bookService.seedBooks(books);
        }
    }

    @Override
    public void seedCategory() throws IOException {

        if (!this.categoryService.isDataSeeded()) {
            this.categoryService.seedCategories(Files.readAllLines(Path.of(RESOURCE_URL + CATEGORY_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(name -> {
                        Category category = new Category(name);
                        return category;
                    }).collect(Collectors.toList()));
        }
    }
}
