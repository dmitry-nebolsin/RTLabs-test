package ru.rtlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtlabs.entity.Service;

public interface ServiceRepository extends MongoRepository<Service,String> {
}
