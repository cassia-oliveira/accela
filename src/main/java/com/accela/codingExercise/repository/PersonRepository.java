package com.accela.codingExercise.repository;

import com.accela.codingExercise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {


}
