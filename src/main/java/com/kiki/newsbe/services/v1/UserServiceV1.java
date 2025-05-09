package com.kiki.newsbe.services.v1;

import com.kiki.newsbe.request.v1.UserRequestV1;
import com.kiki.newsbe.response.v1.UserResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserServiceV1 {
    List<UserResponseV1> getListUser(String requester);
    UserResponseV1 createUser(UserRequestV1 req, String requester);
    UserResponseV1 detailUser(String id, String requester);
    UserResponseV1 updateUser(String id, UserRequestV1 req, String requester);
    UserResponseV1 deleteUser(String id, String requester);
    Slice<UserResponseV1> getUsersActive(Pageable pageable, String requester);
    Slice<UserResponseV1> getUsersInActive(Pageable pageable, String requester);
}