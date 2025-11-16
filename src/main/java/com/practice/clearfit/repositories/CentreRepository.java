package com.practice.clearfit.repositories;

import com.practice.clearfit.models.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {
    @Override
    <S extends Centre> S save(S entity);

    @Override
    Optional<Centre> findById(Long aLong);
}
