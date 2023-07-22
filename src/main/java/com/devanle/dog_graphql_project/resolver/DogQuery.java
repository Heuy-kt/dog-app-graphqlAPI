package com.devanle.dog_graphql_project.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.devanle.dog_graphql_project.entity.Dog;
import com.devanle.dog_graphql_project.exceptions.DogNotFoundException;
import com.devanle.dog_graphql_project.repo.DogRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DogQuery implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public DogQuery(DogRepository dogRepository) {
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
    public Iterable<String> findAllDogNames(){
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
        throw new DogNotFoundException("Dog id doesnt exist", id);
    }

    public Iterable<Dog> findDogBreeds(String breed){
        boolean exist = false;
        Iterable<Dog> dogs = dogRepository.findAll();
        List<Dog> breeds = new ArrayList<>();
        for(Dog dog: dogs){
            if(dog.getBreed() == breed){
                breeds.add(dog);
            }
        }
        if(!exist)
            throw new DogNotFoundException("Breed doesnt exist among dogs", breed);
        else
            return breeds;
    }

}
