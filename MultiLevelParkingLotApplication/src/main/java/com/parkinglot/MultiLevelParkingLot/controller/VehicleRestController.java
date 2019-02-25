package com.parkinglot.MultiLevelParkingLot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.parkinglot.MultiLevelParkingLot.entity.Vehicle;
import com.parkinglot.MultiLevelParkingLot.service.VehicleService;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class VehicleRestController {

    @Autowired
    private VehicleService vehicleService;

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping(value= "/api/vehicles/id/{vehicleId}", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable(name="vehicleId")Long vehicleId) {
        Vehicle ob = new Vehicle();
        BeanUtils.copyProperties(vehicleService.getVehicleById(vehicleId), ob);
        return new ResponseEntity<Vehicle>(ob, HttpStatus.OK);
    }

    @GetMapping(value= "/api/vehicles/{licenseNumber}", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Vehicle> getVehicleByLicense(@PathVariable(name="licenseNumber")String licenseNumber) {
        Vehicle ob = new Vehicle();
        BeanUtils.copyProperties(vehicleService.getVehicleByLicenseNumber(licenseNumber), ob);
        return new ResponseEntity<Vehicle>(ob, HttpStatus.OK);
    }

    @GetMapping(value="/api/vehicles", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> responseVehiclesList = new ArrayList<>();

        List<Vehicle> vehicleList = vehicleService.getAllVehicles();
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle ob = new Vehicle();
            BeanUtils.copyProperties(vehicleList.get(i), ob);
            responseVehiclesList.add(ob);
        }
        return new ResponseEntity<List<Vehicle>>(responseVehiclesList, HttpStatus.OK);
    }

    @PostMapping(value= "/api/vehicle", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> addVehicle(@RequestBody Vehicle vehicleInfo, UriComponentsBuilder builder) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleInfo, vehicle);
        boolean flag = vehicleService.addVehicle(vehicle);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();    
        headers.setLocation(builder.path("/vehicle/{vehicleId}").buildAndExpand(vehicle.getVehicleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @DeleteMapping(value= "/api/vehicle/{licenseNumber}", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteVehicle(@PathVariable("licenseNumber") String licenseNumber) {
        Vehicle vehicle = vehicleService.getVehicleByLicenseNumber(licenseNumber);
        vehicleService.deleteVehicle(vehicle);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value= "/api/vehicle", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Vehicle> updateArticle(@RequestBody Vehicle vehicleInfo) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleInfo, vehicle);
        vehicleService.updateVehicle(vehicle);

        Vehicle ob = new Vehicle();
        BeanUtils.copyProperties(vehicle, ob);
        return new ResponseEntity<Vehicle>(ob, HttpStatus.OK);
    }


    @PostMapping(value="api/parkvehicle", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> parkVehicle(@RequestBody Vehicle vehicleInfo, UriComponentsBuilder builder) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleInfo, vehicle);
        boolean flag = vehicleService.parkVehicle(vehicle);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/parkvehicle/{vehicleId}").buildAndExpand(vehicle.getVehicleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value="api/unparkvehicle", produces= { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> unParkVehicle(@RequestBody Vehicle vehicleInfo, UriComponentsBuilder builder) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleInfo, vehicle);
        vehicleService.unParkVehicle(vehicle);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/unparkvehicle/{vehicleId}").buildAndExpand(vehicle.getVehicleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }
}
