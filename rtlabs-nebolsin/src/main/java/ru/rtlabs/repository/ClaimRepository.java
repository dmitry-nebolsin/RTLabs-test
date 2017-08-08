package ru.rtlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.rtlabs.entity.Claim;

public interface ClaimRepository extends MongoRepository<Claim,String> {
}
