package ru.rtlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtlabs.entity.Person;

public interface PersonRepository extends MongoRepository<Person,String> {
}
