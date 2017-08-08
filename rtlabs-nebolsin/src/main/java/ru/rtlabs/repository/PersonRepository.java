package ru.rtlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtlabs.entity.Person;

/**Репозиторий mongoDB для заявителей*/
public interface PersonRepository extends MongoRepository<Person,String> {
}
