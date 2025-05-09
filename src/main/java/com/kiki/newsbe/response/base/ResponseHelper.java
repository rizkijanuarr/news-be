package com.kiki.newsbe.response.base;

import com.kiki.newsbe.utils.enums.ErrorCodeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHelper {
    public static ResponseEntity<BaseResponse> buildOkResponse(Object data) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(data)
                .success(true).build();

        return ResponseEntity.ok(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildOkResponse() {
        BaseResponse mainResponse = BaseResponse.builder()
                .success(true).build();

        return ResponseEntity.ok(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildOkResponse(String data) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(data)
                .success(true).build();

        return ResponseEntity.ok(mainResponse);
    }

    public static <T> ResponseEntity<BaseResponsePagination> buildOkResponsePage(Page<T> data) {
        BaseResponsePagination mainResponse = BaseResponsePagination.builder()
                .data(data.getContent())
                .page(data.getNumber())
                .allElement(data.getTotalElements())
                .element(data.getSize())
                .success(true).build();

        return ResponseEntity.ok(mainResponse);
    }

    public static <T> ResponseEntity<BaseResponseSlice> buildOkeResponse(Slice<T> data) {
        return ResponseEntity.ok(ResponseHelper.buildSliceResponse(data));
    }

    public static <T> BaseResponseSlice buildSliceResponse(Slice<T> data) {
        BaseResponseSlice build = BaseResponseSlice.builder()
                .data(data.get())
                .hasNext(data.hasNext())
                .isFirst(data.isFirst())
                .isLast(data.isLast())
                .success(true)
                .size(data.getSize())
                .page(data.getNumber())
                .build();
        return build;
    }

    public static ResponseEntity<BaseResponse> buildCreatedResponse(Object data) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(data)
                .success(true).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildNoDataResponse(String message) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(message)
                .success(false).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildInternalServerErrorResponse(String message) {
        BaseResponse mainResponse = BaseResponse.builder()
                .data(message)
                .success(false).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildBadRequestResponse(String message) {
        BaseResponse mainResponse = BaseResponse.builder()
                .success(false)
                .data(message).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildBadRequestResponseWithErrorCode(String message, ErrorCodeEnum errorCodeEnum, String fieldName) {
        BaseResponse mainResponse = BaseResponse.builder()
                .success(false)
                .data(message)
                .errors(List.of(BaseErrorResponse.builder()
                        .code(errorCodeEnum.getCode())
                        .description(errorCodeEnum.getDescription())
                        .field(fieldName)
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(mainResponse);
    }

    public static ResponseEntity<BaseResponse> buildUnauthorizedResponse(String message) {
        BaseResponse mainResponse = BaseResponse.builder()
                .success(false)
                .data(message).build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(mainResponse);
    }
    public static <T> ResponseEntity<BaseResponseSlice> buildSliceResponseWithTotalElement(Slice<T> data, int totalElement) {
        BaseResponseSlice body = ResponseHelper.buildSliceResponse(data);
        body.setTotal(totalElement);
        return ResponseEntity.ok(body);
    }
}
