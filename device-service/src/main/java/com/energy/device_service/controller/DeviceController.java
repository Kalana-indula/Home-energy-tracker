package com.energy.device_service.controller;

import com.energy.device_service.dto.DeviceDto;
import com.energy.device_service.dto.response.SingleEntityResponse;
import com.energy.device_service.entity.Device;
import com.energy.device_service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/devices")
@CrossOrigin(origins = "*")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

//    Create device
    @PostMapping
    public ResponseEntity<?> createDevice(@RequestBody Device device){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(deviceService.createDevice(device));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

//    get device by id
    @GetMapping("{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id){
        SingleEntityResponse<DeviceDto> response=deviceService.getDeviceById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update device
    @PutMapping("{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody Device device){
        SingleEntityResponse<DeviceDto> response=deviceService.updateDevice(id, device);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    methods
//    get device by id
 //  create device
 // update device
 // delete device
}
