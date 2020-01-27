package com.nitv.controller;

import com.nitv.entity.Device;
import com.nitv.error_handling.DeviceNotFoundException;
import com.nitv.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TableController {
    @Autowired
    private DeviceService deviceService;

    //Get All Devices via Search Parameter

//    @GetMapping("/")
//    public String getDevices(@RequestParam(value = "search", required = false, defaultValue = "") String search ,Model model){
//        List<Device> devices;
//        if (search.isEmpty())
//            devices = deviceService.getDevices();
//        else
//            devices = deviceService.getDeviceBySearch(search);
//        model.addAttribute("search", search);
//        model.addAttribute("devices", devices);
//        Device device = new Device();
//        model.addAttribute("device", device);
//        return "index";
//    }


    // Thymeleaf Pagination for the Device Table

//    @GetMapping("/")
//    public String getDevices(@RequestParam(value = "page", required = false, defaultValue = "1") int page ,Model model){
//        Page<Device> devices = deviceService.getPagedDevices(PageRequest.of(page-1, 5));
//        model.addAttribute("devices", devices);
//        Device device = new Device();
//        model.addAttribute("device", device);
//        model.addAttribute("currentPage", page);
//        return "index";
//    }

    @GetMapping("/devices/search/{search}")
    public List<Device> getDevicesBySearch(@PathVariable String search){
        return deviceService.getDeviceBySearch(search);
    }

    @GetMapping("/devices")
    public List<Device> getDevices(){
        return deviceService.getDevices();
    }

    @GetMapping("/devices/{id}")
    public Device getDevice(@PathVariable int id){
        Device device = deviceService.getDeviceById(id);
        if(device == null)
            throw new DeviceNotFoundException("Device with id " + id + " not found.");
        else return device;
    }

    @PostMapping("/devices")
    public void addDevice(@RequestBody @Valid Device device){
        deviceService.saveDevice(device);
    }

}
