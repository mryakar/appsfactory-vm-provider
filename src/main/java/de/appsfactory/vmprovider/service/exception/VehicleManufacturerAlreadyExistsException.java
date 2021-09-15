package de.appsfactory.vmprovider.service.exception;

import de.appsfactory.vmprovider.util.Constant;

/**
 * Thrown when the vehicle manufacturer already exists in the database.
 *
 * @author mryakar
 */
public class VehicleManufacturerAlreadyExistsException extends RuntimeException {
    public VehicleManufacturerAlreadyExistsException() {
        super(Constant.ERROR_MESSAGE_EXISTING_VM);
    }
}
