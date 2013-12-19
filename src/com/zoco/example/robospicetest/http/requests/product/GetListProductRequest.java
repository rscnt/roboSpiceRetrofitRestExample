package com.zoco.example.robospicetest.http.requests.product;

import android.util.Log;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.zoco.example.robospicetest.http.services.ProductService;
import com.zoco.example.robospicetest.models.Product;

/**
 * RetrofitSpiceRequest, are the bridge between services and activities.
 * */
public class GetListProductRequest extends
		RetrofitSpiceRequest<Product.List, ProductService> {

	public GetListProductRequest() {
		super(Product.List.class, ProductService.class);
	}

	@Override
	public Product.List loadDataFromNetwork() {
		try {
			Log.d(GetListProductRequest.class.getCanonicalName(),
					"Calling web service");
			return getService().products();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new IllegalAccessError("The fuck happen!");
		}
	}
}
