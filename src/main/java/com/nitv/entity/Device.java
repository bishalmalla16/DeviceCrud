package com.nitv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 16, max = 17)
    @Pattern(regexp = "((\\d|([a-f]|[A-F])){2}:){5}(\\d|([a-f]|[A-F])){2}", message = "Follow the correct Format")
    private String mac;

    @NotNull
    @Size(min = 1, max = 30)
    private String chipSet;

    @NotNull
    @Size(min = 1, max = 10)
    @Pattern(regexp = "(\\d+\\.)+(\\d+)", message = "follow the build format")
    private String build;

    @NotNull
    @Size(min = 1, max = 30)
    private String manufacturer;

    public Device() {
    }

    public int getId() {
        return id;
    }

    public String getMac() {
        return mac;
    }

    public String getChipSet() {
        return chipSet;
    }

    public String getBuild() {
        return build;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setChipSet(String chipSet) {
        this.chipSet = chipSet;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
