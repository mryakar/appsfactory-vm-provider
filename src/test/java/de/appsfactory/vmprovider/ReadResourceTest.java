package de.appsfactory.vmprovider;

import de.appsfactory.vmprovider.entity.VehicleManufacturer;
import de.appsfactory.vmprovider.repository.VehicleManufacturerRepository;
import de.appsfactory.vmprovider.util.Constant;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReadResourceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    VehicleManufacturerRepository repository;

    VehicleManufacturer vm1;
    VehicleManufacturer vm2;
    VehicleManufacturer vm3;
    VehicleManufacturer vm4;
    VehicleManufacturer vm5;

    @BeforeAll
    void setup() {
        vm1 = persistVehicleManufacturer("VM1", 12.2);
        vm2 = persistVehicleManufacturer("VM2", 13.2);
        vm3 = persistVehicleManufacturer("VM3", 15.723);
        vm4 = persistVehicleManufacturer("VM4", 1.2);
        vm5 = persistVehicleManufacturer("VM5", 12.65);
    }

    @AfterAll
    void dispose() {
        repository.deleteAll();
    }

    @Transactional
    VehicleManufacturer persistVehicleManufacturer(
            String brandName,
            Double factor
    ) {
        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer();
        vehicleManufacturer.setBrandName(brandName);
        vehicleManufacturer.setFactor(factor);
        return repository.save(vehicleManufacturer);
    }

    @Test
    @Order(1)
    void testGetAllVehicleManufacturers() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(
                        jsonPath(
                                "$[*].brandName",
                                containsInAnyOrder(
                                        vm1.getBrandName(),
                                        vm2.getBrandName(),
                                        vm3.getBrandName(),
                                        vm4.getBrandName(),
                                        vm5.getBrandName()
                                )
                        ))
                .andReturn();
    }
}
