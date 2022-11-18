package com.dev.logistics.domain.exception;

import java.io.Serial;

/**
 * @author rodrigoqueiroz
 */

public class DomainException extends RuntimeException {

    @Serial
    public static final long serialVersionUID = 1L;

    public DomainException(String message) {
        super(message);
    }

}
