package lv.venta.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import lv.venta.model.CourseDate;
import lv.venta.model.CourseParticipant;
import lv.venta.model.Grade;

public interface IGradeRepo extends JpaRepository<Grade, Integer> {

    Optional<Grade> findByParticipantAndCourseDate(CourseParticipant participant, CourseDate courseDate);

}