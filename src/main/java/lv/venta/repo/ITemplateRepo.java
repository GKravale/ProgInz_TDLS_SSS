package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.venta.model.Template;

public interface ITemplateRepo extends JpaRepository<Template, Integer>{

}
