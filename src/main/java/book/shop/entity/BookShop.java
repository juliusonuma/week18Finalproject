package book.shop.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class BookShop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookShopId;
	private String bookShopName;
	private String bookShopAddress;
	private String bookShopCity;
	private String bookShopState;
	private String bookShopZip;
	private String bookShopPhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "book_shop_customer", joinColumns = @JoinColumn(name = "book_shop_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "bookShop", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Supplier> suppliers = new HashSet<>();
}
