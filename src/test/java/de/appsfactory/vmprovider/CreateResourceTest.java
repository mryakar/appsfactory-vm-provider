package de.appsfactory.vmprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.appsfactory.vmprovider.dto.NewVehicleManufacturerDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateResourceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    VehicleManufacturerRepository repository;


    @AfterAll
    void dispose() {
        repository.deleteAll();
    }


    @Test
    @Order(1)
    void testCreateNonExistingVehicleManufacturer() throws Exception {

        NewVehicleManufacturerDto dto = new NewVehicleManufacturerDto();
        dto.setBrandName("TEST1");
        dto.setFactor(15.2);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.brandName").value(dto.getBrandName())
                )
                .andReturn();
    }


    @Test
    @Order(2)
    void testCreateExistingVehicleManufacturer() throws Exception {

        VehicleManufacturer entity = new VehicleManufacturer();
        entity.setBrandName("TEST2");
        entity.setFactor(15.2);
        repository.save(entity);

        NewVehicleManufacturerDto dto = new NewVehicleManufacturerDto();
        dto.setBrandName("TEST2");
        dto.setFactor(15.2);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
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
    void testCreateVehicleManufacturerWithoutBrandName() throws Exception {

        NewVehicleManufacturerDto dto = new NewVehicleManufacturerDto();
        dto.setBrandName("");
        dto.setFactor(15.2);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
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
    @Order(4)
    void testCreateVehicleManufacturerWhenBrandNameIsNull() throws Exception {

        NewVehicleManufacturerDto dto = new NewVehicleManufacturerDto();
        dto.setFactor(15.2);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_RELATED_INFO_NOT_PRESENT)
                )
                .andReturn();
    }


    @Test
    @Order(5)
    void testCreateVehicleManufacturerWhenFactorIsNull() throws Exception {

        NewVehicleManufacturerDto dto = new NewVehicleManufacturerDto();
        dto.setBrandName("TEST5");

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_RELATED_INFO_NOT_PRESENT)
                )
                .andReturn();
    }


    @Test
    @Order(6)
    void testCreateVehicleManufacturerWhenFactorIsNegative() throws Exception {

        NewVehicleManufacturerDto dto = new NewVehicleManufacturerDto();
        dto.setBrandName("TEST6");
        dto.setFactor(-1.0);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(Constant.API_PATH + Constant.API_PATH_VEHICLE_MANUFACTURERS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.message").value(Constant.ERROR_MESSAGE_NEGATIVE_FACTOR)
                )
                .andReturn();
    }
}
