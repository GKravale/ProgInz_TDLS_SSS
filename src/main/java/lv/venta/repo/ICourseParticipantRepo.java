package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.venta.model.CourseParticipant;

public interface ICourseParticipantRepo extends JpaRepository<CourseParticipant, Integer>{

}
