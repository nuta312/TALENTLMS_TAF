package api.utils;

import api.entity.User;
import com.github.javafaker.Faker;

public class EntityManager {
    private static final Faker faker = new Faker();

    public static User generateUser() {
        faker.number().digits(3);
        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .login(faker.name().username())
                .password(faker.internet().password(10, 15, true, true, true))
                .build();
    }
}