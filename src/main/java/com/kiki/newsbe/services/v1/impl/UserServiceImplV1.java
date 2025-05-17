package com.kiki.newsbe.services.v1.impl;

import com.kiki.newsbe.repositories.auth.RoleRepository;
import com.kiki.newsbe.repositories.entities.auth.RoleEntity;
import com.kiki.newsbe.utils.exceptions.NotFoundException;
import com.kiki.newsbe.utils.message.MessageLib;
import com.kiki.newsbe.utils.validation.Validate;
import com.kiki.newsbe.repositories.auth.UserRepository;
import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import com.kiki.newsbe.request.v1.UserRequestV1;
import com.kiki.newsbe.response.v1.UserResponseV1;
import com.kiki.newsbe.services.v1.UserServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImplV1 implements UserServiceV1 {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageLib messageLib;

    @Override
    public List<UserResponseV1> getListUser() {
        List<UserEntity> users = userRepository.findAllByOrderByCreatedDateDesc();
        List<UserResponseV1> responses = new ArrayList<>();
        for (UserEntity user : users) {
            responses.add(mapUserToResponse(user));
        }
        return responses;
    }

    @Override
    public UserResponseV1 createUser(UserRequestV1 req) {
        UserEntity savedUser = setUserInDatabase(req);
        return mapUserToResponse(savedUser);
    }

    @Override
    public UserResponseV1 detailUser(String id) {
        UserEntity userById = findUserById(id);
        return mapUserToResponse(userById);
    }

    @Override
    public UserResponseV1 updateUser(String id, UserRequestV1 req) {
        UserEntity updated = setUserUpdateInDatabase(id, req);
        return mapUserToResponse(updated);
    }

    @Override
    public UserResponseV1 deleteUser(String id) {
        return mapUserToResponse(setUserSoftDelete(id));
    }

    @Override
    public Page<UserResponseV1> getUsersActive(Pageable pageable, String stringFilter) {

        if(stringFilter == null || stringFilter.isEmpty()){
            stringFilter = null;
        }

        Page<UserEntity> usersList = userRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable, stringFilter);
        return usersList.map(this::mapUserToResponse);
    }

    @Override
    public Slice<UserResponseV1> getUsersInActive(Pageable pageable) {
        Slice<UserEntity> usersList = userRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<UserResponseV1> responses = new ArrayList<>();

        for (UserEntity user : usersList) {
            responses.add(mapUserToResponse(user));
        }

        return new SliceImpl<>(responses, pageable, usersList.hasNext());
    }

    private Optional<RoleEntity> findRoleByName(UserRequestV1 req) {
        return Optional.ofNullable(roleRepository.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("Role Not Found")));
    }

    private UserEntity setUserInDatabase(UserRequestV1 req) {
        Validate.c(req, Map.of(
                messageLib.getUserNameCantNull(), UserRequestV1::getUser_name,
                messageLib.getUserEmailCantNull(), UserRequestV1::getUser_email,
                messageLib.getUserPasswordCantNull(), UserRequestV1::getUser_password
        ));

        findRoleByName(req);

        UserEntity account = new UserEntity();
        account.setUser_name(req.getUser_name());
        account.setUser_email(req.getUser_email());
        account.setUser_password(passwordEncoder.encode(req.getUser_password()));
        account.setUser_phone(req.getUser_phone());
        account.setRole(findRoleByName(req).get());
        account.setCreatedBy(getCurentUser());
        account.setCreatedDate(getCreatedDate());

        return userRepository.save(account);
    }

    private UserEntity setUserUpdateInDatabase(String id, UserRequestV1 req) {
        findRoleByName(req);
        UserEntity userById = findUserById(id);

        userById.setUser_name(req.getUser_name());
        userById.setUser_email(req.getUser_email());
        userById.setUser_password(passwordEncoder.encode(req.getUser_password()));
        userById.setUser_phone(req.getUser_phone());
        userById.setRole(findRoleByName(req).get());
        userById.setModifiedBy(getModifiedByUpdate());
        userById.setModifiedDate(getModifiedDate());

        return userRepository.save(userById);
    }

    private UserResponseV1 mapUserToResponse(UserEntity entity) {
        return UserResponseV1.builder()
                .id(entity.getId())
                .user_name(entity.getUser_name())
                .user_email(entity.getUser_email())
                .user_password(entity.getUser_password())
                .user_phone(entity.getUser_phone())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    private UserEntity findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getUserIdNotFound()));
    }

    private UserEntity setUserSoftDelete (String id) {
        UserEntity userById = findUserById(id);

        userById.setDeletedDate(getModifiedDate());
        userById.setDeletedBy(getCurentUser());
        userById.setModifiedBy(getModifiedByDelete());
        userById.setActive(false);

        return userRepository.save(userById);
    }

    private String getCurentUser() {
        return "SYSTEM";
    }

    private String getModifiedByDelete() {
        return "SOFT DELETE";
    }

    private String getModifiedByUpdate() {
        return "UPDATE";
    }

    private Date getModifiedDate() {
        return new Date();
    }

    private Date getCreatedDate() {
        return new Date();
    }
}
