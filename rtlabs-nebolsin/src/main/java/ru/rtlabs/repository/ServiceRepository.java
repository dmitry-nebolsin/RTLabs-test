package ru.rtlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtlabs.entity.Service;

/**Репозиторий mongoDB для услуг*/
public interface ServiceRepository extends MongoRepository<Service,String> {
}
