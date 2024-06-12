package book.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.shop.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
