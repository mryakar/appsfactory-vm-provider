package de.appsfactory.vmprovider.service;

import de.appsfactory.vmprovider.service.exception.VehicleManufacturerAlreadyExistsException;
import de.appsfactory.vmprovider.service.exception.VehicleManufacturerDoesNotExistException;
import de.appsfactory.vmprovider.util.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * Handler class that handles exceptions which are raised from service class.
 *
 * @author mryakar
 */
@ControllerAdvice
@RestController
public class VehicleManufacturerServiceExceptionHandler {

    @ExceptionHandler(VehicleManufacturerDoesNotExistException.class)
    public final ResponseEntity<ExceptionResponse> handleNotExistingVehicleManufacturerException(
            VehicleManufacturerDoesNotExistException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(new ExceptionResponse(
                exception.getMessage(),
                request.getDescription(false)
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VehicleManufacturerAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse> handleAlreadyExistingVehicleManufacturerException(
            VehicleManufacturerAlreadyExistsException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(new ExceptionResponse(
                exception.getMessage(),
                request.getDescription(false)
        ), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalStateException.class)
    public final ResponseEntity<ExceptionResponse> handleIllegalStateException(
            IllegalStateException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(new ExceptionResponse(
                exception.getMessage(),
                request.getDescription(false)
        ), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}