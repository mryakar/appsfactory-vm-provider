package de.appsfactory.vmprovider.repository;

import de.appsfactory.vmprovider.entity.VehicleManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Hibernate repository interface for vehicle manufacturers.
 *
 * @author mryakar
 */
@Repository
public interface VehicleManufacturerRepository extends JpaRepository<VehicleManufacturer, Long> {

    Boolean existsByBrandName(String brandName);

    VehicleManufacturer findByBrandName(String brandName);

    void deleteByBrandName(String brandName);
}