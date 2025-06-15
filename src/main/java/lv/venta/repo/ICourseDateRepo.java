package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.venta.model.CourseDate;


public interface ICourseDateRepo extends JpaRepository<CourseDate, Integer>{

}
