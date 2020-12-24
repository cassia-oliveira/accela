package com.accela.codingExercise.service;

import com.accela.codingExercise.model.Person;
import com.accela.codingExercise.repository.PersonRepository;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Test
    public void addPerson() {
        Person person = getPerson(1);
        Mockito.when(repository.save(any(Person.class))).thenReturn(person);
        Person p = service.addPerson(person);
        assertEquals(person.getName(), p.getName());
        assertEquals(person.getSurname(), p.getSurname());
        assertEquals(person.getId(), p.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPersonWhenFirstNameIsEmpty() {
        Person person = getPerson(1);
        person.setName(null);
        service.addPerson(person);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPersonWhenFirstSurnameIsEmpty() {
        Person person = getPerson(1);
        person.setSurname(null);
        service.addPerson(person);
    }

    @Test
    public void updatePerson() {
        Person person = getPerson(1);
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(person));
        Mockito.when(repository.save(any(Person.class))).thenReturn(person);
        Person p = service.updatePerson(person);
        assertEquals(person.getName(), p.getName());
        assertEquals(person.getSurname(), p.getSurname());
        assertEquals(person.getId(), p.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePersonWhenPersonNotFound() {
        Person person = getPerson(1);
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.empty());
        service.updatePerson(person);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePersonWhenFirstNameIsEmpty() {
        Person person = getPerson(1);
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(person));
        person.setName(null);
        service.updatePerson(person);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePersonWhenFirstSurnameIsEmpty() {
        Person person = getPerson(1);
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(person));
        person.setSurname(null);
        service.updatePerson(person);
    }

    @Test
    public void deletePerson() {
        Person person = getPerson(1);
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(person));
        service.deletePerson(1);
        Mockito.verify(repository, Mockito.times(1)).deleteById(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deletePersonWhenPersonNotFound() {
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.empty());
        service.deletePerson(1);
    }

    @Test
    public void listPerson() {
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(getPerson(1));
        listPerson.add(getPerson(2));

        Mockito.when(repository.findAll()).thenReturn(listPerson);
        List<Person> result = service.listPerson();

        assertEquals(listPerson.get(0).getName(), result.get(0).getName());
        assertEquals(listPerson.get(0).getSurname(), result.get(0).getSurname());
        assertEquals(listPerson.get(0).getId(), result.get(0).getId());

        assertEquals(listPerson.get(1).getName(), result.get(1).getName());
        assertEquals(listPerson.get(1).getSurname(), result.get(1).getSurname());
        assertEquals(listPerson.get(1).getId(), result.get(1).getId());

    }

    private Person getPerson(Integer id){
        FakeValuesService faker = new FakeValuesService(
                new Locale("en-US"), new RandomService());
        Person person = new Person();
        person.setId(id);
        person.setName(faker.regexify("[a-z1-9]{10}"));
        person.setSurname(faker.regexify("[a-z1-9]{10}"));
        return person;
    }
}