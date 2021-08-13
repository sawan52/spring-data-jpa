package com.sawan.springdata.patientscheduling.repos;

import org.springframework.data.repository.CrudRepository;

import com.sawan.springdata.patientscheduling.entities.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
