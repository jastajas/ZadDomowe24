package com.example.housingcooperative;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlatRepository extends JpaRepository<Flat,Long> {


    List<Flat>findAllByHousingCooperative_IdHC(Long idHC);

    @Query("SELECT SUM(flat.flatArea) FROM Flat flat WHERE flat.housingCooperative = :hc")
    double sumFlatAreaByIdHC(@Param("hc") HousingCooperative hc);

}
