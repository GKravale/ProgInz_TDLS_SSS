package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

			}

		};
	}
}
