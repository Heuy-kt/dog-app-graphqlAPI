package com.devanle.dog_graphql_project.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.devanle.dog_graphql_project.entity.Dog;
import com.devanle.dog_graphql_project.exceptions.DogNotFoundException;
import com.devanle.dog_graphql_project.repo.DogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DogMutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public DogMutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog newDog(String name, String breed, String owner){
        Dog newDog = new Dog("Bingo", "Local", "James");
        dogRepository.save(newDog);
        return newDog;
    }

    public Boolean deleteDog(Long id){
        boolean isPresent = dogRepository.findById(id).isPresent();
        if(isPresent){
            dogRepository.deleteById(id);
            return true;
        }
        throw new DogNotFoundException("Dog id not found", id);
    }

    public String updateDogOwner(Long id, String newOwner){
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent()){
            dog.get().setOwner(newOwner);
            return dog.get().getOwner();
        }else
            throw new DogNotFoundException("Dog doesnt exist", id);
    }

    public List<Dog> deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> dogs= dogRepository.findAll();
        for(Dog dog: dogs){
            if(dog.getBreed() == breed){
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        if(!deleted)
            throw new DogNotFoundException("Breed doesnt exist among list of dogs", breed);
        return List.of((Dog)dogRepository.findAll());
    }
}
