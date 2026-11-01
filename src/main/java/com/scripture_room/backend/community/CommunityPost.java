package com.scripture_room.backend.community;

import com.scripture_room.backend.studies.ScriptureStudy;
import com.scripture_room.backend.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "community_posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "author_id",nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "scripture_study_id",nullable = false)
    private ScriptureStudy scriptureStudy;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type",nullable = false,length = 20)
    private PostType postType;

    @Column(nullable = false,length = 150)
    private String title;

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