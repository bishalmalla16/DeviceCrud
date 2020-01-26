package com.nitv.controller;

import com.nitv.entity.Device;
import com.nitv.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices/{search}")
    public List<Device> getDevicesBySearch(@PathVariable String search){
        return deviceService.getDeviceBySearch(search);
    }

    @GetMapping("/devices")
    public List<Device> getDevices(){
        return deviceService.getDevices();
    }

}
