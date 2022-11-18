package com.dev.logistics.api.handler;

import com.dev.logistics.domain.exception.DomainException;
import com.dev.logistics.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.NoResultException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rodrigoqueiroz
 */

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INVALIDATION_MESSAGE = "Um ou mais campos estão inválidos. " +
            "Faça o preenchimento correto e tente novamente.";

    private static final String GENERIC_ERROR_MESSAGE = "Houve uma falha inesperada no sistema. " +
            "Tente novamente e, se a falha persistir, entre em contato com o administrador.";

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiError.Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new ApiError.Field(name, message));
        }

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title(INVALIDATION_MESSAGE)
                .detail(ex.getMessage())
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title("Recurso não encontrado")
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title("Recurso inválido")
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Object> handleNoResult(NoResultException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title("Recurso não encontrado")
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title("Erro de integridade de dados.")
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointer(NullPointerException ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title(GENERIC_ERROR_MESSAGE)
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> handleHttpServerError(HttpServerErrorException ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title(GENERIC_ERROR_MESSAGE)
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRunTime(RuntimeException ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title(GENERIC_ERROR_MESSAGE)
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtException(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var apiError = ApiError
                .builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title(GENERIC_ERROR_MESSAGE)
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

}
