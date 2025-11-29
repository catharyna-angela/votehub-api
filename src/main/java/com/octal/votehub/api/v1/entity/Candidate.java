package com.octal.votehub.api.v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "candidatos")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    private boolean vote = false; //vai receber "true" quando for votado e em seguida incrementado em "totalVotes".

    //fixme: criar atributo "version".

    @Column(name = "total_de_votos")
    private int totalVotes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voting_id")
    private Voting voting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

}
