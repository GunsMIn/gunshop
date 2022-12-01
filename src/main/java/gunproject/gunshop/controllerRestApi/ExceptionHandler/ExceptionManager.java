package gunproject.gunshop.controllerRestApi.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResult> manageIllegalException(IllegalStateException e) {
        log.info("IllegalStateException 예외핸들러 들어옴");
        ErrorResult errorResult = new ErrorResult("CONFLICT 409 error", "name is duplicated");
        return new ResponseEntity<>(errorResult, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderException.class)
    public ErrorResult manageOrderException(OrderException e) {
        ErrorResult errorResult = new ErrorResult("NOT_FOUND 404 error", e.getMessage());
        return errorResult;
    }
}
