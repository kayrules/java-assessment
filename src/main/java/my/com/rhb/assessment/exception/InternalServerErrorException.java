package my.com.rhb.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = 5799948847163068992L;

    public InternalServerErrorException(String message) {
        super(message);
    }
}
