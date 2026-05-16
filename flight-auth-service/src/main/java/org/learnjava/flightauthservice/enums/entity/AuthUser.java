package org.learnjava.flightauthservice.enums.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.learnjava.flightauthservice.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_users")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    private Integer id;

    private String userName;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
