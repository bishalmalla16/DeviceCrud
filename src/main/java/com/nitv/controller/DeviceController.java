package com.nitv.controller;

import com.nitv.entity.Device;
import com.nitv.error_handling.DeviceNotFoundException;
import com.nitv.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public Page<Device> getDevices(
            @RequestParam(value = "search", defaultValue = "", required = false) String search,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
           @RequestParam(value = "size", required = false, defaultValue = "20") int size){
        return deviceService.getDeviceBySearch(search, PageRequest.of(page-1, size));
    }

    @GetMapping("/devices/{id}")
    public Device getDevice(@PathVariable int id){
        Device device = deviceService.getDeviceById(id);
        if(device == null)
            throw new DeviceNotFoundException("Device with id " + id + " not found.");
        else return device;
    }

    @GetMapping("/getDevices")
    public Page<Device> getFilteredDevices(
            @RequestParam(required = false,defaultValue = "") String mac,
            @RequestParam(required = false,defaultValue = "") String chipSet,
            @RequestParam(required = false,defaultValue = "") String build,
            @RequestParam(required = false,defaultValue = "") String manufacturer,
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size){
        return deviceService.getFilteredDevices(mac, chipSet, build, manufacturer, PageRequest.of(page-1, size));
    }

    @PostMapping("/devices")
    public void addDevice(@RequestBody @Valid Device device){
        deviceService.saveDevice(device);
    }

}
