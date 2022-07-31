package com.inobitec.ris.patient.dao.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.inobitec.ris.patient.beans.PatientBean;
import com.inobitec.ris.patient.beans.PatientLiteBean;

@Mapper
public interface PatientMapper {

	public PatientBean selectPatient(@Param("id") int id);

	public void insertPatient(PatientBean patient);

	public void updatePatient(@Param("id") int id, @Param("p") PatientBean patient);

	public void deletePatient(@Param("id") int id);
	
	public List<PatientLiteBean> selectPatientSuggestions(@Param("pattern") String pattern);

}
