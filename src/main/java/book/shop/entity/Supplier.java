package book.shop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplierId;
	private String supplierBusinessName;
	private String supplierTaxID;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_shop_id")
	private BookShop bookShop;

}
