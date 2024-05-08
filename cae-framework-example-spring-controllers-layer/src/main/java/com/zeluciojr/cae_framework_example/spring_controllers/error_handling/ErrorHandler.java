package com.zeluciojr.cae_framework_example.spring_controllers.error_handling;

import com.cae.mapped_exceptions.MappedException;
import com.cae.mapped_exceptions.specifics.InputMappedException;
import com.cae.mapped_exceptions.specifics.InternalMappedException;
import com.cae.mapped_exceptions.specifics.NotFoundMappedException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<Problem> handle(InputMappedException inputMappedException){
        return ResponseEntity.badRequest().body(Problem.of(inputMappedException));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(NotFoundMappedException notFoundMappedException){
        return ResponseEntity.status(404).body(Problem.of(notFoundMappedException));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(InternalMappedException internalMappedException){
        return ResponseEntity.internalServerError().body(Problem.of(internalMappedException));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handle(Exception unexpectedException){
        return ResponseEntity.internalServerError().body(Problem.of(unexpectedException));
    }


    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class Problem{

        public static Problem of(MappedException mappedException){
            return new Problem(mappedException.getBriefPublicMessage());
        }

        public static Problem of(Exception exception){
            return new Problem(exception.getMessage());
        }

        private final String message;

    }

}
