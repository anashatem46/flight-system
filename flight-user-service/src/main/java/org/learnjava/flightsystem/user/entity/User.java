package org.learnjava.flightsystem.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Table(name = "users")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;


    @Builder.Default
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Builder.Default
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();


}
