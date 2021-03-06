package com.zoco.example.robospicetest.http.requests.product;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.zoco.example.robospicetest.http.services.ProductService;
import com.zoco.example.robospicetest.models.Product;

public class DeleteProductRequest extends
		RetrofitSpiceRequest<Product, ProductService> {

	Integer id;

	public DeleteProductRequest(Integer usrId) {
		super(Product.class, ProductService.class);
		this.id = usrId;
	}

	@Override
	public Product loadDataFromNetwork() throws Exception {
		try {
			getService().deleteProduct(id);
			Product prdct = new Product();
			prdct.setId(id);
			return prdct;
		} catch (Exception excep) {
			// Gottcha! catch them all!
			excep.printStackTrace();
			throw new IllegalAccessError("If this don't succed");
		}
	}
}
