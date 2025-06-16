package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.model.Course;
import lv.venta.model.enums.CourseLevel;
import lv.venta.service.ICourseService;
import lv.venta.service.ILecturerService;

@Controller
@RequestMapping("/course/crud")
public class CourseController {

	@Autowired
	private ICourseService courseService;

	@Autowired
	private ILecturerService lecturerService;

	// get /course/crud/show/all
	@GetMapping("/all") // localhost:8080/course/crud/all
	public String getAllCourses(Model model) {
		try {
			model.addAttribute("package", courseService.selectAllCourses());
			return "course-all-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// get /course/crud/show/{id}
	@GetMapping("/{cid}") // localhost:8080/course/crud/show/{id}
	public String getCoursesById(@PathVariable("cid") int cId, Model model) {
		try {
			model.addAttribute("package", courseService.selectCourseById(cId));
			return "course-one-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// get /course/crud/remove/{id}
	@GetMapping("/remove/{cid}") // localhost:8080/course/crud/remove/2
	public String getCourseRemove(@PathVariable("cid") int cId, Model model) {
		try {
			courseService.deleteCourseById(cId);
			ArrayList<Course> allCourses = courseService.selectAllCourses();
			model.addAttribute("package", allCourses);
			return "course-all-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") // localhost:8080/course/crud/add
	public String getCourseAdd(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("levels", CourseLevel.values());
		model.addAttribute("lecturers", lecturerService.getAllLecturers());
		return "course-add-page";
	}

	@PostMapping("/add")
	public String postCourseAdd(@Valid Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("levels", CourseLevel.values());
			model.addAttribute("lecturers", lecturerService.getAllLecturers());
			return "course-add-page";
		}

		try {
			courseService.createNewCourse(course);
			return "redirect:/course/crud/all";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("levels", CourseLevel.values());
			model.addAttribute("lecturers", lecturerService.getAllLecturers());
			return "error-page";
		}
	}

	@GetMapping("/update/{cid}") // localhost:8080/course/crud/update
	public String getCourseUpdate(@PathVariable("cid") int cId, Model model) {
		try {

			Course courseForUpdating = courseService.selectCourseById(cId);
			model.addAttribute("course", courseForUpdating);
			model.addAttribute("cid", cId);
			return "course-update-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{cid}")
	public String postCourseUpdate(@PathVariable("cid") int cId, @Valid Course course, BindingResult result,
			Model model) {
		try {
			courseService.updateCourseById(cId, course);
			return "redirect:/course/crud/all/" + cId;
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

}
