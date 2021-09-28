package com.kodo.exercise.domain.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Description {
  @NonNull String description;

  public static Description create(String description) {
    return new Description(description);
  }
}
