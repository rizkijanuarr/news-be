package com.kiki.newsbe.services.v1.impl;

import com.kiki.newsbe.repositories.PostViewsRepository;
import com.kiki.newsbe.repositories.entities.PostEntity;
import com.kiki.newsbe.repositories.entities.PostViewsEntity;
import com.kiki.newsbe.response.v1.PostResponseV1;
import com.kiki.newsbe.response.v1.PostViewsResponseV1;
import com.kiki.newsbe.services.v1.PostViewsServiceV1;
import com.kiki.newsbe.utils.message.MessageLib;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostViewsServiceImplV1 implements PostViewsServiceV1 {

    private final PostViewsRepository postViewsRepository;
    private final MessageLib messageLib;

    @Override
    public List<PostViewsResponseV1> getListPostViews() {
        List<PostViewsEntity> postViewsList = postViewsRepository.findAll();
        List<PostViewsResponseV1> responses = new ArrayList<>();
        for (PostViewsEntity postView : postViewsList) {
            responses.add(responses(postView));
        }
        return responses;
    }

    private PostViewsResponseV1 responses(PostViewsEntity entity) {
            return PostViewsResponseV1.builder()
                    .id(entity.getId())
                    .post(PostViewsResponseV1.PostResponse.builder()
                            .id(entity.getPost_id().getId())
                            .title(entity.getPost_id().getTitle())
                            .slug(entity.getPost_id().getSlug())
                            .content(entity.getPost_id().getContent())
                            .content(entity.getPost_id().getImage())
                            .build()
                    )
                    .views(entity.getViews())
                    .active(entity.getActive())
                    .createdDate(entity.getCreatedDate())
                    .modifiedDate(entity.getModifiedDate())
                    .deletedDate(entity.getDeletedDate())
                    .deletedBy(entity.getDeletedBy())
                    .modifiedBy(entity.getModifiedBy())
                    .build();
    }
}
