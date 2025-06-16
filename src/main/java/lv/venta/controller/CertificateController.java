package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lv.venta.model.Certificate;
import lv.venta.model.enums.CourseResult;
import lv.venta.service.ICertificateService;

@Controller
@RequestMapping("/certificate")
public class CertificateController {

	@Autowired
	private ICertificateService certificateService;

	// Get - /certificate/show/all
	@GetMapping("/show/all")
	public String getControllerAllCertificates(Model model) {
		try {
			model.addAttribute("certificates", certificateService.getAllCertificates());
			return "all-certificate-page"; // all-certificate-page.html
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page"; // error-page.html
		}
	}

	// Get - /certificate/show/{crtId}
	@GetMapping("/show/{crtId}")
	public String getControllerOneCertificateById(@PathVariable(name = "crtId") int crtId, Model model) {
		try {
			Certificate oneCertificate = certificateService.getCertificateById(crtId);
			model.addAttribute("certificate", oneCertificate);
			return "one-certificate-page"; // one-certificate-page.html
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

	// Get - /certificate/remove/{crtId}
	@GetMapping("/remove/{crtId}")
	public String getControllerRemoveCertificateById(@PathVariable(name = "crtId") int crtId, Model model) {
		try {
			certificateService.deleteCertificate(crtId);
			model.addAttribute("certificates", certificateService.getAllCertificates());
			return "all-certificate-page"; // all-certificate-page.html
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

	// Get - /certificate/add
	@GetMapping("/add")
	public String getControllerAddCertificate(Model model) {
		model.addAttribute("certificate", new Certificate());
		model.addAttribute("courseResults", CourseResult.values());
		return "add-certificate-page"; // add-certificate-page.html
	}

	// Post - /certificate/add
	@PostMapping("/add")
	public String postControllerAddCertificate(@RequestParam("cpId") int cpId, @RequestParam("cdId") int cdId,
			@RequestParam("tId") int tId, @RequestParam("result") CourseResult result, Model model) {
		try {
			certificateService.createCertificate(cpId, cdId, tId, result);
			return "redirect:/certificate/show/all";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

	// Get - /certificate/update/{crtId}
	@GetMapping("/update/{crtId}")
	public String getControllerUpdateCertificateById(@PathVariable(name = "crtId") int crtId, Model model) {
		try {
			Certificate foundCertificate = certificateService.getCertificateById(crtId);
			model.addAttribute("certificate", foundCertificate);
			return "update-certificate-page"; // update-certificate-page.html
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}

	// Post - /certificate/update/{crtId}
	@PostMapping("/update/{crtId}")
	public String postControllerUpdateCertificateById(@RequestParam("participantName") String participantName,
			@RequestParam("participantSurname") String participantSurname,
			@RequestParam("courseTitle") String courseTitle, @RequestParam("courseHours") int courseHours,
			@RequestParam("tId") int tId, @RequestParam("completionDate") String completionDate,
			@RequestParam("newGradeValue") int newGradeValue, @PathVariable(name = "crtId") int crtId, Model model) {
		try {
			certificateService.updateCertificate(crtId, participantName, participantSurname, courseTitle, courseHours,
					tId, java.time.LocalDate.parse(completionDate), newGradeValue);
			return "redirect:/certificate/show/all";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
	}
}