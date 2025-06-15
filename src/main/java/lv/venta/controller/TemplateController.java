package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		@GetMapping("/all/{tid}") // localhost":8080/template/crud/all/1
		public String getTemplatesById(@PathVariable("cid") int tId, Model model) {
			try {
				model.addAttribute("package", templateService.selectTemplateById(tId));
				return "template-one-page";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
	
}
