package se.lexicon.mattias.library.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse extends ExceptionResponse{

    private final List<Violation> violations;

    public ValidationErrorResponse(LocalDateTime timestamp, Integer status, String error, String message, String details, List<Violation> violations) {
        super(timestamp, status, error, message, details);
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }

}
