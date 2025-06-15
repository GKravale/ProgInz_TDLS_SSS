package lv.venta.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Lecturer;
import lv.venta.repo.ILecturerRepo;
import lv.venta.service.ILecturerService;

@Service
public class LecturerServiceImpl implements ILecturerService {

	@Autowired
	private ILecturerRepo lecturerRepo;

	// retrieve
	public List<Lecturer> getAllLecturers() {
		return lecturerRepo.findAll();
	}

	// retrieve all
	public Optional<Lecturer> getLecturerById(int lId) {
		return lecturerRepo.findById(lId);
	}

	// create
	public Lecturer createLecturer(Lecturer lecturer) {
		return lecturerRepo.save(lecturer);
	}

	// update
	public Lecturer updateLecturer(int lId, Lecturer updatedLecturer) {
		Optional<Lecturer> existingLecturer = lecturerRepo.findById(lId);
		if (existingLecturer.isPresent()) {
			Lecturer lecturer = existingLecturer.get();
			lecturer.setName(updatedLecturer.getName());
			lecturer.setSurname(updatedLecturer.getSurname());
			return lecturerRepo.save(lecturer);
		}
		return null;
	}

	// delete
	public boolean deleteLecturer(int lId) {
		if (lecturerRepo.existsById(lId)) {
			lecturerRepo.deleteById(lId);
			return true;
		}
		return false;
	}
}
