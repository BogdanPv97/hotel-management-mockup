import Model.Person;
import Repository.PersonRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class PersonRepoTest {



    @Test
    public void insertPersonTest(){

        PersonRepo repo=new PersonRepo();

        for(int i=1;i<=20;i++){
            repo.insertPerson(new Person("firstName"+i,"lastName"+i,"username"+i,"password"+i));
        }
    }

    @Test
    public void getPersonIDTest(){

        PersonRepo repo=new PersonRepo();

        Person person=new Person("FirstName","LastName","Username","Password");
        //repo.insertPerson(person);
        System.out.println(repo.getPersonID(person));
        Assert.assertEquals(21,repo.getPersonID(person));
    }

    @Test
    public void deletePersonTest(){
        PersonRepo repo=new PersonRepo();

        Person person=new Person("Bogdan","Paval","aaa","bbb");
        //repo.insertPerson(person);
        repo.deletePerson(person);

    }

    @Test
    public void updateFirstName(){}

    @Test
    public void updateLastName(){}

    @Test
    public void updateUsername(){}

    @Test
    public void updatePassword(){}
}
