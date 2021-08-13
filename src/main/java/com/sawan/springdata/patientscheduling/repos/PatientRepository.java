package com.sawan.springdata.patientscheduling.repos;

import org.springframework.data.repository.CrudRepository;

import com.sawan.springdata.patientscheduling.entities.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
