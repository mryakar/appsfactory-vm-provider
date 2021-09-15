package de.appsfactory.vmprovider.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer class to transfer vehicle manufacturer information.
 *
 * @author mryakar
 */
@Getter
@Setter
public class VehicleManufacturerDto {
    private Long id;
    private String brandName;
    private Double factor;
}
