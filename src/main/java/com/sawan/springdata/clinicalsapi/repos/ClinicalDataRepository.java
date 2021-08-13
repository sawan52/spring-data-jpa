package com.sawan.springdata.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawan.springdata.clinicalsapi.model.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

}
