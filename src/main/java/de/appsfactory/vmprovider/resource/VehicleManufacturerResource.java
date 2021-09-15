package de.appsfactory.vmprovider.resource;

import de.appsfactory.vmprovider.dto.NewVehicleManufacturerDto;
import de.appsfactory.vmprovider.dto.VehicleManufacturerDto;
import de.appsfactory.vmprovider.service.VehicleManufacturerService;
import de.appsfactory.vmprovider.util.Constant;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class to provide corresponding CRUD operations for vehicle manufacturers.
 *
 * @author mryakar
 */
@RestController
@RequestMapping(Constant.API_PATH)
@AllArgsConstructor
public class VehicleManufacturerResource {

    private final VehicleManufacturerService service;

    @PostMapping(Constant.API_PATH_VEHICLE_MANUFACTURERS)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "newVehicleManufacturerDto",
                    value = "Vehicle manufacturer information is sent as JSON.",
                    required = true,
                    paramType = "body",
                    dataTypeClass = NewVehicleManufacturerDto.class
            )
    })
    @ApiOperation(value = "Adds new vehicle manufacturer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vehicle manufacturer created"),
            @ApiResponse(code = 409, message = "Brand name already exists"),
            @ApiResponse(code = 422, message = "Undefined brand name or factor value.")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody
    ResponseEntity<VehicleManufacturerDto> create(@RequestBody NewVehicleManufacturerDto newVehicleManufacturerDto) {
        return new ResponseEntity<>(service.create(newVehicleManufacturerDto), HttpStatus.CREATED);
    }

    @GetMapping(Constant.API_PATH_VEHICLE_MANUFACTURERS)
    @ApiOperation(value = "Retrieves all vehicle manufacturers.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<VehicleManufacturerDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(Constant.API_PATH_VEHICLE_MANUFACTURERS + "/{" + Constant.API_VARIABLE_PATH_BRAND_NAME + "}")
    @ApiOperation(value = "Retrieves vehicle manufacturer by brand name.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Vehicle manufacturer cannot be found")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<VehicleManufacturerDto> getByBrandName(@PathVariable(Constant.API_VARIABLE_PATH_BRAND_NAME) String brandName) {
        return new ResponseEntity<>(service.getByBrandName(brandName), HttpStatus.OK);
    }

    @PutMapping(Constant.API_PATH_VEHICLE_MANUFACTURERS)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "vehicleManufacturerDto",
                    value = "Updated vehicle manufacturer information is sent as JSON.",
                    required = true,
                    paramType = "body",
                    dataTypeClass = VehicleManufacturerDto.class
            )
    })
    @ApiOperation(value = "Updates vehicle manufacturer information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle manufacturer updated"),
            @ApiResponse(code = 404, message = "Vehicle manufacturer could not be found"),
            @ApiResponse(code = 409, message = "Brand name already exists"),
            @ApiResponse(code = 422, message = "Undefined brand name or factor value.")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<VehicleManufacturerDto> update(@RequestBody VehicleManufacturerDto vehicleManufacturerDto) {
        return new ResponseEntity<>(service.update(vehicleManufacturerDto), HttpStatus.OK);
    }

    @DeleteMapping(Constant.API_PATH_VEHICLE_MANUFACTURERS + "/{" + Constant.API_VARIABLE_PATH_BRAND_NAME + "}")
    @ApiOperation(value = "Deletes vehicle manufacturer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle manufacturer deleted"),
            @ApiResponse(code = 404, message = "Vehicle manufacturer cannot be found")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@PathVariable(Constant.API_VARIABLE_PATH_BRAND_NAME) String brandName) {
        service.delete(brandName);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}