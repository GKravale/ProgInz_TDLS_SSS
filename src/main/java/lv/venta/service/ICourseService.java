package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Course;

public interface ICourseService {
	
	public abstract ArrayList<Course> selectAllCourses() throws Exception;
	
	public abstract Course selectCourseById(int cId) throws Exception;
	
	public abstract void deleteCourseById(int cId) throws Exception;
	
	public abstract void createNewCourse(Course course) throws Exception;
	
	public abstract void updateCourseById(int cId, Course course) throws Exception;
	
}
