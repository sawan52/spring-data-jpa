package com.sawan.springdata.patientscheduling.repos;

import org.springframework.data.repository.CrudRepository;

import com.sawan.springdata.patientscheduling.entities.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

}