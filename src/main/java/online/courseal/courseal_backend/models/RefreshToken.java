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
    private int refresh_token_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "refreshTokens_users",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private int user_id;
    private boolean is_valid;
    private LocalDateTime date_created;

    public RefreshToken(){}

    public RefreshToken(boolean is_valid, LocalDateTime date_created){
        this.is_valid = is_valid;
        this.date_created = date_created;
    }
}
