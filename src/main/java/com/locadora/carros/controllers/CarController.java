package com.locadora.carros.controllers;


import com.locadora.carros.dtos.CarRecordDto;
import com.locadora.carros.models.CarModel;
import com.locadora.carros.repositories.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/cars")
    public ResponseEntity<CarModel> saveCar(@RequestBody @Valid CarRecordDto carRecordDto) {
        var carModel = new CarModel();
        BeanUtils.copyProperties(carRecordDto, carModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(carModel));
}
    @GetMapping("/cars")
    public ResponseEntity<List<CarModel>> getAllCars() {
        List<CarModel> cars = carRepository.findAll();
        if(!cars.isEmpty()) {
            for(CarModel car : cars) {
                UUID id = car.getIdCar();
                car.add(linkTo(methodOn(CarController.class).getCarById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Object> getCarById(@PathVariable(value="id") UUID id){
        Optional<CarModel> car0 = carRepository.findById(id);
        if(car0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        car0.get().add(linkTo(methodOn(CarController.class).getAllCars()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(car0.get());
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Object> updateCarById(@RequestBody @Valid CarRecordDto carRecordDto,@PathVariable(value="id") UUID id){
        Optional<CarModel> car0 = carRepository.findById(id);
        var carModel = car0.get();
        BeanUtils.copyProperties(carRecordDto, carModel);
        if(car0.isPresent()) {
            carRepository.save(carModel);
            return ResponseEntity.ok().body("Car updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
    }
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Object> deleteCarById(@PathVariable(value="id") UUID id){
        Optional<CarModel> car0 = carRepository.findById(id);
        if(car0.isPresent()) {
            carRepository.deleteById(id);
            return ResponseEntity.ok().body("Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
    }

}