package net.enset.bdcc.inventoryservice.exceptions;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Element déjà traité",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ErrorResponse alreadyProcessedItem(ProductAlreadyExistException ex) {
        log.error("Item already process: {}", ex.getMessage(), ex);
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "Produit non trouvé",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ErrorResponse alreadyProcessedItem(ProductNotFoundException ex) {
        log.error("Customer not exist: {}", ex.getMessage(), ex);
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "Erreur serveur",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ErrorResponse alreadyProcessedItem(Exception ex) {
        log.error("Server error: {}", ex.getMessage(), ex);
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(500)
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(ex.getMessage())
                .build();
    }
}
