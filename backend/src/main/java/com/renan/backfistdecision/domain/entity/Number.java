package com.renan.backfistdecision.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Column(name = "NUMBER", unique = true)
    private String number;

    @Column(name = "CLIENT_ID")
    private Long client_id;

    @PrePersist
    @PreUpdate
    private void validateNumber() throws Exception{
        String num = this.getNumber();
        Pattern pattern = Pattern.compile("(([0-9])\\2{3})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(num);

        if (matcher.find()) {
            throw new Exception("Numero de telefone invalido");
        }
    }
}
