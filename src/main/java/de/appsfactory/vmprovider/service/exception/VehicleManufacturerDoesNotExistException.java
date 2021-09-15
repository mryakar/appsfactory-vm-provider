package de.appsfactory.vmprovider.service.exception;

import de.appsfactory.vmprovider.util.Constant;

/**
 * Thrown when vehicle manufacturer could not be found in the database.
 *
 * @author mryakar
 */
public class VehicleManufacturerDoesNotExistException extends RuntimeException {
    public VehicleManufacturerDoesNotExistException() {
        super(Constant.ERROR_MESSAGE_NOT_EXISTING_VM);
    }
}
