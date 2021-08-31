package Controller;

import Model.Person;
import Repository.PersonRepo;

import java.util.ArrayList;
import java.util.List;

public class PersonController {

    List<Person> persons;
    PersonRepo personRepo;

    public PersonController(){
        persons=new ArrayList<>();
        personRepo=new PersonRepo();
        loadRepo();
    }

    public Person getPersonByUsername(String username){
        for(Person person:persons){
            if(person.getUsername().equals(username))
                return person;
        }

        return null;
    }

    public boolean validUsername(String username) {
        for (Person person : persons) {
            if (person.getUsername().equals(username))
                return false;
        }

        return true;
    }

    public boolean checkCredentials(String username, String password){
        for(Person person:persons){
            if(person.getUsername().equals(username) && person.getPassword().equals(password))
                return true;
        }

        return false;
    }

    //CRUD

    public boolean addPerson(Person person){
        if(validUsername(person.getUsername())) {
            personRepo.insertPerson(person);
            return true;
        }
        return false;
    }

    public void removePerson(String username, String password){
        personRepo.deletePerson(getPersonByUsername(username));
    }

    public void updateFirstName(String username, String newFirstName){
        personRepo.updateFirstName(getPersonByUsername(username),newFirstName);
    }

    public void updateLastName(String username, String newLastName){
        personRepo.updateLastName(getPersonByUsername(username),newLastName);
    }

    public void updateUsername(String username, String newUsername){
        personRepo.updateUsername(getPersonByUsername(username),newUsername);
    }

    public void updatePassword(String username, String newPassword){
        personRepo.updatePassword(getPersonByUsername(username),newPassword);
    }

    public void display(){
        for(Person person:persons){
            System.out.println(person);
        }
    }

    public void loadRepo(){
        persons=personRepo.getAllPerson();
    }
}
