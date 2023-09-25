package com.locadora.carros.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;


@Entity
@Table(name = "TB_CARS")
public class CarModel extends RepresentationModel<CarModel> implements Serializable { // Serializable mostra pra JVM que essa é uma classe que esta habilitada a passar por serialização

    // Numero de controle de versão de cada uma das classes
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCar;
    private String name;
    private int year;
    private String color;
    private Boolean available;
    private BigDecimal dailyValue;


    public UUID getIdCar() {
        return idCar;
    }

    public void setIdCar(UUID idCar) {
        this.idCar = idCar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public BigDecimal getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(BigDecimal dailyValue) {
        this.dailyValue = dailyValue;
    }
}
