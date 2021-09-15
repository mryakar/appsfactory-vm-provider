package de.appsfactory.vmprovider.service;

import de.appsfactory.vmprovider.dto.NewVehicleManufacturerDto;
import de.appsfactory.vmprovider.dto.VehicleManufacturerDto;
import de.appsfactory.vmprovider.entity.VehicleManufacturer;
import de.appsfactory.vmprovider.mapper.VehicleManufacturerMapper;
import de.appsfactory.vmprovider.repository.VehicleManufacturerRepository;
import de.appsfactory.vmprovider.service.exception.VehicleManufacturerAlreadyExistsException;
import de.appsfactory.vmprovider.service.exception.VehicleManufacturerDoesNotExistException;
import de.appsfactory.vmprovider.util.Constant;
import de.appsfactory.vmprovider.util.ObjectUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class to provide the business logic for vehicle manufacturers.
 *
 * @author mryakar
 */
@Service
@AllArgsConstructor
public class VehicleManufacturerService {

    private final VehicleManufacturerRepository repository;
    private final VehicleManufacturerMapper mapper;
    private final ObjectUtil objectUtil;

    /**
     * Persist a new vehicle manufacturer to the database.
     *
     * @param newVehicleManufacturerDto New vehicle manufacturer information.
     * @return Created vehicle manufacturer information.
     */
    public VehicleManufacturerDto create(NewVehicleManufacturerDto newVehicleManufacturerDto) {
        String brandName = newVehicleManufacturerDto.getBrandName();
        Double factor = newVehicleManufacturerDto.getFactor();
        if (brandName == null || factor == null) {
            throw new IllegalStateException(Constant.ERROR_MESSAGE_RELATED_INFO_NOT_PRESENT);
        }
        if (brandName.length() == 0) {
            throw new IllegalStateException(Constant.ERROR_MESSAGE_EMPTY_BRAND_NAME);
        }
        if (factor < 0) {
            throw new IllegalStateException(Constant.ERROR_MESSAGE_NEGATIVE_FACTOR);
        }
        if (repository.existsByBrandName(brandName)) {
            throw new VehicleManufacturerAlreadyExistsException();
        }
        VehicleManufacturer entity = mapper.toEntityFromNew(newVehicleManufacturerDto);
        return mapper.toDto(repository.save(entity));
    }

    /**
     * Get all vehicle manufacturers.
     *
     * @return Vehicle manufacturer information list.
     */
    public List<VehicleManufacturerDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    /**
     * Get the vehicle manufacturer by its brand name.
     *
     * @param brandName Vehicle manufacturer brand name.
     * @return Vehicle manufacturer information.
     */
    public VehicleManufacturerDto getByBrandName(String brandName) {
        if (!repository.existsByBrandName(brandName)) {
            throw new VehicleManufacturerDoesNotExistException();
        }
        return mapper.toDto(repository.findByBrandName(brandName));
    }

    /**
     * Update the vehicle manufacturer.
     *
     * @param vehicleManufacturerDto Update information.
     * @return Updated vehicke manufacturer information.
     */
    public VehicleManufacturerDto update(VehicleManufacturerDto vehicleManufacturerDto) {
        String brandName = vehicleManufacturerDto.getBrandName();
        Double factor = vehicleManufacturerDto.getFactor();
        Long id = vehicleManufacturerDto.getId();
        if (vehicleManufacturerDto.getId() == null) {
            throw new IllegalStateException(Constant.ERROR_MESSAGE_NULL_ID);
        }
        if (brandName == null && factor == null) {
            throw new IllegalStateException(Constant.ERROR_MESSAGE_UPDATE_INFO_NOT_PRESENT);
        }
        if (brandName != null && brandName.length() == 0) {
            throw new IllegalStateException(Constant.ERROR_MESSAGE_EMPTY_BRAND_NAME);
        }
        if (factor != null && factor < 0) {
            throw new IllegalStateException(Constant.ERROR_MESSAGE_NEGATIVE_FACTOR);
        }
        if (!repository.existsById(id)) {
            throw new VehicleManufacturerDoesNotExistException();
        }
        if (repository.existsByBrandName(brandName)) {
            throw new VehicleManufacturerAlreadyExistsException();
        }
        VehicleManufacturer vehicleManufacturer = repository.findById(id).get();
        objectUtil.copyNonNullProperties(mapper.toEntity(vehicleManufacturerDto), vehicleManufacturer);
        return mapper.toDto(repository.save(vehicleManufacturer));
    }

    /**
     * Delete vehicle manufacturer by brand name.
     *
     * @param brandName Vehicle manufacturer brand name.
     */
    @Transactional
    public void delete(String brandName) {
        if (!repository.existsByBrandName(brandName)) {
            throw new VehicleManufacturerDoesNotExistException();
        }
        repository.deleteByBrandName(brandName);
    }
}