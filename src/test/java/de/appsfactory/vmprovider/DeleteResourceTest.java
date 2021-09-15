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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteResourceTest {

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
    void testDeleteVehicleManufacturer() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS + "/" + vm1.getBrandName())
                                .accept(String.valueOf(MediaType.APPLICATION_JSON))
                )
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    @Order(2)
    void testDeleteNotExistingVehicleManufacturer() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS + "/VM6")
                                .accept(String.valueOf(MediaType.APPLICATION_JSON))
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_NOT_EXISTING_VM)
                )
                .andReturn();
    }


}