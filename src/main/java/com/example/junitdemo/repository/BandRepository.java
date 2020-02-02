package com.example.junitdemo.repository;

import com.example.junitdemo.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BandRepository extends JpaRepository<Band, Integer> {

    @Query(
        value =
            "SELECT " +
                "CAST(AVG(creation_year) AS INTEGER) " +
            "FROM " +
                "band",
        nativeQuery = true
    )
    Integer getCreationYearAverage();

    Band findByName(String name);
}
