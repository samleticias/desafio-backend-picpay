package com.example.desafiopicpay.util;

import com.example.desafiopicpay.services.exceptions.ApplicationException;
import com.example.desafiopicpay.services.exceptions.EmailAlreadyExistsException;
import com.example.desafiopicpay.services.exceptions.UnauthorizedTransactionException;
import com.example.desafiopicpay.services.exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> UserNotFoundHandler(UserNotFoundException e, HttpServletRequest request){
        String error = "Usuário não encontrado.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<StandardError> UnauthorizedTransactionHandler(UnauthorizedTransactionException e, HttpServletRequest request){
        String error = "Transação não autorizada.";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<StandardError> InvalidActionHandler(ApplicationException e, HttpServletRequest request){
        String error = "Ação inválida.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<StandardError> EmailAlreadyExistsHandler(EmailAlreadyExistsException e, HttpServletRequest request) {
        String error = "Já existe um usuário cadastrado com esse e-mail.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<StandardError> handleForbiddenException(HttpClientErrorException.Forbidden e, HttpServletRequest request) {
        String error = "Ocorreu algum erro na Requisição via API para autorizar a Transação.";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
