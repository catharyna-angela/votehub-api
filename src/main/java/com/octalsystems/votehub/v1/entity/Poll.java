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
@Table(name = "enquetes")
@NoArgsConstructor
public class Poll extends Scheme {

    @Column(name = "multipla_escolha", nullable = false)
    private boolean multipleChoice;

}
