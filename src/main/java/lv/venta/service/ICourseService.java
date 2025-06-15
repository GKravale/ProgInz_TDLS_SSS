package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Course;
import lv.venta.model.Lecturer;
import lv.venta.model.enums.CourseLevel;

public interface ICourseService {
	// CRUD - create, read, update and delete

	// create 
	public abstract void createCourse(Course course) throws Exception;
	
	// read
	public abstract ArrayList<Course> selectAllCourses() throws Exception;
	
	public abstract ArrayList<Course> selectCourseByTitle(String title) throws Exception;
	
	public abstract ArrayList<Course> selectCourseById(int cId) throws Exception;
	
	public abstract ArrayList<Course> selectCourseByLevel(CourseLevel courseLevel) throws Exception;
	
	public abstract ArrayList<Course> selectCourseByHours(Course hours) throws Exception;
	
	public abstract ArrayList<Course> selectCourseByLecturerId(int lId) throws Exception;
	
	public abstract ArrayList<Course> selectCourseByLecturer(Lecturer lecturer) throws Exception;
	
	// update
	public abstract void udpateCourseById(int cId) throws Exception;
	
	public abstract void updateCourseByTitle(String title) throws Exception;
	
	public abstract void updateCourseByLevel(CourseLevel courseLevel) throws Exception;
	
	public abstract void updateCourseByHours(Course hours) throws Exception;
	
	// public abstract void updateCourseByLecturerId(int lId) throws Excpetion;
	
	// delete
	public abstract void deleteCourseById(int cId) throws Exception;
	
}
