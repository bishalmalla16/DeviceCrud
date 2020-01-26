//package com.nitv.controller;
//
//import com.nitv.entity.Device;
//import com.nitv.service.DeviceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class HomeController {
//    @Autowired
//    private DeviceService deviceService;
//
//    @GetMapping("/devices")
//    public List<Device> getAllDevices(){
//        return deviceService.getDevices();
//    }
//
//    @GetMapping("/devices/{id}")
//    public Device getDevice(@PathVariable )
//}
