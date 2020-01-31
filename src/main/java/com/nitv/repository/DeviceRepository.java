package com.nitv.repository;

import com.nitv.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Optional<Device> findByMac(String mac);
    List<Device> findAllByMacContainingOrBuildContainingOrChipSetContainingOrManufacturerContaining(String mac, String chipSet, String build, String manufacturer);

//    @Query("select d from Device d where mac ...")

    List<Device> findAllByMacContainingAndChipSetContainingAndBuildContainingAndManufacturerContaining(String mac, String chipSet, String build, String manufacturer);


}
