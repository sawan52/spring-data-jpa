package com.sawan.springdata.patientscheduling;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sawan.springdata.patientscheduling.entities.Appointment;
import com.sawan.springdata.patientscheduling.entities.Doctor;
import com.sawan.springdata.patientscheduling.entities.Insurance;
import com.sawan.springdata.patientscheduling.entities.Patient;
import com.sawan.springdata.patientscheduling.repos.AppointmentRepository;
import com.sawan.springdata.patientscheduling.repos.DoctorRepository;
import com.sawan.springdata.patientscheduling.repos.PatientRepository;

@SpringBootTest
class PatientschedulingApplicationTests {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Test
	void testCreateDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("Pradum");
		doctor.setLastName("Singh");
		doctor.setSpeciality("Physician");
		doctorRepository.save(doctor);
	}

	@Test
	void testCreatePatient() {
		Patient patient = new Patient();
		patient.setFirstName("Rama");
		patient.setLastName("Gopal");
		patient.setPhone("9856325601");

		Insurance insurance = new Insurance();
		insurance.setProviderName("Bajaj Alliance");
		insurance.setCopay(2000d);
		patient.setInsurance(insurance);

		Doctor doctor = doctorRepository.findById(1L).get();
		List<Doctor> doctorsList = Arrays.asList(doctor);
		patient.setDoctors(doctorsList);

		patientRepository.save(patient);
	}

	@Test
	void testCreateAppointment() {
		Appointment appointment = new Appointment();
		Timestamp appointmentTime = new Timestamp(new Date().getTime());
		appointment.setAppointmentTime(appointmentTime);
		appointment.setReason("I have fever");
		appointment.setStarted(true);
		appointment.setEnded(false);

		appointment.setPatient(patientRepository.findById(1L).get());
		appointment.setDoctor(doctorRepository.findById(1L).get());

		appointmentRepository.save(appointment);
	}

}
