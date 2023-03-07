package com.example.demo.services.book;

import com.example.demo.domain.dto.BookInformation;
import com.example.demo.domain.entities.Book;
import com.example.demo.domain.enums.AgeRestriction;
import com.example.demo.domain.enums.EditionType;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public List<Book> findAllByReleaseDateAfter(LocalDate localDate) {
        return this.bookRepository.findAllByReleaseDateAfter(localDate).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findBooksByAgeRestriction(ageRestriction).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllBooksByEditionTypeAndCopies(EditionType editionType, int copies) {
        return this.bookRepository.findBooksByEditionTypeAndCopiesLessThan(editionType, copies).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllBooksWithPriceLessThanAndPriceOver(BigDecimal priceUnder, BigDecimal priceOver) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(priceUnder, priceOver).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllBooksNotReleasedIn(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateIsNot(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByTitleContaining(String textToMatch) {
        return this.bookRepository.findAllByTitleContainingIgnoreCase(textToMatch).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String textToMatch) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(textToMatch).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer findAllBooksWithTitleNameLongerThan(Integer length) {
        return this.bookRepository.findAllBooksWithTitleNameLongerThan(length).orElse(0);
    }

    @Override
    public Integer findTotalCopiesOfBookByAuthor(String firstName, String lastName) {
        return this.bookRepository.findTotalCopiesOfBookByAuthor(firstName, lastName).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public BookInformation findFirstByTitle(String title) {
        BookInformation bookInformation = this.bookRepository
                .findFirstByTitle(title)
                .orElseThrow(NoSuchElementException::new);

        System.out.println(bookInformation.toString());
        return bookInformation;
    }

    @Override
    @Transactional
    public int increaseBookCopies(LocalDate date, int amount) {
        return this.bookRepository.increaseBookCopies(date, amount);
    }

    @Override
    public int deleteAllByCopiesLessThan(Integer copies) {
        return this.bookRepository.deleteAllByCopiesLessThan(copies);
    }
}
