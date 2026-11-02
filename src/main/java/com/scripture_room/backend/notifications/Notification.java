package com.scripture_room.backend.notifications;

import com.scripture_room.backend.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "recipient_id",nullable = false)
    private User recipient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NotificationType type;

    @Column(length = 150,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String message;

    @Column(name = "related_entity_type",length = 50)
    private String relatedEntityType;

    @Column(name = "related_entity_id")
    private Long relatedEntityId;

    @Column(name = "read_at")
    private Instant readAt;

    @Column(name = "created_at",nullable = false,updatable = false)
    private Instant createdAt;


    @PrePersist
    protected void onCreate(){
        createdAt = Instant.now();
    }
}