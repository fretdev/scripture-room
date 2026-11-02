package com.scripture_room.backend.comments;

import com.scripture_room.backend.community.CommunityPost;
import com.scripture_room.backend.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "author_id",nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "community_post_id",nullable = false)
    private CommunityPost communityPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at",nullable = false,updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at",nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected  void onUpdate(){
        updatedAt = Instant.now();
    }
}