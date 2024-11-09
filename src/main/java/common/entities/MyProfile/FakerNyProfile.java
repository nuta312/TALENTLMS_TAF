package common.entities.MyProfile;

import com.github.javafaker.Faker;

public class FakerNyProfile {
private static Faker faker = new Faker();

public static String randomFirstName () {
    return faker.name().firstName();
}

    public static String randomLastName () {
        return faker.name().lastName();
    }

    public static String randomEmail () {
        return faker.internet().emailAddress();
    }

    public static String randomBio() {
        return faker.lorem().sentence();
    }

    public static String randomUserName () {
        return faker.name().username();
    }

    public static MyProfile randomField () {
    String firstName = randomFirstName();
    String lastName = randomLastName();
    String email = randomEmail();
    String bio = randomBio();
    String userName = randomUserName();
   MyProfile myProfile = new MyProfile(firstName,lastName,email,bio,userName);
   return myProfile;
    }

}
