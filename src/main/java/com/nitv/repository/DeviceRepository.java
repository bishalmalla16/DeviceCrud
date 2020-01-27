package com.nitv.repository;

import com.nitv.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Optional<Device> findByMac(String mac);
    List<Device> findAllByMacContainingOrBuildContainingOrChipSetContainingOrManufacturerContaining(String search1, String search2, String search3, String search4);

}
