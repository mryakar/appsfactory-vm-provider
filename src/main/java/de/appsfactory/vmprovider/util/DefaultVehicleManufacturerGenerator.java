package de.appsfactory.vmprovider.util;

import de.appsfactory.vmprovider.entity.VehicleManufacturer;
import de.appsfactory.vmprovider.repository.VehicleManufacturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Generates default vehicle manufacturers.
 *
 * @author mryakar
 */
@Component
@AllArgsConstructor
@Profile("active")
public class DefaultVehicleManufacturerGenerator implements CommandLineRunner {

    private final VehicleManufacturerRepository repository;

    @Override
    public void run(String... args) {
        persist("Mercedes Benz", 1.2);
        persist("BMW", 2.3);
        persist("Ford", 5.2);
        persist("Opel", 3.4);
        persist("Volvo", 1.1);
    }

    /**
     * Persist a vehicle manufacturer to the database.
     *
     * @param brandName Brand name.
     * @param factor    Factor value.
     */
    private void persist(String brandName, Double factor) {

        if (repository.existsByBrandName(brandName))
            return;

        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer();
        vehicleManufacturer.setBrandName(brandName);
        vehicleManufacturer.setFactor(factor);
        repository.save(vehicleManufacturer);
    }
}