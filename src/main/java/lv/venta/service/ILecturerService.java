package lv.venta.service;

import java.util.List;
import java.util.Optional;

import lv.venta.model.Lecturer;

public interface ILecturerService {

	List<Lecturer> getAllLecturers();

	Optional<Lecturer> getLecturerById(int lId);

	Lecturer createLecturer(Lecturer lecturer);

	Lecturer updateLecturer(int lId, Lecturer updatedLecturer);

	boolean deleteLecturer(int lId);

}
