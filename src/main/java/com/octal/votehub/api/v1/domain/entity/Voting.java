package com.octal.votehub.api.v1.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "votacoes")
@NoArgsConstructor
public class Voting extends Scheme {
    @Column(name = "voto_com_justificativa")
    private boolean justify = false;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "voting", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "candidatos_voto")
    private List<Candidate> candidates;

}
