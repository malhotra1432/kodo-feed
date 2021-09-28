package com.kodo.exercise.domain;

import com.kodo.exercise.domain.value.DateLastEdited;
import com.kodo.exercise.domain.value.Description;
import com.kodo.exercise.domain.value.Image;
import com.kodo.exercise.domain.value.Name;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class FeedState {
  @NonNull private Name name;
  @NonNull private Image image;
  @NonNull private Description description;
  @NonNull private DateLastEdited dateLastEdited;
}
