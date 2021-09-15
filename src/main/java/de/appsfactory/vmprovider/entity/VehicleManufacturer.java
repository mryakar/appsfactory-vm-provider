package de.appsfactory.vmprovider.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity class to represent vehicle manufacturers.
 *
 * @author mryakar
 */
@Entity
@Getter
@Setter
@Table(name = "vehicle_manufacturer")
public class VehicleManufacturer {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigint", name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Vehicle brand.
     */
    @Column(name = "brand_name", nullable = false, unique = true)
    private String brandName;

    /**
     * Type factor value.
     */
    @Column(name = "factor", nullable = false)
    private Double factor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleManufacturer that = (VehicleManufacturer) o;
        return Objects.equals(getBrandName(), that.getBrandName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrandName());
    }
}