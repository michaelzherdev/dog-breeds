package org.mzherdev.awsdogs.repo;

import java.util.List;

import org.mzherdev.awsdogs.model.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<DogBreed, Integer>{

    List<DogBreed> findByNameContainingIgnoreCase(String name);
}
