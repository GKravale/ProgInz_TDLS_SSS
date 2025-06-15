package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import lv.venta.model.Certificate;

public interface ICertificateRepo extends JpaRepository<Certificate, Integer> {

}
