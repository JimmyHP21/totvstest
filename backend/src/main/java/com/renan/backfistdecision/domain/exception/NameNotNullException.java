package com.renan.backfistdecision.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @autor Renan Peres.
 *
 * Classe padrão para tratamento de erros NameNull
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NameNotNullException extends RuntimeException {
    public NameNotNullException() {
        super("Parametro NAME está vazio!");
    }
}