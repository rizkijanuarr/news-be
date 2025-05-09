package com.kiki.newsbe.services.auth;

import com.kiki.newsbe.configs.constant.ConstantSecurity;
import com.kiki.newsbe.configs.security.JwtUtil;
import com.kiki.newsbe.repositories.auth.RefreshTokenRepository;
import com.kiki.newsbe.repositories.auth.RoleRepository;
import com.kiki.newsbe.repositories.auth.UserRepository;
import com.kiki.newsbe.repositories.entities.auth.RefreshTokenEntity;
import com.kiki.newsbe.repositories.entities.auth.RoleEntity;
import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import com.kiki.newsbe.request.auth.RegisterRequest;
import com.kiki.newsbe.request.auth.LoginRequest;
import com.kiki.newsbe.response.auth.LoginResponse;
import com.kiki.newsbe.response.auth.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public RegisterResponse register(RegisterRequest req) {
        findRoleByName(req);
        UserEntity users = setUserInDatabase(req);
        return mapRegisterToResponses(users);
    }

    private RegisterResponse mapRegisterToResponses(UserEntity entity) {
        return RegisterResponse.builder()
                .id(entity.getId())
                .user_name(entity.getUser_name())
                .user_email(entity.getUser_email())
                .phone(entity.getUser_phone())
                .role(entity.getRole().getName())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public LoginResponse login(LoginRequest req) {
        getAuthenticationManager(req);
        UserEntity users = findUserById(req);
        String accessToken = jwtUtils.generateAccessToken(users);
        String refreshToken = jwtUtils.generateRefreshToken(users);
        
        // Save refresh token
        saveRefreshToken(users, refreshToken);
        
        return mapLoginToResponses(users, accessToken, refreshToken);
    }

    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    public LoginResponse refreshToken(String refreshToken) {
        refreshToken = extractToken(refreshToken);
        
        if (!jwtUtils.isTokenValid(refreshToken) || !jwtUtils.isRefreshToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String userEmail = jwtUtils.validateToken(refreshToken);
        UserEntity user = userRepository.login(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify refresh token in database
        RefreshTokenEntity storedToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (storedToken.getRevoked()) {
            throw new RuntimeException("Refresh token has been revoked");
        }

        // Generate new tokens
        String newAccessToken = jwtUtils.generateAccessToken(user);
        String newRefreshToken = jwtUtils.generateRefreshToken(user);

        // Update refresh token in database
        storedToken.setToken(newRefreshToken);
        storedToken.setExpiryDate(new Date(System.currentTimeMillis() + ConstantSecurity.REFRESH_EXPIRATION_TIME));
        refreshTokenRepository.save(storedToken);

        return mapLoginToResponses(user, newAccessToken, newRefreshToken);
    }

    private Optional<RoleEntity> findRoleByName(RegisterRequest req) {
        return Optional.ofNullable(roleRepository.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("Role Not Found")));
    }

    private UserEntity setUserInDatabase(RegisterRequest req) {
        findRoleByName(req);

        UserEntity account = new UserEntity();
        account.setUser_name(req.getUser_name());
        account.setUser_email(req.getUser_email());
        account.setUser_password(passwordEncoder.encode(req.getUser_password()));
        account.setUser_phone(req.getUser_phone());
        account.setRole(findRoleByName(req).get());
        account.setCreatedBy(createdBy());
        account.setCreatedDate(createdDate());

        return userRepository.save(account);
    }

    private UserEntity findUserById(LoginRequest req) {
        return userRepository.login(req.getUser_email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

    private void getAuthenticationManager(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUser_email(), req.getUser_password())
        );
    }

    private LoginResponse mapLoginToResponses(UserEntity entity, String accessToken, String refreshToken) {
        return LoginResponse.builder()
                .id(entity.getId())
                .user_name(entity.getUser_name())
                .user_email(entity.getUser_email())
                .phone(entity.getUser_phone())
                .role(entity.getRole().getName())
                .access_token(accessToken)
                .refresh_token(refreshToken)
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    private void saveRefreshToken(UserEntity user, String token) {
        try {
            // Delete existing tokens for this user
            int deletedCount = refreshTokenRepository.deleteByUser(user);
            log.info("Deleted {} existing tokens for user {}", deletedCount, user.getUser_email());
            
            // Create new token
            RefreshTokenEntity refreshToken = new RefreshTokenEntity();
            refreshToken.setUser(user);
            refreshToken.setToken(token);
            refreshToken.setExpiryDate(new Date(System.currentTimeMillis() + ConstantSecurity.REFRESH_EXPIRATION_TIME));
            refreshToken.setRevoked(false);
            refreshToken.setActive(true);
            refreshToken.setCreatedBy(createdBy());
            refreshToken.setCreatedDate(createdDate());
            refreshTokenRepository.save(refreshToken);
        } catch (Exception e) {
            log.error("Error saving refresh token for user {}: {}", user.getUser_email(), e.getMessage());
            throw new RuntimeException("Failed to save refresh token: " + e.getMessage());
        }
    }

    public String createdBy() {
        return "SYSTEM";
    }

    public Date createdDate() {
        return new Date();
    }

    @Transactional
    public Map<String, Boolean> logout(String token) {
        token = extractToken(token);

        if (!jwtUtils.isTokenValid(token)) {
            throw new RuntimeException("Invalid token");
        }

        String userEmail = jwtUtils.validateToken(token);
        UserEntity user = userRepository.login(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Revoke all refresh tokens for the user
        refreshTokenRepository.deleteByUser(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return response;
    }
}
