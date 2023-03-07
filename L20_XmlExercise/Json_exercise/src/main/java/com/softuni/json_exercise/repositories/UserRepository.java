package com.softuni.json_exercise.repositories;

import com.softuni.json_exercise.domain.entities.Category;
import com.softuni.json_exercise.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from `product_shop`.users ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Optional<User> getRandomEntity();


    Optional<List<User>> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();
}
