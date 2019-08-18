package my.com.rhb.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceValidationException extends RuntimeException {

    private static final long serialVersionUID = 4693898866570942147L;

    public ServiceValidationException(String message) {
        super(message);
    }
}