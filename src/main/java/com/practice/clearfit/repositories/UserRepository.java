package com.practice.clearfit.repositories;

import com.practice.clearfit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Long aLong);
}
