package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.venta.model.Course;

public interface ICourseRepo extends JpaRepository<Course, Integer> {

}
