package com.maxwell.pos.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maxwell.pos.action.base.BaseAction;
import com.maxwell.pos.model.Product;
import com.maxwell.pos.model.ProductBox;
import com.maxwell.pos.service.ProductBoxManager;
import com.maxwell.pos.service.ProductManager;

@Component("productBoxAction")
@Scope("prototype")
public class ProductBoxAction extends BaseAction {

	private static final long serialVersionUID = 5642899826516355925L;
	
	private ProductManager productManager;
	private ProductBoxManager productBoxManager;
	
	@Resource
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	@Resource
	public void setProductBoxManager(ProductBoxManager productBoxManager) {
		this.productBoxManager = productBoxManager;
	}

	private Integer productId;
	private Integer boxId;
	private Integer quantity;
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	private String message;

	public String getMessage() {
		return message;
	}
	
	public String update() {
		if (productId == null)
			return INPUT;
		
		Product product = productManager.get(productId);
		ProductBox productBox = productBoxManager.get(productId);
		
		//確認更新時 沒有傳入的boxId ，代表設定移除， 刪除productBox 完成動作。
		if (boxId == null || "".equals(boxId)) {
			if (productBox != null) {
				product.setProductBox(null);
				productBox.setProduct(null);
				productBoxManager.delete(productBox);
			}
			return SUCCESS;
		}
		
		if (quantity == null || quantity ==0) {
			message = "設定錯誤 !<br />拆箱數量不能為0";
			return SUCCESS;
		}
		
		if (productBox == null) {
			productBox = new ProductBox();
			productBox.setProduct(product);
			
			product.setProductBox(productBox);
		}
			
		productBox.setBox(productManager.get(boxId));
		productBox.setQuantity(quantity);
		
		if (exits(product,productBox)) {
			message = "設定失敗 !<br />子類商品不能擁有父類商品";
			return SUCCESS;
		}
		
		productManager.update(product);
		return SUCCESS;
	}
	
	private boolean exits(Product product, ProductBox productBox) {
		if (productBox == null)
			return false;
		if (product.getId() == productBox.getBox().getId())
			return true;
		
		return exits(product,productBox.getBox().getProductBox());
	}
}
