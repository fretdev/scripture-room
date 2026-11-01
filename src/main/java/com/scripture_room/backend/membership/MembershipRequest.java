package com.scripture_room.backend.membership;

import com.scripture_room.backend.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "membership_requests")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private MembershipRequestStatus status = MembershipRequestStatus.PENDING;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private  User reviewedBy;

    @Column(name = "reviewed_at")
    private Instant reviewedAt;

    @Column(name = "created_at",nullable = false,updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at",nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected  void onCreate(){
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }

    private void ensurePending(){
        if(this.status != MembershipRequestStatus.PENDING){
            throw new IllegalStateException("Only pending requests can be reviewed.");
        }
    }
    public void approve(User reviewer){
        ensurePending();
        this.status = MembershipRequestStatus.APPROVED;
        this.reviewedBy = reviewer;
        this.reviewedAt = Instant.now();
    }
    public void reject(User reviewer){
        ensurePending();
        this.status = MembershipRequestStatus.REJECTED;
        this.reviewedBy = reviewer;
        this.reviewedAt = Instant.now();
    }
}
