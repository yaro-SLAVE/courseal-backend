package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.errors.exceptions.InvalidRefreshTokenException;
import online.courseal.courseal_backend.models.RefreshToken;
import online.courseal.courseal_backend.repositories.RefreshTokenRepository;
import online.courseal.courseal_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService{
    @Value("${REFRESH_EXPIRATION_MS}")
    private Long refreshTokenDurationMs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByRefreshToken(token);
    }

    public RefreshToken createRefreshToken(Integer userId){
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setDateCreated(LocalDateTime.now());
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setValid(true);

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        LocalDateTime dateExpired = refreshToken.getDateCreated().plusSeconds(refreshTokenDurationMs / 1000);
        if (LocalDateTime.now().isAfter(dateExpired) && refreshToken.isValid()) {
            refreshTokenRepository.delete(refreshToken);
            throw new InvalidRefreshTokenException();
        }

        return refreshToken;
    }

    @Transactional
    public int deleteByUserId(Integer userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
