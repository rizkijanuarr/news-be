package com.kiki.newsbe.request.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestV1 {
    private String category_id;
    private String title;
    private String content;
    private MultipartFile image;
}
