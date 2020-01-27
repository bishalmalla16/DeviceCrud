package com.nitv.controller;

import com.nitv.entity.Device;
import com.nitv.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/")
    public String getDevices(Model model){
        Device device = new Device();
        model.addAttribute("device", device);
        return "index";
    }

    @GetMapping("/home")
    public String getAllDevices(Model model){
        List<Device> devices = deviceService.getDevices();
        model.addAttribute("devices", devices);
        Device device = new Device();
        model.addAttribute("device", device);
        return "home";
    }

    @GetMapping("/read/{id}")
    public String viewDevice(@PathVariable int id, Model model){
        Device device = deviceService.getDeviceById(id);
        if(device == null){
            model.addAttribute("deviceNotFound", "Device with ID " + id + " not found.");
            return "not_found";
        }
        model.addAttribute("device", device);
        return "read-device";
    }

    @GetMapping("/update/{id}")
    public String updateDevice(@PathVariable int id, Model model){
        Device device = deviceService.getDeviceById(id);
        if(device == null){
            model.addAttribute("deviceNotFound", "Device with ID " + id + " not found.");
            return "not_found";
        }
        model.addAttribute("device", device);
        return "update-device";
    }

    @GetMapping("/delete/{id}")
    public String deleteDevice(@PathVariable int id, Model model){
        Device device = deviceService.getDeviceById(id);
        if(device == null){
            model.addAttribute("deviceNotFound", "Device with ID " + id + " not found.");
            return "not_found";
        }
        deviceService.removeDevice(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/addDevice" , method = RequestMethod.POST)
    public String saveDevice(@ModelAttribute("device") @Valid Device device, BindingResult bindingResult, Model model){
        device.setMac(device.getMac().toUpperCase());
        if (bindingResult.hasErrors()){
            model.addAttribute("saveError", "Error while Adding. Please follow correct format.");
            return "index";
        }
        if(deviceService.getDeviceByMac(device.getMac())!=null){
            model.addAttribute("saveError", "Device with this MAC already exists");
            return "index";
        }
        deviceService.saveDevice(device);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateDevice" , method = RequestMethod.POST)
    public String updateDevice(@ModelAttribute("device") @Valid Device device, BindingResult bindingResult, Model model){
        device.setMac(device.getMac().toUpperCase());
        if (bindingResult.hasErrors()){
            model.addAttribute("updateError", "Error while Updating.");
            return "update-device";
        }
        Device device1 = deviceService.getDeviceById(device.getId());
        Device device2 = deviceService.getDeviceByMac(device.getMac());

        if(device2!=null && device1!=device2) {
            model.addAttribute("updateError", "Device with this MAC already exists");
            return "update-device";
        }
        deviceService.saveDevice(device);
        return "redirect:/";
    }
}
