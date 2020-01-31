package com.nitv.controller;

import com.nitv.entity.Device;
import com.nitv.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TableController {
    @Autowired
    private DeviceService deviceService;

    Logger logger = LoggerFactory.getLogger(TableController.class);

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
//    public String getDevices(@RequestParam(value = "page", required = false, defaultValue = "1") int page ,@RequestParam(value = "size", required = false, defaultValue = "5") int size , Model model){
//        Page<Device> devices = deviceService.getPagedDevices(PageRequest.of(page-1, size));
//        model.addAttribute("devices", devices);
//        Device device = new Device();
//        model.addAttribute("device", device);
//        model.addAttribute("currentPage", page);
//        return "index";
//    }


}
