package lv.venta.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Template;
import lv.venta.repo.ITemplateRepo;
import lv.venta.service.ITemplateService;

@Service
public class TemplateServiceImpl implements ITemplateService{

	@Autowired
	private ITemplateRepo templateRepo;
	
	@Override
	public ArrayList<Template> selectAllTemplates() throws Exception {
		if(templateRepo.count() == 0) {
			throw new Exception("templateRepo ir tukša!");
		}
		return (ArrayList<Template>) templateRepo.findAll();
	}

	@Override
	public Template selectTemplateById(int tId) throws Exception {
		if(tId < 0) {
			throw new Exception("Veidnes ID nevar būt negatīvs!");
		}
		
		if(!templateRepo.existsById(tId)) {
			throw new Exception("Veidne ar tādu ID nepastāv!");
		}
		
		return templateRepo.findById(tId).get();
	}
	
	@Override
	public void createTemplate(Template template) throws Exception {
		if(templateRepo.existsById(template.getTId())) {
			throw new Exception("Veidne ar tādu ID jau eksistē!");
		}
		
		templateRepo.save(template);
		
	}

	@Override
	public void updateTemplateById(int tId, Template template) throws Exception {
		Template update = selectTemplateById(tId);
		update.setTitle(template.getTitle());
		update.setDescription(template.getDescription());
		update.setPdfData(template.getPdfData());
		
		templateRepo.save(update);
		
	}

	@Override
	public void deleteTemplateById(int tId) throws Exception {
		if(tId < 0) {
	        throw new Exception("Veidnes ID nevar būt negatīvs!");
	    }

	    if(!templateRepo.existsById(tId)) {
	        throw new Exception("Veidne ar tādu ID nepastāv!");
	    }

	    templateRepo.deleteById(tId);
		
	}

}
