package lv.venta.service.Impl;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lv.venta.model.Certificate;
import lv.venta.model.Course;
import lv.venta.model.CourseDate;
import lv.venta.model.CourseParticipant;
import lv.venta.model.Grade;
import lv.venta.model.Template;
import lv.venta.model.enums.CourseResult;
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
	
	@Override
	public Certificate createCertificate(int cpId, int cdId, int tId, CourseResult result) throws Exception {

	    CourseParticipant participant = participantRepo.findById(cpId)
	        .orElseThrow(() -> new Exception("Dalībnieks ar ID " + cpId + " nav atrasts"));

	    CourseDate courseDate = courseDateRepo.findById(cdId)
	        .orElseThrow(() -> new Exception("Kursa datums ar ID " + cdId + " nav atrasts"));

	    Course course = courseDate.getCourse();

//	    Template template = templateRepo.findById(tId)
//	        .orElseThrow(() -> new Exception("Veidne ar ID " + tId + " nav atrasta"));
	    
	    Grade grade = gradeRepo.findByParticipantAndCourseDate(participant, courseDate)
	            .orElseThrow(() -> new Exception("Atzīme dalībniekam " + participant.getName() + " " + 
	                                           participant.getSurname() + " kursa datumam nav atrasta"));

	    Certificate certificate = new Certificate();
	    certificate.setResult(result);
	    certificate.setCourse(course);
	    certificate.setParticipant(participant);
	  //  certificate.setTemplate(template);
	    certificate.setResult(result);
	    certificate.setCourseDate(courseDate);
	    certificate.setGrade(grade);
	    certificate.setSigned(false);
	    certificate.setSent(false);

	    Certificate savedCertificate = certificateRepo.save(certificate);
	    
	    return savedCertificate;
	}

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

	// DELETE

	@Transactional
	@Override
	public void deleteCertificate(int crtId) throws Exception {
		Certificate certificate = getCertificateById(crtId);

		certificateRepo.delete(certificate);
	}

	// TOGGLE PARAKSTITS

	// TOGGLE NOSUTITS

}
