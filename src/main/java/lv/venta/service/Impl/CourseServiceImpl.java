package lv.venta.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Course;
import lv.venta.model.Lecturer;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.ILecturerRepo;
import lv.venta.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseRepo courseRepo;

	@Autowired
	private ILecturerRepo lecturerRepo;

	@Override
	public ArrayList<Course> selectAllCourses() throws Exception {
		if (courseRepo.count() == 0) {
			throw new Exception("courseRepo ir tukša!");
		}
		return (ArrayList<Course>) courseRepo.findAll();
	}

	@Override
	public Course selectCourseById(int cId) throws Exception {
		if (cId < 0) {
			throw new Exception("Kursa ID nevar būt negatīvs!");
		}

		if (!courseRepo.existsById(cId)) {
			throw new Exception("Kurss ar tādu ID nepastāv!");
		}

		return courseRepo.findById(cId).get();
	}

	@Override
	public void deleteCourseById(int cId) throws Exception {
		if (cId < 0) {
			throw new Exception("Kusa ID jābūt pozitīvam!");
		}

		if (!courseRepo.existsById(cId)) {
			throw new Exception("Kurss ar tādu ID nepastāv!");
		}

		courseRepo.deleteById(cId);

	}

	@Override
	public void createNewCourse(Course course) throws Exception {
	    if (course == null) {
	        throw new Exception("Kurss nedrīkst būt null.");
	    }
	    
	    if (course.getLecturer() == null) {
	        throw new Exception("Lektors ir jānorāda.");
	    }
	    
	    Lecturer lecturer = lecturerRepo.findById(course.getLecturer().getLId())
	            .orElseThrow(() -> new Exception("Lektors ar norādīto ID neeksistē"));
	    course.setLecturer(lecturer);

	    if (course.getTitle() == null || course.getTitle().isBlank()) {
	        throw new Exception("Kursa nosaukums ir obligāts.");
	    }

	    if (course.getHours() <= 0) {
	        throw new Exception("Kursa stundu skaitam jābūt pozitīvam.");
	    }

	    if (course.getCourseLevel() == null) {
	        throw new Exception("Kursa līmenis ir jānorāda.");
	    }

	    courseRepo.save(course);
	}

	@Override
	public void updateCourseById(int cId, Course course) throws Exception {
		Course update = selectCourseById(cId);
		update.setTitle(course.getTitle());
		update.setDescription(course.getDescription());
		update.setHours(course.getHours());
		update.setCourseLevel(course.getCourseLevel());
		update.setLecturer(course.getLecturer());

		courseRepo.save(update);

	}

}
