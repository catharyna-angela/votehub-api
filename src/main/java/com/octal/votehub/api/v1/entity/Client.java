package com.octal.votehub.api.v1.entity;

import com.octal.votehub.api.v1.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Table(name = "clientes")
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "senha", nullable = false)
    private String password;

    @Column(name = "nome", nullable = false, length = 32)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private Roles role = Roles.ROLE_CLIENT;

    @Column(name = "conta_ativada", nullable = false)
    private boolean activated = false;

    @OneToMany(mappedBy = "client")
    @Column(name = "votacoes")
    private List<Voting> votings;

    @OneToMany(mappedBy = "client")
    @Column(name = "enquetes")
    private List<Poll> polls;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "data_modificacao")
    private LocalDateTime lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
