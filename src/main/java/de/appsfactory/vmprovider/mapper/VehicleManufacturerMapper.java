package de.appsfactory.vmprovider.mapper;

import de.appsfactory.vmprovider.dto.NewVehicleManufacturerDto;
import de.appsfactory.vmprovider.dto.VehicleManufacturerDto;
import de.appsfactory.vmprovider.entity.VehicleManufacturer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class which maps vehicle manufacturer object to facto DTOs.
 *
 * @author mryakar
 */
@Component
public class VehicleManufacturerMapper {

    /**
     * Mapper method which creates FactorDto object from VehicleManufacturer object.
     *
     * @param vehicleManufacturer VehicleManufacturer object
     * @return FactorDto object.
     */
    public VehicleManufacturerDto toDto(VehicleManufacturer vehicleManufacturer) {
        VehicleManufacturerDto vehicleManufacturerDto = new VehicleManufacturerDto();
        vehicleManufacturerDto.setId(vehicleManufacturer.getId());
        vehicleManufacturerDto.setBrandName(vehicleManufacturer.getBrandName());
        vehicleManufacturerDto.setFactor(vehicleManufacturer.getFactor());
        return vehicleManufacturerDto;
    }

    /**
     * Mapper method which converts entity list to dto list.
     *
     * @param vehicleManufacturerList Entity list.
     * @return Dto list.
     */
    public List<VehicleManufacturerDto> toDto(List<VehicleManufacturer> vehicleManufacturerList) {
        return vehicleManufacturerList.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Mapper method which converts dto to entity.
     *
     * @param vehicleManufacturerDto VehicleManufacturer object
     * @return FactorDto object.
     */
    public VehicleManufacturer toEntity(VehicleManufacturerDto vehicleManufacturerDto) {
        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer();
        vehicleManufacturer.setId(vehicleManufacturerDto.getId());
        vehicleManufacturer.setBrandName(vehicleManufacturerDto.getBrandName());
        vehicleManufacturer.setFactor(vehicleManufacturerDto.getFactor());
        return vehicleManufacturer;
    }

    /**
     * Mapper method which creates FactorDto object from VehicleManufacturer object.
     *
     * @param newVehicleManufacturerDto VehicleManufacturer object
     * @return FactorDto object.
     */
    public VehicleManufacturer toEntityFromNew(NewVehicleManufacturerDto newVehicleManufacturerDto) {
        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer();
        vehicleManufacturer.setBrandName(newVehicleManufacturerDto.getBrandName());
        vehicleManufacturer.setFactor(newVehicleManufacturerDto.getFactor());
        return vehicleManufacturer;
    }
}