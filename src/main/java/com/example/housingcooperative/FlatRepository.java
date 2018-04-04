package com.example.housingcooperative;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlatRepository extends JpaRepository<Flat,Long> {


    List<Flat>findAllByHousingCooperative_IdHC(Long idHC);

}
