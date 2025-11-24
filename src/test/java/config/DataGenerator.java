package config;
import com.github.javafaker.Faker;

public class DataGenerator {

   private String email;
   private String password;
   private Faker faker = new Faker();

   public String generateEmail(){
       email = faker.internet().emailAddress();
       return email;
   }

    public String generatePassword(){
        password = faker.internet().emailAddress();
        return password;
    }
}
