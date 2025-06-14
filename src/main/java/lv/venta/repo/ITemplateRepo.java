package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Template;

public interface ITemplateRepo extends CrudRepository<Template, Integer>{

}
