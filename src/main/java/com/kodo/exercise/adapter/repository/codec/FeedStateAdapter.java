package com.kodo.exercise.adapter.repository.codec;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.domain.FeedState;
import com.kodo.exercise.domain.value.DateLastEdited;
import com.kodo.exercise.domain.value.Description;
import com.kodo.exercise.domain.value.Image;
import com.kodo.exercise.domain.value.Name;
import org.springframework.stereotype.Component;

@Component
public class FeedStateAdapter {
  public FeedEntity encode(FeedState feedState) {
    return FeedEntity.builder()
        .name(feedState.getName().getName())
        .image(feedState.getImage().getImage())
        .description(feedState.getDescription().getDescription())
        .dateLastEdited(feedState.getDateLastEdited().getDateLastEdited())
        .build();
  }

  public FeedState decode(FeedEntity feedEntity) {
    return FeedState.builder()
        .name(Name.create(feedEntity.getName()))
        .image(Image.create(feedEntity.getImage()))
        .description(Description.create(feedEntity.getDescription()))
        .dateLastEdited(DateLastEdited.create(feedEntity.getDateLastEdited()))
        .build();
  }
}
