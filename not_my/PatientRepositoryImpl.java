package com.inobitec.ris.patient.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inobitec.ris.patient.beans.PatientBean;
import com.inobitec.ris.patient.beans.PatientLiteBean;
import com.inobitec.ris.patient.dao.mappers.PatientMapper;

/**
 * Implements all method for work with DB.
 */
@Repository
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private PatientMapper patientMapper;

	@Override
	public PatientBean selectPatient(int id) {
		return patientMapper.selectPatient(id);
	}

	@Override
	@Transactional
	public PatientBean insertPatient(PatientBean patient) {
		patientMapper.insertPatient(patient);
		return patient;
	}

	@Override
	@Transactional
	public void updatePatient(int id, PatientBean patient) {
		patientMapper.updatePatient(id, patient);		
	}

	@Override
	@Transactional
	public void deletePatient(int id) {
		patientMapper.deletePatient(id);
	}

	@Override
	public List<PatientLiteBean> selectPatientSuggestions(String pattern) {
		return patientMapper.selectPatientSuggestions(pattern);
	}
    
}
