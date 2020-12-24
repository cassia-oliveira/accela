package com.accela.codingExercise;

import com.accela.codingExercise.model.Person;
import com.accela.codingExercise.service.PersonService;
import com.accela.codingExercise.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CodingExerciseApplication implements ApplicationRunner {

	@Autowired
	PersonService service;

	static private String MENU_LIST = "Choose the option:\n" +
										"1 - Add a person \n" +
										"2 - Edit a person \n" +
										"3 - Delete a person \n" +
										"4 - Cont number of people \n" +
										"5 - List people \n" +
										"6 - Exit";

	public static void main(String[] args) {
		SpringApplication.run(CodingExerciseApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Boolean keep = true;
		String menu;
		Integer option = 0;
		Scanner scanIn = new Scanner(System.in);
		while(keep) {
			System.out.println(MENU_LIST);

			menu = scanIn.nextLine();

			if(Utils.isInteger(menu)){
				option = Integer.parseInt(menu);
			} else{
				System.out.println("ERROR: Type the option number");
				continue;
			}
			switch (option) {
				case 1: addPerson();
					break;
				case 2: updatePerson();
					break;
				case 3: deletePerson();
					break;
				case 4: countPerson();
					break;
				case 5: listPerson();
					break;
				case 6: keep = false;
					break;
				default: System.out.println("ERROR: Type the option number");
					break;
			}
		}
		scanIn.close();
	}

	public void addPerson(){
		Person person = new Person();
		System.out.print("First name: ");
		Scanner scanIn = new Scanner(System.in);
		person.setName(scanIn.nextLine());

		System.out.print("Surname: ");
		person.setSurname(scanIn.nextLine());
		try {
			System.out.println(service.addPerson(person) + "\nSuccessfully added!");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updatePerson(){
		Person person = new Person();
		Scanner scanIn = new Scanner(System.in);
		Boolean keep = true;
		while (keep) {
			System.out.println("Choose the person and type the id:\n" +
					service.listPerson()
			);
			String id = scanIn.nextLine();
			if (Utils.isInteger(id)) {
				person.setId(Integer.parseInt(id));
				System.out.print("First name: ");
				person.setName(scanIn.nextLine());

				System.out.print("Surname: ");
				person.setSurname(scanIn.nextLine());
				try{
					System.out.println(service.updatePerson(person) + "\nSuccessfully edited!");
					keep = false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("ERROR: Type the id number");
			}


		}
	}

	public void deletePerson(){
		Scanner scanIn = new Scanner(System.in);
		Boolean keep = true;
		while (keep) {
			System.out.println("Choose the person:\n" +
					service.listPerson()
			);

			String id = scanIn.nextLine();
			if (Utils.isInteger(id)) {
				try{
					service.deletePerson(Integer.parseInt(id));
					System.out.println("Successfully deleted!");
					keep = false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("ERROR: Type the id number");
			}
		}
	}

	public void countPerson(){
		System.out.println("There are " + service.listPerson().size() + " people.");
	}

	public void listPerson(){
		System.out.println(service.listPerson());
	}
}
