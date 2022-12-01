package gunproject.gunshop.controllerRestApi.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class OrderException extends RuntimeException {

    //private String errorCode;
    private String message;

}
