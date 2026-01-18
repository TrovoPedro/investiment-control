package com.investment.control.investment_control_microservice_person.repository;

import com.investment.control.investment_control_microservice_person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
}
