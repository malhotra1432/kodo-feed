package com.kodo.exercise.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodo.exercise.adapter.repository.jpa.FeedJpaRepository;
import com.kodo.exercise.config.GlobalConfig;
import com.kodo.exercise.util.TestDataBuilder;
import java.util.List;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@Tag("integrationTest")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class FeedIntegrationTest {
  private ClientAndServer mockServer;
  private final ObjectMapper objectMapper = GlobalConfig.createObjectMapper();
  @Autowired private MockMvc mockMvc;
  @Autowired private FeedJpaRepository feedJpaRepository;

  public static String asJsonString(final Object obj) {
    try {
      return GlobalConfig.createObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeAll
  void setUpServer() {
    mockServer = ClientAndServer.startClientAndServer(8092);
  }

  @AfterAll
  void tearDown() {
    mockServer.stop();
  }

  @BeforeEach
  void setUp() {
    feedJpaRepository.deleteAll();
  }

  @Test
  public void shouldSimulateGetFeedApiAndReturnIsOkStatus() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    "/api/v1/search/feeds?page=0&size=10&sort=dateLastEdited&text=King")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldSimulatePostFeedApiAndReturnIsAcceptedStatus() throws Exception {
    var feedMessageBuilder = TestDataBuilder.randomFeedMessageBuilder();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/seed/feeds")
                .content(asJsonString(List.of(feedMessageBuilder.build())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }
}
