package com.accela.codingExercise.service;

import com.accela.codingExercise.model.Person;
import com.accela.codingExercise.repository.PersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person addPerson(Person person){
        String empty = "";
        if(StringUtils.isEmpty(person.getName())){
            empty = "ERROR: First name can't be null.";
        }
        if(StringUtils.isEmpty(person.getSurname())){
            empty += "\nERROR: Surname can't be null.";
        }
        if(!empty.isEmpty()){
            throw new IllegalArgumentException(empty);
        }
        return repository.save(person);
    }

    public Person updatePerson(Person person){
        if(!repository.findById(person.getId()).isPresent()){
            throw new IllegalArgumentException("ERROR: Person with ID " + person.getId() + " not found");
        }
        String empty = "";
        if(StringUtils.isEmpty(person.getName())){
            empty = "ERROR: First name can't be null.";
        }
        if(StringUtils.isEmpty(person.getSurname())){
            empty += "\nERROR: Surname can't be null.";
        }
        if(!empty.isEmpty()){
            throw new IllegalArgumentException(empty);
        }
        return repository.save(person);
    }

    public void deletePerson(Integer id){
        if(!repository.findById(id).isPresent()) {
            throw new IllegalArgumentException("ERROR: Person with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    public List<Person> listPerson(){
        return repository.findAll();
    }

}
