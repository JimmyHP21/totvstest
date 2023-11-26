package com.renan.backfistdecision.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NUMBER_TABLE")
@SequenceGenerator(allocationSize = 1, name = "seqNumber", sequenceName = "SEQ_NUMBER")
public class Number implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqNumber")
    private Long id;

    @NotBlank(message = "Numero não pode ser vazio")
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$", message = "Numero de telefone invalido")
    @Column(name = "NUMBER", unique = true)
    private String number;

    @Column(name = "CLIENT_ID")
    private Long client_id;
}
