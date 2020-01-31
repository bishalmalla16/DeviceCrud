package com.nitv.service;

import com.nitv.entity.Device;
import com.nitv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    public Page<Device> getPagedDevices(PageRequest pageRequest){
        return deviceRepository.findAll(pageRequest);
    }

    public Page<Device> getDeviceBySearch(String search, Pageable pageable){
//        return deviceRepository.findAllByMacContainingOrBuildContainingOrChipSetContainingOrManufacturerContaining(search, search, search, search);
        return deviceRepository.findAllByMacContainingOrBuildContainingOrChipSetContainingOrManufacturerContaining(search, search, search, search, pageable);
    }

    public Device getDeviceById(int id){
        Optional<Device> device = deviceRepository.findById(id);
        return device.orElse(null);
    }

    public Device getDeviceByMac(String mac){
        Optional<Device> device = deviceRepository.findByMac(mac);
        return device.orElse(null);
    }

    public void saveDevice(Device device) {
        deviceRepository.save(device);
    }

    public void removeDevice(int id) {
        deviceRepository.deleteById(id);
    }

    public Page<Device> getFilteredDevices(String mac, String chipSet, String build, String manufacturer, Pageable pageable) {
        return deviceRepository.findAllByMacContainingAndChipSetContainingAndBuildContainingAndManufacturerContaining(mac, chipSet, build, manufacturer, pageable);
//
//        Device device = new Device();
//        device.setId(3);      //Unsolved Error
//        device.setMac(mac);
//        device.setChipSet(chipSet);
//        device.setBuild(build);
//        device.setManufacturer(manufacturer);
//        return deviceRepository.findAll(Example.of(device));
    }
}
