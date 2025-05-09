package com.kiki.newsbe.services.v1;


import com.kiki.newsbe.request.v1.CategoryRequestV1;
import com.kiki.newsbe.response.v1.CategoryResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CategoryServiceV1 {

    List<CategoryResponseV1> getListCategories();

    CategoryResponseV1 createCategory(CategoryRequestV1 request);

    CategoryResponseV1 detailsCategory(String id);

    CategoryResponseV1 updateCategory(String id, CategoryRequestV1 request);

    CategoryResponseV1 deleteCategory(String id);

    Slice<CategoryResponseV1> getListCategoriesActive(Pageable pageable);

    Slice<CategoryResponseV1> getListCategoriesInactive(Pageable pageable);

}
