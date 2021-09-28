package com.kodo.exercise.domain.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Name {
  @NonNull String name;

  public static Name create(String name) {
    return new Name(name);
  }
}
