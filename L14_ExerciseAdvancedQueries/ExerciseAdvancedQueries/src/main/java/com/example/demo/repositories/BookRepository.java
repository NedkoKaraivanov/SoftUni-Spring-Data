package com.example.demo.repositories;


import com.example.demo.domain.dto.BookInformation;
import com.example.demo.domain.entities.Author;
import com.example.demo.domain.entities.Book;
import com.example.demo.domain.enums.AgeRestriction;
import com.example.demo.domain.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllByReleaseDateAfter(LocalDate localDate);

    Optional<List<Book>> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    Optional<List<Book>> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    Optional<List<Book>> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceUnder, BigDecimal priceOver);

    Optional<List<Book>> findAllByReleaseDateIsNot(LocalDate date);

    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);

    Optional<List<Book>> findAllByTitleContainingIgnoreCase(String textToMatch);

    Optional<List<Book>> findAllByAuthorLastNameStartingWith(String textToMatch);

    @Query(value = "SELECT COUNT(b) FROM Book AS b" +
            " WHERE LENGTH(b.title) > :length")
    Optional<Integer> findAllBooksWithTitleNameLongerThan(Integer length);

    @Query(value = "SELECT SUM(b.copies) FROM Book AS b " +
            "JOIN b.author AS a " +
            "GROUP BY a.id " +
            "HAVING a.firstName = :firstName AND a.lastName = :lastName")
    Optional<Integer> findTotalCopiesOfBookByAuthor(String firstName, String lastName);

    @Query(value = "SELECT new com.example.demo.domain.dto.BookInformation(b.title, b.editionType, b.ageRestriction, b.price) " +
            " FROM Book AS b " +
            " WHERE b.title = :title")
    Optional<BookInformation> findFirstByTitle(String title);

    @Query("UPDATE Book AS b " +
            "SET b.copies = b.copies + :amount " +
            "WHERE b.releaseDate > :date")
    @Modifying
    @Transactional
    int increaseBookCopies(LocalDate date, int amount);

    @Transactional
    @Modifying
    int deleteAllByCopiesLessThan(Integer copies);
}
