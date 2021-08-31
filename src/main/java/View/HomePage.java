package View;

import Controller.PersonController;
import Model.Person;

import java.util.Scanner;

public class HomePage {

    private Scanner keyboard;
    private PersonController personController;
    private LoggedView loggedView;


    public HomePage(){
        keyboard=new Scanner(System.in);
        personController=new PersonController();
        loggedView=new LoggedView();
    }

    public void homeMenu(){
        System.out.println("--------WELCOME--------");
        System.out.println("1.Login");
        System.out.println("2.Create account");
        System.out.println("3.Exit");
    }

    public void play(){
        homeMenu();

        boolean logged=true;

        while(logged){
            int choice=Integer.parseInt(keyboard.nextLine());
            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Thank you for using the app!");
                    logged=false;
                    break;
            }
        }
    }

    public void login(){
        String username;
        String password;

        System.out.println("Enter username:");
        username=keyboard.nextLine();
        System.out.println("Enter password:");
        password=keyboard.nextLine();

        if(personController.checkCredentials(username,password)){
            loggedView.play(username);
        }else{
            System.out.println("Username or password incorrect!");
            login();
        }
    }

    public void createAccount(){
        String firstName;
        String lastName;
        String username=null;
        String password;
        boolean validUsername=false;

        System.out.println("Enter first name:");
        firstName=keyboard.nextLine();

        System.out.println("Enter last name:");
        lastName=keyboard.nextLine();

        while(!validUsername) {
            System.out.println("Enter username:");
            username = keyboard.nextLine();
            if (personController.validUsername(username)) {
                validUsername = true;
            } else {
                System.out.println("Username is taken!");
            }
        }
        System.out.println("Enter password:");
        password=keyboard.nextLine();

        if(username!=null) {
            Person person = new Person(firstName, lastName, username, password);
            personController.addPerson(person);
        }else{
            System.out.println("Account not created!");
        }

    }
}
