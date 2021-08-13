package com.sawan.springdata.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawan.springdata.clinicalsapi.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
