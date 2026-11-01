package com.scripture_room.backend.roster;

import com.scripture_room.backend.users.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "roster_assignments",uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_roster_assignments_scheduled_date",
                columnNames = "scheduled_date")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RosterAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "assigned_member_id",nullable = false)
    private User assignedMember;

    @Column(name = "scheduled_date",nullable = false)
    private LocalDate scheduledDate;

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
    protected   void onUpdate(){
        updatedAt = Instant.now();
    }
}
