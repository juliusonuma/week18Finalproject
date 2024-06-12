package book.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.shop.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Long> {

}
