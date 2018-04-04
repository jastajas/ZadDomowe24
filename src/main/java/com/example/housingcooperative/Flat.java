package com.example.housingcooperative;

import javax.persistence.*;
import java.util.List;

@Entity
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idF;
    @Column(name = "flat_number")
    private String flatNo;
    private double flatArea;
    private int residentsNumber;
    @ManyToOne
    private HousingCooperative housingCooperative;

    @OneToMany//(mappedBy = "resident")
    private List<Resident> residents;

    public long getIdF() {
        return idF;
    }

    public void setIdF(long idF) {
        this.idF = idF;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public double getFlatArea() {
        return flatArea;
    }

    public void setFlatArea(double flatArea) {
        this.flatArea = flatArea;
    }

    public int getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(int residentsNumber) {
        this.residentsNumber = residentsNumber;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public HousingCooperative getHousingCooperative() {
        return housingCooperative;
    }

    public void setHousingCooperative(HousingCooperative housingCooperative) {
        this.housingCooperative = housingCooperative;
    }
}
