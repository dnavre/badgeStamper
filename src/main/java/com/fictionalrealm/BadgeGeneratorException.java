package com.fictionalrealm;

/**
 * User: dnavre
 * Date: 6/5/12
 * Time: 12:52 AM
 */
public class BadgeGeneratorException extends Exception {
    public BadgeGeneratorException(String msg) {
        super(msg);
    }

    public BadgeGeneratorException(String msg, Throwable t) {
        super(msg, t);
    }
}
