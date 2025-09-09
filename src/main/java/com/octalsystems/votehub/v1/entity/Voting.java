package com.octalsystems.votehub.v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Table(name = "votacao")
@NoArgsConstructor
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "descricao")
    private String description;

    @Column(name = "data_de_expiracao")
    private LocalDateTime expirationDate;

    @Column(name = "url")
    private String url = null;

    @Column(name = "gerar_qrcode", nullable = false)
    private boolean generateQrcode = false; //se true, a url será convertida em imagem qrcode.

//    @Column(name = "candidatos", nullable = false)
//    List<Candidate> candidates;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime createdDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Voting voting = (Voting) o;
        return Objects.equals(id, voting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
