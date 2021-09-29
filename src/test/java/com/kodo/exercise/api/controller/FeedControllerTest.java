package com.kodo.exercise.api.controller;

import static org.mockito.Mockito.*;

import com.kodo.exercise.domain.command.CreateFeed;
import com.kodo.exercise.domain.service.FeedService;
import com.kodo.exercise.message.FeedMessage;
import com.kodo.exercise.util.TestDataBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class FeedControllerTest {
  private final FeedService feedService = mock(FeedService.class);
  private final FeedController feedController = new FeedController(feedService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(feedController).build();

  @Test
  void shouldSeedFeedData() {
    List<FeedMessage> feeds = new ArrayList<>();
    List<CreateFeed> createFeedList = new ArrayList<>();

    feeds.add(TestDataBuilder.randomFeedMessageBuilder().build());
    for (FeedMessage feed : feeds) {
      createFeedList.add(FeedMessage.toCreateFeedCommand(feed));
    }
    feedController.storeFeedData(feeds);
    verify(feedService).storeFeedData(createFeedList);
  }

  @Test
  void shouldSearchFeedData() {
    String textSearch = "King";
    Pageable pageable = PageRequest.of(0, 10);

    feedController.searchFeeds(textSearch, pageable);

    verify(feedService).fetchFeeds(textSearch, pageable);
  }

  //  @Test
  //  public void checkUrlWithForwarded() throws Exception {
  //    String textSearch = "King";
  //    Pageable pageable = PageRequest.of(0, 10);
  //    List<FeedEntity> feedEntityList =
  //            List.of(FeedEntity.builder().build());
  //    Page pageResult = new PageImpl(List.of(feedEntityList));
  //    when(feedService.fetchFeeds(textSearch,pageable))
  //            .thenReturn(pageResult);
  //
  //    var buu =
  //            mockMvc
  //                    .perform(
  //
  // MockMvcRequestBuilders.get("/api/v1/search/feeds?name=King&page=0&size=10")
  //                                    .header("x-forwarded-host", "domain")
  //                                    .header("x-forwarded-proto", "https")
  //                                    .header("x-forwarded-prefix", "prefix")
  //                                    .contentType(MediaType.APPLICATION_JSON)
  //                                    .accept(MediaType.APPLICATION_JSON))
  //                    .andExpect(MockMvcResultMatchers.status().isOk())
  //                    .andDo(print())
  //                    .andExpect(
  //                            jsonPath(
  //                                    "$.links.self",
  //                                    org.hamcrest.Matchers.containsString(
  //
  // "https://domain/prefix/api/v1/search/feeds?name=King&page=0&size=10")));
  //  }
}
