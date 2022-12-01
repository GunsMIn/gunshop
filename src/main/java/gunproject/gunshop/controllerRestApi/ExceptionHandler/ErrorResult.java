package gunproject.gunshop.controllerRestApi.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ErrorResult {

    private String code;
    private String message;
}
