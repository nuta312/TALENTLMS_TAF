package ui.helper;

import com.github.javafaker.Faker;

import java.util.Random;

public class RandomDataGenerator {
    Faker faker = new Faker();

    public int generateNumber(int max) {
        return faker.random().nextInt(1, max);
    }
}
