package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.exceptions.TokenRefreshException;
import online.courseal.courseal_backend.models.RefreshToken;
import online.courseal.courseal_backend.repository.RefreshTokenRepository;
import online.courseal.courseal_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RefreshTokenService {
    private Long refreshTokenDurationMs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Integer userId){
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setDateCreated(LocalDateTime.now());
        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        LocalDateTime dateExpired = token.getDateCreated().plusSeconds(refreshTokenDurationMs / 1000);
        if (LocalDateTime.now().isAfter(dateExpired)) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getRefreshToken(), "Refresh token was expired. Please make a new login request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Integer userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
