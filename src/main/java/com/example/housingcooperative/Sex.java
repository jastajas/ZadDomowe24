package com.example.housingcooperative;

public enum Sex {
    FEMALE("Kobieta"),
    MALE("Mężczyzna");

    public String polishSex;

    Sex(String polishSex) {
        this.polishSex = polishSex;
    }

    public String getPolishSex() {
        return polishSex;
    }
}
