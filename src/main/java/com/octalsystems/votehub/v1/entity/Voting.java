package com.octalsystems.votehub.v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
