package com.devanle.dog_graphql_project.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.devanle.dog_graphql_project.entity.Dog;
import com.devanle.dog_graphql_project.repo.DogRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Iterable<String> findAllBreeds(){
        Iterable<Dog> dogs = dogRepository.findAll();
        List<String> breeds = new ArrayList<>();
        for(Dog dog: dogs){
            breeds.add(dog.getBreed());
        }
        return breeds;
    }
    public Iterable<String> findAllNames(){
        Iterable<Dog> dogs = dogRepository.findAll();
        List<String> names = new ArrayList<>();
        for(Dog dog: dogs){
            names.add(dog.getName());
        }
        return names;
    }

    public String findDogBreedById(Long id){
        Boolean contain = dogRepository.findById(id).isPresent();
        if(contain){
            Dog dog = dogRepository.findById(id).get();
            return dog.getBreed();
        }
        return "Dog doesn't exist";
    }

}
