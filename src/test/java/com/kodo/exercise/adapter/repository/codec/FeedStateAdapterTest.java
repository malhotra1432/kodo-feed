package com.kodo.exercise.adapter.repository.codec;

import static org.assertj.core.api.Assertions.assertThat;

import com.kodo.exercise.domain.FeedState;
import com.kodo.exercise.util.TestDataBuilder;
import org.junit.jupiter.api.Test;

class FeedStateAdapterTest {

  private final FeedStateAdapter feedStateAdapter = new FeedStateAdapter();

  @Test
  void shouldEncodeAndDecode() {
    FeedState feedState = TestDataBuilder.randomFeedStateBuilder().build();
    var feedEntity = feedStateAdapter.encode(feedState);
    var actualFeedState = feedStateAdapter.decode(feedEntity);
    var expectedFeedEntity = feedStateAdapter.encode(actualFeedState);

    assertThat(expectedFeedEntity).isEqualTo(feedEntity);
    assertThat(feedState).isEqualTo(actualFeedState);
  }
}
