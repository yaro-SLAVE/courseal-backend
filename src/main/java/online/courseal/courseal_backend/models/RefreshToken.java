/*
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_id",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Setter
    private User userId;
    @Column(name = "is_valid")
    @Setter
    private boolean isValid;
    @Column(name = "date_created")
    @Setter
    private LocalDateTime dateCreated;

    public RefreshToken(){}

    public RefreshToken(String refreshToken, User userId, boolean isValid, LocalDateTime dateCreated){
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.isValid = isValid;
        this.dateCreated = dateCreated;
    }
}
*/