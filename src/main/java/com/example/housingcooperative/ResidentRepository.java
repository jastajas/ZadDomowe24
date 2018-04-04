package com.example.housingcooperative;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident,Long> {

    List<Resident> findAllByFlat_IdF(Long idF);

}
