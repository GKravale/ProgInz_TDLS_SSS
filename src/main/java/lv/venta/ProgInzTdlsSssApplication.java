package lv.venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Certificate;
import lv.venta.model.Course;
import lv.venta.model.CourseDate;
import lv.venta.model.CourseParticipant;
import lv.venta.model.Grade;
import lv.venta.model.Lecturer;
import lv.venta.model.Template;
import lv.venta.model.enums.CourseLevel;
import lv.venta.model.enums.CourseResult;
import lv.venta.repo.ICertificateRepo;
import lv.venta.repo.ICourseDateRepo;
import lv.venta.repo.ICourseParticipantRepo;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.ILecturerRepo;
import lv.venta.repo.ITemplateRepo;

@SpringBootApplication
public class ProgInzTdlsSssApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzTdlsSssApplication.class, args);
	}

	@Bean
	public CommandLineRunner tdlsSssDB(ICertificateRepo certificateRepo, ICourseDateRepo courseDateRepo,
			ICourseParticipantRepo participantRepo, ICourseRepo courseRepo, IGradeRepo gradeRepo,
			ILecturerRepo lecturerRepo, ITemplateRepo templateRepo) {

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {

				// Lecturers
				Lecturer l1 = new Lecturer("Dre", "Berzins");
				Lecturer l2 = new Lecturer("Laura", "Siera");
				Lecturer l3 = new Lecturer("Ralfs", "Mobile");

				lecturerRepo.saveAll(List.of(l1, l2, l3));

				// Course
				Course c1 = new Course("JAVA", "adfeufhadls", 13, CourseLevel.Intermediate, l1);
				Course c2 = new Course("Python", "lalalala", 8, CourseLevel.Beginner, l3);
				Course c3 = new Course("Testing", "fafa", 12, CourseLevel.Junior, l2);

				courseRepo.saveAll(List.of(c1, c2, c3));

				// Course date

				CourseDate cd1 = new CourseDate(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15), c3);
				CourseDate cd2 = new CourseDate(LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 10), c1);

				courseDateRepo.saveAll(List.of(cd1, cd2));

				// Course participant

				CourseParticipant cp1 = new CourseParticipant("Arvis", "Arvitis", "arvitis1232gmail.com",
						"+37112345678", "Brivibas 21", 14, "Riga", "LV-1000", "Latvija");
				CourseParticipant cp2 = new CourseParticipant("Darvis", "Darvitis", "darvitis1232@gmail.com",
						"+37122345678", "Nebrivibas 21", 14, "Riga", "LV-1000", "Latvija");

				participantRepo.saveAll(List.of(cp1, cp2));
				
				
				// course dates ar participantiem

				cp1.getCourseDates().add(cd1);
				cd1.getParticipants().add(cp1);

				cp2.getCourseDates().add(cd1);
				cd1.getParticipants().add(cp2);

				cp2.getCourseDates().add(cd2);
				cd2.getParticipants().add(cp2);

				participantRepo.saveAll(List.of(cp1, cp2));
				
				// template
				
				Template t1 = new Template("Veidne", "Veidneee", null);
				
				templateRepo.save(t1);
				
				//grade
				
				Grade g1 = new Grade(10, LocalDate.of(2025, 8, 1), cd2, cp1);
				
				gradeRepo.save(g1);
				
				// certificate
				
				Certificate cert1 = new Certificate(CourseResult.Certificate, c3, g1, cp2, t1);
				
				certificateRepo.save(cert1);
				
				
				
			
			}

		};
	}
}
