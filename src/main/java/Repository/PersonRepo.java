package Repository;

import Model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepo {

    private String jdbcURL="jdbc:mysql://localhost:3306/rezervari_hotel";

    private String username="root";
    private String password="root";

    private Connection con=null;
    private Statement statement=null;

    public PersonRepo(){
        try{
            con= DriverManager.getConnection(jdbcURL,username,password);
            statement=con.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void executeStatement(String query){
        try {
            statement.execute(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getPersonID(Person person){
        int ID=0;
        String query=String.format("SELECT person_id FROM person WHERE username='%s'",person.getUsername());
        try {
            ResultSet result = statement.executeQuery(query);
            if(result.next())
                ID=result.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return ID;
    }

    public void insertPerson(Person person){

        String insert=String.format("INSERT INTO person (first_name,last_name,username,password) values ('%s','%s','%s','%s')",person.getFirst_name(),person.getLast_name(),person.getUsername(),person.getPassword());
        executeStatement(insert);
    }

    public void deletePerson(Person person){

        String delete=String.format("DELETE FROM person WHERE person_id=%d",getPersonID(person));
        executeStatement(delete);
    }

    public void updateFirstName(Person person, String newFirstName){

        String updateFirstName=String.format("UPDATE person SET first_name='%s' WHERE person_id=%d",newFirstName,getPersonID(person));
        executeStatement(updateFirstName);
    }

    public void updateLastName(Person person, String newLastName){

        String updateLastName=String.format("UPDATE person SET last_name='%s' WHERE person_id=%d",newLastName,getPersonID(person));
        executeStatement(updateLastName);
    }

    public void updateUsername(Person person, String newUsername){

        String updateUsername=String.format("UPDATE person SET username='%s WHERE person_id=%d'",newUsername,getPersonID(person));
        executeStatement(updateUsername);
    }

    public void updatePassword(Person person, String newPassword){

        String updatePassword=String.format("UPDATE person SET password='%s' WHERE person_id=%d",newPassword,getPersonID(person));
    }

    public ResultSet allPerson(){
        executeStatement("SELECT * FROM person");
        try{
            return statement.getResultSet();
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Person> getAllPerson(){
        ResultSet set=allPerson();
        List<Person> people= new ArrayList<>();

        try{
            while(set.next()){
                people.add(new Person(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return people;
    }


}
