package com.kodo.exercise.domain.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Image {
  @NonNull String image;

  public static Image create(String image) {
    return new Image(image);
  }
}
