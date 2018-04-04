package com.example.housingcooperative;

public enum City {

    BYDGOSZCZ("Bydgoszcz"),
    CHORZOW("Chorzów"),
    GLIWICE("Gliwice"),
    GDANSK("Gdańsk"),
    GDYNA("Gdynia"),
    KATOWICE("Katowice"),
    KRAKOW("Kraków"),
    OLSZTYN("Olsztyn"),
    OPOLE("Opole"),
    POZNAN("Poznań"),
    SZCZECIN("Szczecin"),
    TORUN("Toruń"),
    WARSZAWA("Warszawa"),
    WROCLAW("Wrocław");

    private String polishName;

    City(String polishName) {
        this.polishName = polishName;
    }

    public String getPolishName() {
        return polishName;
    }
}
