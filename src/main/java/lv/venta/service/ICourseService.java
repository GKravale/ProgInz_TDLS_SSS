package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Course;

public interface ICourseService {

	public abstract ArrayList<Course> selectAllCourses() throws Exception;
	
	// public abstract Course selectCourseByTitle(String title) throws Exception;
	
	public abstract Course selectCourseById(int cId) throws Exception;
	
	public abstract void createCourse(Course course) throws Exception;
	
	public abstract void udpateCourseById(int cId) throws Exception;
	
	public abstract void deleteCourseByID(int cId) throws Exception;
	
}
