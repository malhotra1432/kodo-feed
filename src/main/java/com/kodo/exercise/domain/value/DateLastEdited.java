package com.kodo.exercise.domain.value;

import java.time.Instant;
import lombok.NonNull;
import lombok.Value;

@Value
public class DateLastEdited {
  @NonNull Instant dateLastEdited;

  public static DateLastEdited create(Instant dateLastEdited) {
    return new DateLastEdited(dateLastEdited);
  }
}
