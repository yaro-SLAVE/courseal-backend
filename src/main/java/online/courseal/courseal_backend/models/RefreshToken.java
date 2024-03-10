package online.courseal.courseal_backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RefreshTokens",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "refresh_token_id")
    })
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private int refreshTokenId;
    private String refreshToken;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "refreshTokens_users",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_id")
    private int userId;
    @Column(name = "is_valid")
    private boolean isValid;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public RefreshToken(){}

    public RefreshToken(int userId, boolean isValid, LocalDateTime dateCreated){
        this.userId = userId;
        this.isValid = isValid;
        this.dateCreated = dateCreated;
    }
}
