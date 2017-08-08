package ru.rtlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtlabs.entity.Claim;

/**Репозиторий mongoDB для заявок*/
public interface ClaimRepository extends MongoRepository<Claim,String> {
}
