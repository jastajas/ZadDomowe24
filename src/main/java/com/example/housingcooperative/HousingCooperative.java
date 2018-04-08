package com.example.housingcooperative;

import javax.persistence.*;
import java.util.List;

@Entity
public class HousingCooperative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHC;

    private String name;

    private String adressStreet;

    private String adressNo;

    @Enumerated(EnumType.STRING)
    private City city;

    private String adressCode;
    @OneToMany(mappedBy = "housingCooperative")
    private List<Flat> flatList;


    public String getAdressStreet() {
        return adressStreet;
    }

    public void setAdressStreet(String adressStreet) {
        this.adressStreet = adressStreet;
    }

    public String getAdressNo() {
        return adressNo;
    }

    public void setAdressNo(String adressNo) {
        this.adressNo = adressNo;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAdressCode() {
        return adressCode;
    }

    public void setAdressCode(String adressCode) {
        this.adressCode = adressCode;
    }


    public long getIdHC() {
        return idHC;
    }

    public void setId(long idHC) {
        this.idHC = idHC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flat> getFlatList() {
        return flatList;
    }

    public void setFlatList(List<Flat> flatList) {
        this.flatList = flatList;
    }
}
