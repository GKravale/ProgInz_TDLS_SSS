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
import lv.venta.model.Template;
import lv.venta.service.ITemplateService;

@Controller
@RequestMapping("/template/crud")
public class TemplateController {
	

		@Autowired
		private ITemplateService templateService;
		
		@GetMapping("/all") // localhost:8080/template/crud/all
		public String getAllTemplates(Model model) {
			try {
				model.addAttribute("package", templateService.selectAllTemplates());
				return "template-all-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@GetMapping("/{tid}") // localhost:8080/template/crud/1
		public String getTemplatesById(@PathVariable("tid") int tId, Model model) {
			try {
				model.addAttribute("package", templateService.selectTemplateById(tId));
				return "template-one-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@GetMapping("/remove/{tid}") // localhost:8080/template/crud/remove/2
		public String getCourseRemove(@PathVariable("tid") int tId, Model model) {
			try {
				templateService.deleteTemplateById(tId);
				ArrayList<Template> allTemplates = templateService.selectAllTemplates();
				model.addAttribute("package", allTemplates);
				return "template-all-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@GetMapping("/add") // localhost:8080/template/crud/add
		public String getTemplateAdd(Model model) {
			model.addAttribute("template", new Template());
			return "template-add-page";
		}
		
		@PostMapping("/add") // localhost:8080/template/crud/add
		public String postTemplateAdd(@Valid Template template, BindingResult result) {
			if(result.hasErrors()) {
				return "template-add-page";
			} else {
				try {
					templateService.createTemplate(template);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return "redirect:/template/crud/all";
			}
		}
		
		@GetMapping("/update/{tid}") // localhost:8080/template/crud/update
		public String getTemplateUpdate(@PathVariable("tid") int tId, Model model) {
			try {
				
				Template templateForUpdating = templateService.selectTemplateById(tId);
				model.addAttribute("template", templateForUpdating);
				model.addAttribute("tid", tId);
				return "template-update-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		
		@PostMapping("/update/{cid}")
		public String postTemplateUpdate(@PathVariable("tid") int tId, @Valid Template template, BindingResult result, Model model) {
			try {
				templateService.updateTemplateById(tId, template);
				return "redirect:/template/crud/all/"+tId;
			} catch (Exception e) {
				model.addAttribute("package",e.getMessage());
				return "error-page";
			}
		}
	
}
