package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Course;

public interface ICourseService {
	
	public abstract ArrayList<Course> selectAllCourses() throws Exception;
	
	public abstract Course selectCourseByID(int cId) throws Exception;
	
	public abstract void deleteCourseByID(int cId) throws Exception;
	
	public abstract void insertNewCourse(Course course) throws Exception;
	
	public abstract void updateCourseByID(int cId, Course course) throws Exception;
	
}
