package com.kiki.newsbe.response.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kiki.newsbe.utils.date.DateUtil;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseV1 {
    private String id;
    private String title;
    private String slug;
    private String content;
    private String image;
    private CategoryResponse category;
    private UserResponse user;

    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private Date modifiedDate;
    @JsonIgnore
    private Date deletedDate;

    private String deletedBy;
    private String modifiedBy;
    private Boolean active;

    @JsonProperty("created_date")
    public String getCreatedDate() {
        return DateUtil.formatLongDateTime(createdDate);
    }
    @JsonProperty("modified_date")
    public String getModifiedDate() {
        return DateUtil.formatLongDateTime(modifiedDate);
    }
    @JsonProperty("deleted_date")
    public String getDeletedDate() {
        return DateUtil.formatLongDateTime(deletedDate);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryResponse {
        private String id;
        private String name;
        private String slug;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private String id;
        private String name;
    }

}
