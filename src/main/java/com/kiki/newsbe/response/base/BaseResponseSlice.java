package com.kiki.newsbe.response.base;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseResponseSlice {
    private Boolean isFirst;
    private Boolean isLast;
    private Boolean hasNext;
    private Boolean success;
    private Object data;
    private int page;
    private int size;
    private int total;
}
