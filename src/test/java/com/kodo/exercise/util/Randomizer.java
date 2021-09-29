package com.kodo.exercise.util;

import com.github.javafaker.Faker;
import java.util.Random;
import java.util.UUID;

public class Randomizer {
  private final Random random;
  private final Faker faker;

  public Randomizer(Random random) {
    this.random = random;
    this.faker = new Faker(random);
  }

  public static Randomizer create() {
    return new Randomizer(new Random());
  }

  public Faker getFaker() {
    return faker;
  }

  public String uuid() {
    return UUID.randomUUID().toString();
  }
}
