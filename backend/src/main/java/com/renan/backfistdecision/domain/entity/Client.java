package com.renan.backfistdecision.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT_TABLE")
@SequenceGenerator(allocationSize = 1, name = "seqClient", sequenceName = "SEQ_CLIENT")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqClient")
    private Long id;

    @NotBlank( message = "Nome não pode ser vazio")
    @Column(name = "NAME")
    private String name;

    @CPF(message = "CPF Inválido")
    @NotBlank( message = "CPF não pode ser vazio")
    @Size(min = 11, max = 11, message = "Tamanho informado inválido")
    @Column(name = "CPF", unique = true)
    private String cpf;

    @NotBlank( message = "Endereço não pode ser vazio")
    @Column(name = "ADDRESS")
    private String address;

    @NotBlank( message = "Bairro não pode ser vazio")
    @Column(name = "NEIGHBORHOOD")
    private String neighborhood ;

    @JoinColumn(name = "CLIENT_ID")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE} )
    private List<Number> numberList;

}
