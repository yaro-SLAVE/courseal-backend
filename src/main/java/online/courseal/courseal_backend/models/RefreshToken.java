package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "RefreshTokens",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "refresh_token_id"),
        @UniqueConstraint(columnNames = "refreshToken")
    })
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id", nullable = false)
    private int refreshTokenId;
    @Setter
    @Column(nullable = false)
    private String refreshToken;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    @Setter
    private User user;
    @Column(name = "is_valid", nullable = false)
    @Setter
    private boolean isValid;
    @Column(name = "date_created", nullable = false)
    @Setter
    private LocalDateTime dateCreated;

    public RefreshToken(){}

    public RefreshToken(String refreshToken, User user, boolean isValid, LocalDateTime dateCreated){
        this.refreshToken = refreshToken;
        this.user = user;
        this.isValid = isValid;
        this.dateCreated = dateCreated;
    }
}