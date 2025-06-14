package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CourseParticipant;

public interface ICourseParticipantRepo extends CrudRepository<CourseParticipant, Integer>{

}
