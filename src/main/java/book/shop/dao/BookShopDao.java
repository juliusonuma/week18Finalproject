package book.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.shop.entity.BookShop;

public interface BookShopDao extends JpaRepository<BookShop, Long> {

}
