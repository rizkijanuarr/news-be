package com.kiki.newsbe.utils;

import com.kiki.newsbe.response.base.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public class ResponseHelper {
    public static <T> BaseResponse createBaseResponse(Page<T> data) {
        return BaseResponse.builder()
                .data(data.getContent())
                .pageSize(data.getPageable().getPageSize())
                .currentPage(data.getPageable().getPageNumber())
                .totalPage(data.getTotalPages())
                .totalData(data.getTotalElements())
                .build();
    }

    public static <T> BaseResponse createBaseResponse(Slice<T> data) {
        return BaseResponse.builder()
                .isFirst(data.isFirst())
                .isLast(data.isLast())
                .hasNext(data.hasNext())
                .data(data.getContent())
                .totalData(Long.parseLong(String.valueOf(data.getNumberOfElements())))
                .currentPage(data.getPageable().getPageNumber())
                .build();
    }

    public static <T> SliceResponseParameter<T> createResponse(Slice<T> data) {
        SliceResponseParameter<T> sliceResponseParameter = new SliceResponseParameter<>();
        sliceResponseParameter.setIsFirst(data.isFirst());
        sliceResponseParameter.setIsLast(data.isLast());
        sliceResponseParameter.setData(data.getContent());
        sliceResponseParameter.setTotalData(Long.parseLong(String.valueOf(data.getNumberOfElements())));
        sliceResponseParameter.setHasNext(data.hasNext());
        sliceResponseParameter.setCurrentPage(data.getPageable().getPageNumber());
        return sliceResponseParameter;
    }
    public static <T> SliceResponseParameter<T> createResponseSliceCount(Slice<T> data) {
        SliceResponseParameter<T> sliceResponseParameter = new SliceResponseParameter<>();
        sliceResponseParameter.setIsFirst(data.isFirst());
        sliceResponseParameter.setIsLast(data.isLast());
        sliceResponseParameter.setData(data.getContent());
        sliceResponseParameter.setTotalData(Long.parseLong(String.valueOf(data.getPageable().getPageSize())));
        if (data.getContent().isEmpty()){
            sliceResponseParameter.setTotalData(Long.parseLong(String.valueOf(0)));
        }
        sliceResponseParameter.setHasNext(data.hasNext());
        sliceResponseParameter.setCurrentPage(data.getPageable().getPageNumber());
        return sliceResponseParameter;
    }

    public static <T> DataResponseParameter<T> createResponse(T data) {
        DataResponseParameter<T> sliceResponseParameter = new DataResponseParameter<>();
        sliceResponseParameter.setData(data);
        return sliceResponseParameter;
    }

    public static <T> PageResponseParameter<T> createResponse(Page<T> data) {
        PageResponseParameter<T> sliceResponseParameter = new PageResponseParameter<>();
        sliceResponseParameter.setData(data.getContent());
        sliceResponseParameter.setPageSize(data.getPageable().getPageSize());
        sliceResponseParameter.setCurrentPage(data.getPageable().getPageNumber());
        sliceResponseParameter.setTotalPage(data.getTotalPages());
        sliceResponseParameter.setTotalData(data.getTotalElements());
        return sliceResponseParameter;
    }
    public static <T> ListResponseParameter<T> createResponse(List<T> data) {
        ListResponseParameter<T> sliceResponseParameter = new ListResponseParameter<>();
        sliceResponseParameter.setData(data);
        return sliceResponseParameter;
    }

    public static <T> BaseResponse createBaseResponse(List<T> data) {
        return BaseResponse.builder()
                .data(data)
                .build();
    }

    public static <T> BaseResponse createBaseResponse(T data) {
        return BaseResponse.builder()
                .data(data)
                .build();
    }

    public static BaseResponse createBaseResponse(String massage) {
        return BaseResponse.builder()
                .message(massage)
                .build();
    }

    public static BaseResponse createBaseResponse() {
        return BaseResponse.builder()
                .build();
    }
}
