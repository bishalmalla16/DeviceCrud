package com.nitv.service;

import com.nitv.entity.Device;
import com.nitv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
