package de.appsfactory.vmprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.appsfactory.vmprovider.dto.VehicleManufacturerDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateResourceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    VehicleManufacturerRepository repository;

    @Autowired
    ObjectMapper mapper;

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
    void testUpdateVehicleManufacturerBrandName() throws Exception {

        VehicleManufacturerDto dto = new VehicleManufacturerDto();
        dto.setId(vm1.getId());
        dto.setBrandName("VM2_CHANGED");


        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.brandName").value("VM2_CHANGED")
                )
                .andReturn();

    }

    @Test
    @Order(2)
    void testUpdateVehicleManufacturerExistingBrandName() throws Exception {

        VehicleManufacturerDto dto = new VehicleManufacturerDto();
        dto.setId(vm1.getId());
        dto.setBrandName("VM2");


        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_EXISTING_VM)
                )
                .andReturn();

    }

    @Test
    @Order(3)
    void testUpdateVehicleManufacturerNotPresentRelatedInfo() throws Exception {

        VehicleManufacturerDto dto = new VehicleManufacturerDto();
        dto.setId(vm1.getId());

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_UPDATE_INFO_NOT_PRESENT)
                )
                .andReturn();

    }


    @Test
    @Order(4)
    void testUpdateVehicleManufacturerWithoutID() throws Exception {

        VehicleManufacturerDto dto = new VehicleManufacturerDto();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_NULL_ID)
                )
                .andReturn();

    }

    @Test
    @Order(5)
    void testUpdateVehicleManufacturerWithEmptyBrandName() throws Exception {

        VehicleManufacturerDto dto = new VehicleManufacturerDto();
        dto.setId(vm1.getId());
        dto.setBrandName("");


        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_EMPTY_BRAND_NAME)
                )
                .andReturn();

    }

    @Test
    @Order(6)
    void testUpdateVehicleManufacturerWithNegativeFactor() throws Exception {

        VehicleManufacturerDto dto = new VehicleManufacturerDto();
        dto.setId(vm1.getId());
        dto.setFactor(-1.1);


        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_NEGATIVE_FACTOR)
                )
                .andReturn();

    }

    @Test
    @Order(6)
    void testUpdateVehicleManufacturerNotExistingId() throws Exception {

        VehicleManufacturerDto dto = new VehicleManufacturerDto();
        dto.setId(1000L);
        dto.setFactor(1.1);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_NOT_EXISTING_VM)
                )
                .andReturn();

    }
}
