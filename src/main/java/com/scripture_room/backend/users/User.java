package com.scripture_room.backend.users;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 30)
    private String username;

    @Column(nullable = false,unique = true,length = 255)
    private  String email;

    @Column(name = "password_hash",nullable = false,length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private UserRole role = UserRole.MEMBER;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_status",nullable = false,length = 20)
    private MembershipStatus membershipStatus = MembershipStatus.PENDING;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "avatar_url",length = 500)
    private  String avatarUrl;

    @Column(name = "created_at",nullable = false,updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at",nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        Instant now  = Instant.now();
        createdAt = now;
        updatedAt = now;
    }
    @PreUpdate
    protected  void onUpdate(){
        updatedAt = Instant.now();
    }

    public void updateUsername(String username){
        this.username = username;
    }
    public void updateEmail(String email){
        this.email = email;
    }
    public void updateBio(String bio){
        this.bio = bio;
    }
    public void updateAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public void changePasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }
}
