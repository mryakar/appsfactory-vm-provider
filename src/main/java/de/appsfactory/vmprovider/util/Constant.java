package de.appsfactory.vmprovider.util;

/**
 * Class which includes constant values used widely in the code.
 *
 * @author mryakar
 */
public class Constant {

    /**
     * Main api path.
     */
    public static final String API_PATH = "/api";

    /**
     * Sub path for all http methods for vehicle manufacturer information.
     */
    public static final String API_PATH_VEHICLE_MANUFACTURERS = "/vehicleManufacturer";

    /**
     * Path variable text for brand name.
     */
    public static final String API_VARIABLE_PATH_BRAND_NAME = "brandName";

    /**
     * Error message for already existing vehicle manufacturer.
     */
    public static final String ERROR_MESSAGE_EXISTING_VM = "Brand name already exists";

    /**
     * Error message for not existing vehicle manufacturer.
     */
    public static final String ERROR_MESSAGE_NOT_EXISTING_VM = "Vehicle manufacturer does not exist";

    /**
     * Error message for not present related info.
     */
    public static final String ERROR_MESSAGE_RELATED_INFO_NOT_PRESENT = "Related info must be present";

    /**
     * Error message for empty brand name.
     */
    public static final String ERROR_MESSAGE_EMPTY_BRAND_NAME = "Brand name cannot be empty";

    /**
     * Error message for negative factor.
     */
    public static final String ERROR_MESSAGE_NEGATIVE_FACTOR = "Factor value cannot be less than 0";

    /**
     * Error message for null ID.
     */
    public static final String ERROR_MESSAGE_NULL_ID = "ID cannot be null";

    /**
     * Error message for not present update info.
     */
    public static final String ERROR_MESSAGE_UPDATE_INFO_NOT_PRESENT = "Update info must be present";

}