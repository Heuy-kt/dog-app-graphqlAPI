package com.devanle.dog_graphql_project.mutator;

import com.devanle.dog_graphql_project.entity.Dog;
import com.devanle.dog_graphql_project.repo.DogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Mutation {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog newDog(String name, String breed, String owner){
        Dog newDog = new Dog("Bingo", "Local", "James");
        dogRepository.save(newDog);
        return newDog;
    }

    public boolean deleteDog(Long id){
        boolean isPresent = dogRepository.findById(id).isPresent();
        if(isPresent){
            dogRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String updateDogOwner(Long id, String newOwner){
        Optional<Dog> dog = dogRepository.findById(id);
        dog.get().setOwner(newOwner);
        return dog.get().getOwner();
    }

    public List<Dog> deleteDogBreed(String breed){
        Iterable<Dog> dogs= dogRepository.findAll();
        for(Dog dog: dogs){
            if(dog.getBreed() == breed){
                dogRepository.delete(dog);
            }
        }
        return List.of((Dog)dogRepository.findAll());
    }
}
