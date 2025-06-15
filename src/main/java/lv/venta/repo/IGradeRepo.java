package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Grade;

public interface IGradeRepo extends JpaRepository<Grade, Integer> {

}
