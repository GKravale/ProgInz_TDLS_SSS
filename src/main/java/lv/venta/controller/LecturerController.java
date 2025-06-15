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
import lv.venta.model.Lecturer;

@Controller
@RequestMapping("/lecturer/crud")
public class LecturerController {
	
	@Autowired
	private ILecturerService lecturerService;

	
		@GetMapping("/all") // localhost:8080/lecturer/crud/all
		public String getAllLecturers(Model model) {
			try {
				model.addAttribute("package", lecturerService.selectAllLecturers());
				return "lecturer-all-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@GetMapping("/all/{lid}") // localhost:8080/lecturer/crud/all/1
		public String getLecturersById(@PathVariable("lid") int lId, Model model) {
			try {
				model.addAttribute("package", lecturerService.selectCourseById(cId));
				return "lecturer-one-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@GetMapping("/remove/{lid}") // localhost:8080/lecturer/crud/remove/2
		public String getLecturerRemove(@PathVariable("lid") int lId, Model model) {
			try {
				lecturerService.deleteLecturerById(lId);
				ArrayList<Lecturer> allLecturers = lecturerService.selectAllLecturers();
				model.addAttribute("package", allLecturers);
				return "lecturer-all-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@GetMapping("/add") // localhost:8080/lecturer/crud/add
		public String getLecturerAdd(Model model) {
			model.addAttribute("lecturer", new Lecturer());
			return "lecturer-add-page";
		}
		
		@PostMapping("/add") 
		public String postLecturerAdd(@Valid Lecturer lecturer, BindingResult result) {
			if(result.hasErrors()) {
				return "lecturer-add-page";
			} else {
				try {
					lecturerService.createNewLecturer(lecturer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return "redirect:/lecturer/crud/all";
			}
		}
		
		@GetMapping("/update/{cid}") // localhost:8080/lecturer/crud/update
		public String getLecturerUpdate(@PathVariable("lid") int lId, Model model) {
			try {
				
				Lecturer lecturerForUpdating = lecturerService.selectLecturerById(lId);
				model.addAttribute("lecturer", lecturerForUpdating);
				model.addAttribute("lid", lId);
				return "lecturer-update-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@PostMapping("/update/{cid}")
		public String postLecturerUpdate(@PathVariable("lid") int lId, @Valid Lecturer lecturer, BindingResult result, Model model) {
			try {
				lecturerService.updateLecturerById(lId, lecturer);
				return "redirect:/lecturer/crud/all/"+lId;
			} catch (Exception e) {
				model.addAttribute("package",e.getMessage());
				return "error-page";
			}
		}
	
}
