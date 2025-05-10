package com.kiki.newsbe.services.v1;

import com.kiki.newsbe.request.v1.UserRequestV1;
import com.kiki.newsbe.response.v1.UserResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserServiceV1 {
    List<UserResponseV1> getListUser();
    UserResponseV1 createUser(UserRequestV1 req);
    UserResponseV1 detailUser(String id);
    UserResponseV1 updateUser(String id, UserRequestV1 req);
    UserResponseV1 deleteUser(String id);
    Slice<UserResponseV1> getUsersActive(Pageable pageable);
    Slice<UserResponseV1> getUsersInActive(Pageable pageable);
}