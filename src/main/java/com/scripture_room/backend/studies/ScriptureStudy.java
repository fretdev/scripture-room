package com.scripture_room.backend.studies;

import com.scripture_room.backend.roster.RosterAssignment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "scripture_studies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScriptureStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "roster_assignment_id",nullable = false)
    private RosterAssignment rosterAssignment;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false,length = 50)
    private String book;

    @Column(name = "start_chapter",nullable = false)
    private Integer startChapter;

    @Column(name = "start_verse",nullable = false)
    private Integer startVerse;

    @Column(name = "end_chapter",nullable = false)
    private Integer endChapter;

    @Column(name = "end_verse",nullable = false)
    private Integer endVerse;

    @Column(name = "translation_code",nullable = false,length = 20)
    private String translationCode;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String lesson;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String reflection;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String application;

    @Column(name = "closing_prayer",nullable = false,columnDefinition = "TEXT")
    private String closingPrayer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private StudyStatus status = StudyStatus.DRAFT;

    @Column(name = "published_at")
    private Instant publishedAt;

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
    protected void onUpdate(){
        updatedAt = Instant.now();
    }
}
