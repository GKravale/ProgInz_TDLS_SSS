package lv.venta.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Course;
import lv.venta.repo.ICourseRepo;
import lv.venta.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private ICourseRepo courseRepo;
	
	@Override
	public ArrayList<Course> selectAllCourses() throws Exception {
		if(courseRepo.count() == 0) {
			throw new Exception("courseRepo ir tukša!");
		}
		return (ArrayList<Course>) courseRepo.findAll();
	}

	@Override
	public Course selectCourseByID(int cId) throws Exception {
		if(cId < 0) {
			throw new Exception("Kursa ID nevar būt negatīvs!");
		}
		
		if(!courseRepo.existsById(cId)) {
			throw new Exception("Kurss ar tādu ID nepastāv!");
		}
		
		return courseRepo.findById(cId).get();
	}

	@Override
	public void deleteCourseByID(int cId) throws Exception {
		if (cId < 0) {
            throw new Exception("Kusa ID jābūt pozitīvam!");
        }
        // 
		
	}

	@Override
	public void createNewCourse(Course course) throws Exception {
		if(courseRepo.existsById(course.getCId())) {
			throw new Exception("Kurss ar tādu ID jau eksistē!");
		}
		
		courseRepo.save(course);
		
	}

	@Override
	public void updateCourseByID(int cId, Course course) throws Exception {
		Course update = selectCourseByID(cId);
		update.setTitle(course.getTitle());
		update.setDescription(course.getDescription());
		update.setHours(course.getHours());
		update.setCourseLevel(course.getCourseLevel());
		update.setLecturer(course.getLecturer());
		update.setCourseDates(course.getCourseDates());
		
		courseRepo.save(update);
		
	}

	
}
