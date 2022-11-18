package com.dev.logistics.domain.exception;

import java.io.Serial;

/**
 * @author rodrigoqueiroz
 */

public class EntityNotFoundException extends DomainException {

    @Serial
    public static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
