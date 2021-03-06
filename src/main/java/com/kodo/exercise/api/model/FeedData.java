package com.kodo.exercise.api.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedData {
  private String name;
  private String image;
  private String description;
  private Instant dateLastEdited;
}
