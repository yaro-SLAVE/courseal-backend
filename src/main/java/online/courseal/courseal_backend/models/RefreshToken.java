package online.courseal.courseal_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "RefreshTokens",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "refresh_token_id")
    })
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private int refreshTokenId;
    @Setter
    private String refreshToken;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    @Setter
    private User user;
    @Column(name = "is_valid")
    @Setter
    private boolean isValid;
    @Column(name = "date_created")
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