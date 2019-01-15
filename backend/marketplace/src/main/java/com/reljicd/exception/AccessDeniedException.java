package com.reljicd.exception;

import com.reljicd.model.Product;

/**
 * @author Anna Guminskaya
 * @since 15/01/2019.
 */
public class AccessDeniedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "You have no access here";

    public AccessDeniedException() {
        super(DEFAULT_MESSAGE);
    }

}
