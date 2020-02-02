package com.nitv.repository;

import com.nitv.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Optional<Device> findByMac(String mac);

    Page<Device> findAllByMacContainingOrBuildContainingOrChipSetContainingOrManufacturerContaining(String mac, String chipSet, String build, String manufacturer, Pageable pageable);

//    @Query("select d from Device d where mac ...")

    Page<Device> findAllByMacContainingAndChipSetContainingAndBuildContainingAndManufacturerContaining(String mac, String chipSet, String build, String manufacturer, Pageable pageable);

}
