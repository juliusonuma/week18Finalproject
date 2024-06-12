package book.shop.controller.model;

import book.shop.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookShopSupplier {

	private Long supplierId;
	private String supplierBusinessName;
	private String supplierTaxID;

	public BookShopSupplier(Supplier supplier) {
		supplierId = supplier.getSupplierId();
		supplierBusinessName = supplier.getSupplierBusinessName();
		supplierTaxID = supplier.getSupplierTaxID();

	}
}
