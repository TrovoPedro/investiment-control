package com.investment.control.investment_control_microservice_person.service;

import com.investment.control.investment_control_microservice_person.dto.RegisterRequest;
import com.investment.control.investment_control_microservice_person.entity.Person;
import com.investment.control.investment_control_microservice_person.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository,
                         PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        if (personRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("email already registered\n");
        }

        Person person = new Person();
        person.setName(request.getName());
        person.setAge(request.getAge());
        person.setEmail(request.getEmail());
        person.setPassword(passwordEncoder.encode(request.getPassword()));

        personRepository.save(person);
    }
}

