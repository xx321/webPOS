package com.maxwell.pos.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "product_box", catalog = "pos")
public class ProductBox implements java.io.Serializable {

	private static final long serialVersionUID = -1808032767176248067L;
	private Integer id;
	private Product product;
	private Product box;
	private Integer quantity;

	public ProductBox() {
	}
	
	@Id
	@GenericGenerator(name ="pkGenerator",strategy="foreign" ,parameters={@Parameter(name = "property", value = "product")})
    @GeneratedValue(generator="pkGenerator")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "productBox")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Product getBox() {
		return this.box;
	}

	public void setBox(Product box) {
		this.box = box;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}