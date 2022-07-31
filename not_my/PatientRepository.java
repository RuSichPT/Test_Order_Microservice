package com.inobitec.ris.patient.dao;

import java.util.List;

import com.inobitec.ris.patient.beans.PatientBean;
import com.inobitec.ris.patient.beans.PatientLiteBean;

/**
 * Describes all methods for working with DB.
 */
public interface PatientRepository {
	public PatientBean selectPatient(int id);
	public PatientBean insertPatient(PatientBean patient);
	public void updatePatient(int id, PatientBean patient);
	public void deletePatient(int id);
	public List<PatientLiteBean> selectPatientSuggestions(String pattern);
}
