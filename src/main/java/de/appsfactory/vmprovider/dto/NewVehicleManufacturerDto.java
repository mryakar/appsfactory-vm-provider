package de.appsfactory.vmprovider.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer class to transfer new vehicle manufacturer information.
 *
 * @author mryakar
 */
@Getter
@Setter
public class NewVehicleManufacturerDto {
    private String brandName;
    private Double factor;
}
