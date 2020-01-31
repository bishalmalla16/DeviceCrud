package com.nitv.controller;

import com.nitv.entity.Device;
import com.nitv.error_handling.DeviceNotFoundException;
import com.nitv.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices/search/{search}")
    public Page<Device> getDevicesBySearch(@PathVariable String search,
               @RequestParam(value = "page", defaultValue = "1", required = false) int page,
               @RequestParam(value = "size", defaultValue = "20", required = false) int size){
        List<Device> devices = deviceService.getDeviceBySearch(search);
        return getPageImpl(devices, page, size);
    }

    @GetMapping("/devices")
    public Page<Device> getDevices(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
           @RequestParam(value = "size", required = false, defaultValue = "20") int size){

        return deviceService.getPagedDevices(PageRequest.of(page-1,size));
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
        List<Device> devices = deviceService.getFilteredDevices(mac, chipSet, build, manufacturer);
        return getPageImpl(devices, page, size);
    }

    @PostMapping("/devices")
    public void addDevice(@RequestBody @Valid Device device){
        deviceService.saveDevice(device);
    }

    private Page<Device> getPageImpl(List<Device> devices, int page, int size){
        int total = devices.size();
        Pageable pageable = PageRequest.of(page-1, size);

        int start = (int) pageable.getOffset();
        int end = Math.min((start+pageable.getPageSize()), total);
        List<Device> output = new ArrayList<>();
        if(start<=end){
            output = devices.subList(start, end);
        }

        return new PageImpl<>(output, pageable, devices.size());
    }

}
