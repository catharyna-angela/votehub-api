package com.octalsystems.votehub.v1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
