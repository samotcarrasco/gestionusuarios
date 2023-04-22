package es.mdef.gestionusuarios.REST;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenExceptionHandler extends RuntimeException {
    public ForbiddenExceptionHandler() {
        super("La solicitud no se ha podio completar correctamente, consulte con el administrador ");
    }

    public ForbiddenExceptionHandler(String message, Throwable cause) {
    	super("La solicitud no se ha podio completar correctamente, consulte con el administrador ");
    }

    public ForbiddenExceptionHandler(String message) {
    	super("La solicitud no se ha podio completar correctamente, consulte con el administrador ");
    }

    public ForbiddenExceptionHandler(Throwable cause) {
    	super("La solicitud no se ha podio completar correctamente, consulte con el administrador ");
    }
}