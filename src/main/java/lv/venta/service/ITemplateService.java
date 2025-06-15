package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Template;

public interface ITemplateService {

	// CRUD
	
	//create
	public abstract void createTemplate(Template template) throws Exception;
	
	// read
	public abstract ArrayList<Template> selectAllTemplates() throws Exception;
	
	public abstract Template selectTemplateById(int tId) throws Exception;
	
	// update
	public abstract void updateTemplateById(int tId, Template template) throws Exception;
	
	// delete
	public abstract void deleteTemplateById(int tId) throws Exception;
}
