package lv.venta.service.Impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lv.venta.model.Certificate;
import lv.venta.model.Course;
import lv.venta.model.CourseDate;
import lv.venta.model.CourseParticipant;
import lv.venta.model.Grade;
import lv.venta.model.Template;
import lv.venta.repo.ICertificateRepo;
import lv.venta.repo.ICourseDateRepo;
import lv.venta.repo.ICourseParticipantRepo;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.ITemplateRepo;
import lv.venta.service.ICertificateService;

@Service
public class CertificateServiceImpl implements ICertificateService {

	@Autowired
	private ICertificateRepo certificateRepo;

	@Autowired
	private ICourseRepo courseRepo;

	@Autowired
	private ICourseParticipantRepo participantRepo;

	@Autowired
	private ITemplateRepo templateRepo;

	@Autowired
	private ICourseDateRepo courseDateRepo;

	@Autowired
	private IGradeRepo gradeRepo;

	// CREATE
	
	public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
	
	 public List<Template> getAllTemplates() {
	        return templateRepo.findAll();
	    }
	 
	 public Map<String, Object> prepareCertificateGeneration(int cId, int tId, LocalDate completionDate) {
	        Course course = courseRepo.findById(cId)
	                .orElseThrow(() -> new RuntimeException("Kurss ar ID " + cId + " nav atrasts"));
	        
	        Template template = templateRepo.findById(tId)
	                .orElseThrow(() -> new RuntimeException("Veidne ar ID " + tId + " nav atrasta"));
	        
	        Map<String, Object> generationData = new HashMap<>();
	        generationData.put("course", course);
	        generationData.put("template", template);
	        generationData.put("completionDate", completionDate);
	        generationData.put("participantCount", 0);
	        
	        return generationData;
	    }
	 
	 	// Dalibnieku ielade
	 


	// RETRIEVE

	@Override
	public Certificate getCertificateById(int crtId) throws Exception {
		return certificateRepo.findById(crtId)
				.orElseThrow(() -> new Exception("Sertifikāts ar ID " + crtId + " nav atrasts"));
	}

	// RETRIEVE ALL

	@Override
	public List<Certificate> getAllCertificates() {
		return certificateRepo.findAll();
	}

	// UPDATE

	@Override
	public Certificate updateCertificate(int crtId, String participantName, String participantSurname,
			String courseTitle, int courseHours, int tId, LocalDate completionDate, int newGradeValue)
			throws Exception {

		Certificate certificate = getCertificateById(crtId);

		// Dalibnieka dati

		CourseParticipant participant = certificate.getParticipant();
		participant.setName(participantName);
		participant.setSurname(participantSurname);
		participantRepo.save(participant);

		// Kursa dati
		Course course = certificate.getCourse();
		course.setTitle(courseTitle);
		course.setHours(courseHours);
		courseRepo.save(course);

		// Veidne

		Template template = templateRepo.findById(tId)
				.orElseThrow(() -> new RuntimeException("Veidne ar ID " + tId + " nav atrasta"));
		certificate.setTemplate(template);

		// Kursa datums

		CourseDate courseDate = certificate.getCourseDate();
		courseDate.setEndingDate(completionDate);
		courseDateRepo.save(courseDate);

		// Atzime

		Grade grade = certificate.getGrade();
		grade.setGrade(newGradeValue);
		gradeRepo.save(grade);

		return certificateRepo.save(certificate);
	}
	
	//DELETE
	
	@Transactional
	@Override
	 public void deleteCertificate(int crtId) throws Exception {
	        Certificate certificate = getCertificateById(crtId);

	        certificateRepo.delete(certificate);
	 }
	
	// TOGGLE PARAKSTITS
	
	// TOGGLE NOSUTITS
	
	 
}
