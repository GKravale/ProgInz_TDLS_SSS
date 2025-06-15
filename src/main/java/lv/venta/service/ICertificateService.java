package lv.venta.service;

import java.time.LocalDate;
import java.util.List;

import lv.venta.model.Certificate;
import lv.venta.model.enums.CourseResult;

public interface ICertificateService {

	Certificate getCertificateById(int crtId) throws Exception;

	List<Certificate> getAllCertificates();

	Certificate updateCertificate(int crtId, String participantName, String participantSurname, String courseTitle,
			int courseHours, int tId, LocalDate completionDate, int newGradeValue) throws Exception;

	void deleteCertificate(int crtId) throws Exception;

	Certificate createCertificate(int participantId, int courseDateId, int templateId, CourseResult result)
			throws Exception;

}
