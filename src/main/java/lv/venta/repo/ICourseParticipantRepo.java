package lv.venta.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.venta.model.CourseParticipant;
import lv.venta.model.Template;

public interface ICourseParticipantRepo extends JpaRepository<CourseParticipant, Integer>{

	Optional<Template> findByNameAndSurname(String name, String surname);

}
