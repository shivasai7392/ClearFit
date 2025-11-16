package com.practice.clearfit.repositories;

import com.practice.clearfit.models.CentreTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreTimingRepository extends JpaRepository<CentreTiming, Long> {
    @Override
    <S extends CentreTiming> S save(S entity);
}
