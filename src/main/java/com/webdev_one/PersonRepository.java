package com.webdev_one;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findBylastnameStartsWithIgnoreCase(String lastname);
}
