package book.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import book.shop.controller.model.BookShopCustomer;
import book.shop.controller.model.BookShopData;
import book.shop.controller.model.BookShopSupplier;
import book.shop.service.BookShopService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book_shop")
@Slf4j
public class BookShopController {

	@Autowired
	private BookShopService bookShopService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookShopData createBookShop(@RequestBody BookShopData bookShopData) {
		log.info("Creating book shop {}", bookShopData);

		return bookShopService.saveBookShop(bookShopData);
	}

	@PutMapping("/{bookShopId}")
	public BookShopData updateBookShop(@PathVariable Long bookShopId, @RequestBody BookShopData bookShopData) {

		bookShopData.setBookShopId(bookShopId);
		log.info("Updating book shop {}", bookShopData);

		return bookShopService.saveBookShop(bookShopData);
	}

	@PostMapping("/{bookShopId}/supplier")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookShopSupplier insertBookShopSupplier(@PathVariable Long bookShopId,
			@RequestBody BookShopSupplier bookShopSupplier) {
		log.info("Creating supplier {} for book shop with ID=()", bookShopSupplier.getSupplierId(), bookShopId);

		return bookShopService.saveSupplier(bookShopId, bookShopSupplier);
	}

	@PutMapping("/{bookShopId}/supplier")
	public BookShopSupplier updateBookShopSupplier(@PathVariable Long bookShopId,
			@RequestBody BookShopSupplier bookShopSupplier) {
		log.info("Updating supplier {} for book shop with ID=()", bookShopSupplier.getSupplierId(), bookShopId);

		return bookShopService.saveSupplier(bookShopId, bookShopSupplier);
	}

	@PostMapping("/{bookShopId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookShopCustomer insertBookShopCustomer(@PathVariable Long bookShopId,
			@RequestBody BookShopCustomer bookShopCustomer) {
		log.info("Creating customer {} for book shop with ID=()", bookShopCustomer.getCustomerId(), bookShopId);
		return bookShopService.saveCustomer(bookShopId, bookShopCustomer);
	}

	@PutMapping("/{bookShopId}/customer")
	public BookShopCustomer updateBookShopCustomer(@PathVariable Long bookShopId,
			@RequestBody BookShopCustomer bookShopCustomer) {
		log.info("Updating customer {} for book shop with ID=()", bookShopCustomer.getCustomerId(), bookShopId);
		return bookShopService.saveCustomer(bookShopId, bookShopCustomer);
	}

	@GetMapping("/book_shop")
	public List<BookShopData> listAllBookShops() {
		log.info("Listing all book shops");
		return bookShopService.retrieveAllBookShops();
	}

	@GetMapping("/{bookShopId}")
	public BookShopData getBookShopById(@PathVariable Long bookShopId) {
		log.info("Retrieving book shop with ID=()", bookShopId);
		return bookShopService.retrieveBookShopById(bookShopId);
	}

	@DeleteMapping("/{bookShopId}")
	public Map<String, String> deleteBookShopById(@PathVariable Long bookShopId) {
		log.info("Received request to delete book shop with ID: " + bookShopId + ".");

		bookShopService.deleteBookShopById(bookShopId);

		return Map.of("Message", "Book shop with ID=" + bookShopId + "was deleted suscessfully.");

	}
}
