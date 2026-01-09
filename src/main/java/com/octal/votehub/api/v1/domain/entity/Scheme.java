package com.octal.votehub.api.v1.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.octal.votehub.api.v1.domain.enums.SchemeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class Scheme {
    @Id
    @SequenceGenerator(name = "esquemas_seq", sequenceName = "esquemas_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "esquemas_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "descricao")
    private String description;

    @Column(name = "data_de_expiracao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expirationDate;

    @Column(name = "url")
    private String url = null; //gera uma url ao criar votação com token no path para usuários votarem.

    @Column(name = "gerar_qrcode", nullable = false)
    private boolean generateQrcode = false; //se true, a url será convertida em imagem qrcode.

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_esquema", nullable = false)
    private SchemeType schemeType;

    @CreatedDate
    @Column(name = "data_de_criacao")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "data_de_modificacao")
    private LocalDateTime modifiedDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Scheme scheme = (Scheme) o;
        return Objects.equals(id, scheme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
