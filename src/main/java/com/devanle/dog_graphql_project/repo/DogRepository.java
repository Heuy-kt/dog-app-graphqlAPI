package com.devanle.dog_graphql_project.repo;

import com.devanle.dog_graphql_project.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
