package com.trendyol.usereventregistration.repository;

import com.trendyol.usereventregistration.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
