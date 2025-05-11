package com.kiki.newsbe.services.v1.impl;

import com.kiki.newsbe.repositories.CategoryRepository;
import com.kiki.newsbe.repositories.PostRepository;
import com.kiki.newsbe.repositories.auth.UserRepository;
import com.kiki.newsbe.repositories.entities.CategoryEntity;
import com.kiki.newsbe.repositories.entities.PostEntity;
import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import com.kiki.newsbe.request.v1.PostRequestV1;
import com.kiki.newsbe.response.v1.PostResponseV1;
import com.kiki.newsbe.services.v1.PostServiceV1;
import com.kiki.newsbe.utils.exceptions.NotFoundException;
import com.kiki.newsbe.utils.generated.Slug;
import com.kiki.newsbe.utils.message.MessageLib;
import com.kiki.newsbe.utils.validation.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImplV1 implements PostServiceV1 {

    private final PostRepository postRepository;
    private final MessageLib messageLib;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<PostResponseV1> getListPosts() {
        List<PostEntity> posts = postRepository.findAll();
        List<PostResponseV1> responses = new ArrayList<>();
        for (PostEntity post : posts) {
            responses.add(responses(post));
        }
        return responses;
    }

    @Override
    public PostResponseV1 createPost(PostRequestV1 request) {
        PostEntity savedPost = setPostInDatabase(request);
        return responses(savedPost);
    }

    @Override
    public PostResponseV1 detailsPost(String id) {
        PostEntity post = findPostById(id);
        return responses(post);
    }

    @Override
    public PostResponseV1 updatePost(String id, PostRequestV1 request) {
        PostEntity updated = setPostUpdateInDatabase(id, request);
        return responses(updated);
    }

    @Override
    public PostResponseV1 deletePost(String id) {
        return responses(setPostSoftDelete(id));
    }

    @Override
    public Slice<PostResponseV1> getListPostActive(Pageable pageable) {
        Slice<PostEntity> postList = postRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable);
        List<PostResponseV1> responses = new ArrayList<>();
        for (PostEntity post : postList) {
            responses.add(responses(post));
        }

        return new SliceImpl<>(responses, pageable, postList.hasNext());
    }

    @Override
    public Slice<PostResponseV1> getListPostInactive(Pageable pageable) {
        Slice<PostEntity> postList = postRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<PostResponseV1> responses = new ArrayList<>();
        for (PostEntity post : postList) {
            responses.add(responses(post));
        }

        return new SliceImpl<>(responses, pageable, postList.hasNext());
    }

    private PostEntity setPostSoftDelete(String id) {
        UserEntity currentUser = getCurrentLoggedInUser();
        PostEntity post = findPostById(id);

        post.setDeletedDate(getModifiedDate());
        post.setDeletedBy(currentUser.getUser_email());
        post.setModifiedBy(getModifiedByDelete());
        post.setActive(false);

        return postRepository.save(post);
    }

    private PostEntity setPostUpdateInDatabase(String id, PostRequestV1 request) {
        CategoryEntity category = setCategoryRelationID(request);
        UserEntity currentUser = getCurrentLoggedInUser();
        PostEntity post = findPostById(id);

        post.setCategory_id(category);
        post.setUser_id(currentUser);
        post.setTitle(request.getTitle());
        post.setSlug(Slug.of(request.getTitle()));
        post.setContent(request.getContent());
        post.setImage(request.getImage());

        post.setModifiedBy(currentUser.getUser_name());
        post.setModifiedDate(getModifiedDate());

        return postRepository.save(post);
    }


    private Date getModifiedDate() {
        return new Date();
    }

    private String getModifiedByDelete() {
        return "SOFT DELETE";
    }


    private PostEntity findPostById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getPostNotFound()));
    }

    private PostEntity setPostInDatabase(PostRequestV1 request) {
        Validate.c(request, Map.of(
                "Kategori Tidak Dapat Kosong", PostRequestV1::getCategory_id,
                "Judul Tidak Dapat Kosong", PostRequestV1::getTitle,
                "Konten Tidak Dapat Kosong", PostRequestV1::getContent,
                "Gambar Tidak Dapat Kosong", PostRequestV1::getImage
        ));

        CategoryEntity category = setCategoryRelationID(request);
        UserEntity currentUser = getCurrentLoggedInUser();
        PostEntity post = new PostEntity();

        post.setCategory_id(category);
        post.setUser_id(currentUser);
        post.setTitle(request.getTitle());
        post.setSlug(Slug.of(request.getTitle()));
        post.setContent(request.getContent());
        post.setImage(request.getImage());

        post.setCreatedBy(currentUser.getUser_email());
        post.setCreatedDate(getCreatedDate());

        return postRepository.save(post);
    }

    private UserEntity getCurrentLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUser_email(email)
                .orElseThrow(() -> new NotFoundException(messageLib.getUserIdNotFound()));
    }

    private Date getCreatedDate() {
        return new Date();
    }

    private CategoryEntity setCategoryRelationID(PostRequestV1 request) {
        return categoryRepository.findById(request.getCategory_id())
                .orElseThrow(() -> new NotFoundException(messageLib.getCategoryNotFound()));
    }

    private PostResponseV1 responses(PostEntity entity) {
        return PostResponseV1.builder()
                .id(entity.getId())
                .category(PostResponseV1.CategoryResponse.builder()
                        .id(entity.getCategory_id().getId())
                        .name(entity.getCategory_id().getName())
                        .slug(entity.getCategory_id().getSlug())
                        .build()
                )
                .user(PostResponseV1.UserResponse.builder()
                        .id(entity.getUser_id().getId())
                        .name(entity.getUser_id().getUser_name())
                        .build()
                )
                .title(entity.getTitle())
                .slug(entity.getSlug())
                .content(entity.getContent())
                .image(entity.getImage())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }
}
