package com.kodo.exercise.adapter.repository.codec;

import com.kodo.exercise.adapter.repository.Entity.FeedEntity;
import com.kodo.exercise.domain.FeedState;
import org.springframework.stereotype.Component;

@Component
public class FeedStateAdapter {
  public FeedEntity encode(FeedState feedState) {
    return FeedEntity.builder()
        .name(feedState.getName().toString())
        .image(feedState.getImage().toString())
        .description(feedState.getDescription().getDescription())
        .dateLastEdited(feedState.getDateLastEdited().getDateLastEdited())
        .build();
  }
}
