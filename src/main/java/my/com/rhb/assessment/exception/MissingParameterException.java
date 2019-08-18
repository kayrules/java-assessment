package my.com.rhb.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingParameterException extends MissingServletRequestParameterException {

    private static final long serialVersionUID = 1L;

    public MissingParameterException(String paramName, String paramType) {
        super(paramName, paramType);
    }

}