package book.shop.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.shop.controller.model.BookShopCustomer;
import book.shop.controller.model.BookShopData;
import book.shop.controller.model.BookShopSupplier;
import book.shop.dao.BookShopDao;
import book.shop.dao.CustomerDao;
import book.shop.dao.SupplierDao;
import book.shop.entity.BookShop;
import book.shop.entity.Customer;
import book.shop.entity.Supplier;

@Service
public class BookShopService {

	@Autowired
	private BookShopDao bookShopDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private CustomerDao customerDao;

	public BookShopData saveBookShop(BookShopData bookShopData) {

		BookShop bookShop = findOrCreateBookShop(bookShopData.getBookShopId());

		copyBookShopFields(bookShop, bookShopData);

		BookShop dbBookShop = bookShopDao.save(bookShop);

		return new BookShopData(dbBookShop);

	}

	private void copyBookShopFields(BookShop bookShop, BookShopData bookShopData) {
		bookShop.setBookShopId(bookShopData.getBookShopId());
		bookShop.setBookShopName(bookShopData.getBookShopName());
		bookShop.setBookShopAddress(bookShopData.getBookShopAddress());
		bookShop.setBookShopCity(bookShopData.getBookShopCity());
		bookShop.setBookShopState(bookShopData.getBookShopState());
		bookShop.setBookShopZip(bookShopData.getBookShopZip());
		bookShop.setBookShopPhone(bookShopData.getBookShopPhone());

	}

	private BookShop findOrCreateBookShop(Long bookShopId) {

		if (Objects.isNull(bookShopId)) {
			return new BookShop();
		} else {
			return findBookShopById(bookShopId);
		}

	}

	private BookShop findBookShopById(Long bookShopId) {
		return bookShopDao.findById(bookShopId)
				.orElseThrow(() -> new NoSuchElementException("Book shop with ID= " + bookShopId + " does not exist."));

	}

	@Transactional(readOnly = false)
	public BookShopSupplier saveSupplier(Long bookShopId, BookShopSupplier bookShopSupplier) {

		BookShop bookShop = findBookShopById(bookShopId);

		Supplier supplier = findOrCreateSupplier(bookShopId, bookShopSupplier.getSupplierId());

		copySupplierFields(supplier, bookShopSupplier);

		supplier.setBookShop(bookShop);
		bookShop.getSuppliers().add(supplier);

		return new BookShopSupplier(supplierDao.save(supplier));

	}

	private void copySupplierFields(Supplier supplier, BookShopSupplier bookShopSupplier) {
		supplier.setSupplierId(bookShopSupplier.getSupplierId());
		supplier.setSupplierBusinessName(bookShopSupplier.getSupplierBusinessName());
		supplier.setSupplierTaxID(bookShopSupplier.getSupplierTaxID());

	}

	private Supplier findOrCreateSupplier(Long bookShopId, Long supplierId) {

		if (Objects.isNull(supplierId)) {
			return new Supplier();
		} else {
			return findSupplierById(bookShopId, supplierId);
		}
	}

	private Supplier findSupplierById(Long bookShopId, Long supplierId) {

		Supplier supplier = supplierDao.findById(supplierId)
				.orElseThrow(() -> new NoSuchElementException("Supplier with ID= " + supplierId + " was not found."));

		if (supplier.getBookShop().getBookShopId() != bookShopId) {
			throw new IllegalArgumentException(
					"Supplier with ID= " + supplierId + " does not work at pet store with ID" + bookShopId + ".");

		}
		return supplier;
	}

	@Transactional(readOnly = false)
	public BookShopCustomer saveCustomer(Long bookShopId, BookShopCustomer bookShopCustomer) {
		BookShop bookShop = findBookShopById(bookShopId);
		Customer customer = findOrCreateCustomer(bookShopId, bookShopCustomer.getCustomerId());

		copyCustomerFields(customer, bookShopCustomer);

		customer.getBookShops().add(bookShop);

		bookShop.getCustomers().add(customer);

		return new BookShopCustomer(customerDao.save(customer));
	}

	private void copyCustomerFields(Customer customer, BookShopCustomer bookShopCustomer) {
		customer.setCustomerId(bookShopCustomer.getCustomerId());
		customer.setCustomerFirstName(bookShopCustomer.getCustomerFirstName());
		customer.setCustomerLastName(bookShopCustomer.getCustomerLastName());
		customer.setCustomerEmail(bookShopCustomer.getCustomerEmail());

	}

	private Customer findOrCreateCustomer(Long bookShopId, Long customerId) {

		if (Objects.isNull(customerId)) {
			return new Customer();
		} else {
			return findCustomerById(bookShopId, customerId);
		}
	}

	private Customer findCustomerById(Long bookShopId, Long customerId) {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException("Customer with ID= " + customerId + " was not found."));
		boolean found = false;

		for (BookShop bookShop : customer.getBookShops()) {
			if (bookShop.getBookShopId() == bookShopId) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new IllegalArgumentException(
					"Customer with ID= " + customerId + " does not shop at book shop with ID" + bookShopId + ".");

		}
		return customer;

	}

	@Transactional(readOnly = true)
	public List<BookShopData> retrieveAllBookShops() {

		List<BookShopData> bookShopData = new LinkedList<>();

		for (BookShop bookShop : bookShopDao.findAll()) {

			BookShopData bsd = new BookShopData(bookShop);

			bsd.getCustomers().clear();
			bsd.getSuppliers().clear();
			bookShopData.add(bsd);
		}
		return bookShopData;
	}

	@Transactional(readOnly = true)
	public BookShopData retrieveBookShopById(Long bookShopId) {

		return new BookShopData(findBookShopById(bookShopId));
	}

	public void deleteBookShopById(Long bookShopId) {

		BookShop bookShop = findBookShopById(bookShopId);
		bookShopDao.delete(bookShop);
	}
}
