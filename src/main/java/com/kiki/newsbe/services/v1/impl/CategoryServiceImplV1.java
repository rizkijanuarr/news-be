package com.kiki.newsbe.services.v1.impl;

import com.kiki.newsbe.repositories.CategoryRepository;
import com.kiki.newsbe.repositories.entities.CategoryEntity;
import com.kiki.newsbe.request.v1.CategoryRequestV1;
import com.kiki.newsbe.response.v1.CategoryResponseV1;
import com.kiki.newsbe.services.v1.CategoryServiceV1;
import com.kiki.newsbe.utils.FileStorageUtil;
import com.kiki.newsbe.utils.ImageUrlUtil;
import com.kiki.newsbe.utils.exceptions.NotFoundException;
import com.kiki.newsbe.utils.generated.Slug;
import com.kiki.newsbe.utils.message.MessageLib;
import com.kiki.newsbe.utils.validation.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplV1 implements CategoryServiceV1 {

    private final CategoryRepository categoryRepository;
    private final MessageLib messageLib;
    private final FileStorageUtil fileStorageUtil;
    private final ImageUrlUtil imageUrlUtil;

    @Override
    public List<CategoryResponseV1> getListCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryResponseV1> responses = new ArrayList<>();
        for (CategoryEntity category : categories) {
            responses.add(responses(category));
        }
        return responses;
    }

    @Override
    public CategoryResponseV1 createCategory(CategoryRequestV1 request) {
        CategoryEntity savedCategory = setCategoryInDatabase(request);
        return responses(savedCategory);
    }

    @Override
    public CategoryResponseV1 detailsCategory(String id) {
        CategoryEntity category = findCategoryById(id);
        return responses(category);
    }

    @Override
    public CategoryResponseV1 updateCategory(String id, CategoryRequestV1 request) {
        CategoryEntity updated = setCategoryUpdateInDatabase(id, request);
        return responses(updated);
    }

    @Override
    public CategoryResponseV1 deleteCategory(String id) {
        return responses(setCategorySoftDelete(id));
    }
    
    @Override
    public Page<CategoryResponseV1> getListCategoriesActive(Pageable pageable, String stringFilter) {

        if(stringFilter == null || stringFilter.isEmpty()){
            stringFilter = null;
        }

        Page<CategoryEntity> categoriesList = categoryRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable, stringFilter);
        return categoriesList.map(this::responses);
    }

    @Override
    public Slice<CategoryResponseV1> getListCategoriesInactive(Pageable pageable) {
        Slice<CategoryEntity> categoriesList = categoryRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<CategoryResponseV1> responses = new ArrayList<>();
        for (CategoryEntity category : categoriesList) {
            responses.add(responses(category));
        }

        return new SliceImpl<>(responses, pageable, categoriesList.hasNext());
    }

    private CategoryResponseV1 responses(CategoryEntity entity) {
        String imageUrl = imageUrlUtil.getImageUrl(entity.getImage());
        return CategoryResponseV1.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .image(imageUrl)
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    private CategoryEntity setCategoryInDatabase(CategoryRequestV1 request) {
        Validate.c(request, Map.of(
                "Nama Tidak Dapat Kosong", CategoryRequestV1::getName,
                "Gambar Tidak Dapat Kosong", CategoryRequestV1::getImage
        ));

        String imageFilename = null;
        try {
            imageFilename = fileStorageUtil.storeFile(request.getImage(), "category");
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file", e);
        }

        CategoryEntity category = new CategoryEntity();
        category.setName(request.getName());
        category.setSlug(Slug.of(request.getName()));
        category.setImage(imageFilename);

        category.setCreatedBy(getCurrentUser());
        category.setCreatedDate(getCreatedDate());

        return categoryRepository.save(category);
    }

    private CategoryEntity setCategoryUpdateInDatabase(String id, CategoryRequestV1 request) {
        CategoryEntity category = findCategoryById(id);

        // Handle image update if new image is provided
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            try {
                // Delete old image if exists
                if (category.getImage() != null) {
                    fileStorageUtil.deleteFile(category.getImage());
                }
                // Store new image
                String imageFilename = fileStorageUtil.storeFile(request.getImage(), "category");
                category.setImage(imageFilename);
            } catch (IOException e) {
                throw new RuntimeException("Failed to update image file", e);
            }
        }

        category.setName(request.getName());
        category.setSlug(Slug.of(request.getName()));

        category.setModifiedBy(getModifiedByUpdate());
        category.setModifiedDate(getModifiedDate());

        return categoryRepository.save(category);
    }

    private CategoryEntity setCategorySoftDelete(String id) {
        CategoryEntity category = findCategoryById(id);

        // Delete image file when category is deleted
        if (category.getImage() != null) {
            try {
                fileStorageUtil.deleteFile(category.getImage());
            } catch (IOException e) {
                // Log error but continue with deletion
                e.printStackTrace();
            }
        }

        category.setDeletedDate(getModifiedDate());
        category.setDeletedBy(getCurrentUser());
        category.setModifiedBy(getModifiedByDelete());
        category.setActive(false);

        return categoryRepository.save(category);
    }

    private String getModifiedByUpdate() {
        return "UPDATE";
    }

    private CategoryEntity findCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getCategoryNotFound()));
    }

    private String getCurrentUser() {
        return "SYSTEM";
    }

    private String getModifiedByDelete() {
        return "SOFT DELETE";
    }

    private Date getCreatedDate() {
        return new Date();
    }

    private Date getModifiedDate() {
        return new Date();
    }
}