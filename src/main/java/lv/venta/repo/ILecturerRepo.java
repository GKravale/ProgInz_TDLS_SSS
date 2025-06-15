package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.model.Lecturer;

public interface ILecturerRepo extends JpaRepository<Lecturer, Integer> {

}
