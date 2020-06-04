package de.slevermann.cocktails.exception;

public class BadTranslationException extends RuntimeException {

    public BadTranslationException(Reason reason) {
        super(reason.message);
    }

    public BadTranslationException(String language) {
        super(Reason.UNSUPPORTED.message + " " + language);
    }

    public enum Reason {
        EMPTY("Missing translation data"),
        MISMATCH("Inconsistent data for languages"),
        UNSUPPORTED("Unsupported language");

        private final String message;

        Reason(String message) {
            this.message = message;
        }
    }
}
