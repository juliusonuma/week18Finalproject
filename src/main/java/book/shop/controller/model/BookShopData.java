package book.shop.controller.model;

import java.util.HashSet;
import java.util.Set;

import book.shop.entity.BookShop;
import book.shop.entity.Customer;
import book.shop.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookShopData {

	private Long bookShopId;
	private String bookShopName;
	private String bookShopAddress;
	private String bookShopCity;
	private String bookShopState;
	private String bookShopZip;
	private String bookShopPhone;

	private Set<BookShopCustomer> customers = new HashSet<>();
	private Set<BookShopSupplier> suppliers = new HashSet<>();

	public BookShopData(BookShop bookShop) {
		bookShopId = bookShop.getBookShopId();
		bookShopName = bookShop.getBookShopName();
		bookShopAddress = bookShop.getBookShopAddress();
		bookShopCity = bookShop.getBookShopCity();
		bookShopState = bookShop.getBookShopState();
		bookShopZip = bookShop.getBookShopZip();
		bookShopPhone = bookShop.getBookShopPhone();

		for (Customer customer : bookShop.getCustomers()) {
			BookShopCustomer bookShopCustomer = new BookShopCustomer(customer);
			customers.add(bookShopCustomer);
		}
		for (Supplier supplier : bookShop.getSuppliers()) {
			BookShopSupplier bookShopSupplier = new BookShopSupplier(supplier);
			suppliers.add(bookShopSupplier);
		}
	}
}
