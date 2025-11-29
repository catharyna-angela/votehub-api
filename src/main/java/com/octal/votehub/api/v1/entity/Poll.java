package com.octal.votehub.api.v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "enquetes")
@NoArgsConstructor
public class Poll extends Scheme {
    @Column(name = "multipla_escolha", nullable = false)
    private boolean multipleChoice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    @Column(name = "candidatos_enquete")
    private List<Candidate> candidates;

}
