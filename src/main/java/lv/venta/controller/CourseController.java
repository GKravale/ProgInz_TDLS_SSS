package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.service.ICourseService;

@Controller
@RequestMapping("/course/crud")
public class CourseController {

	@Autowired
	private ICourseService courseService;
	
	// get /course/crud/show/all
	@GetMapping("/all") //localhost:8080/course/crud/all
	public String getAllCourses(org.springframework.ui.Model model) {
		try {
			model.addAttribute("package", courseService.selectAllCourses());
			return "course-all-page";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
}
