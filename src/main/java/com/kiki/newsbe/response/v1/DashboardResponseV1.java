package com.kiki.newsbe.response.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardResponseV1 {
    private Integer categories;
    private Integer posts;
    private Integer sliders;
    private Integer users;
    private PostViewsResponse postViews;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostViewsResponse {
        private List<Integer> count;
        private List<String> days;
    }
}

