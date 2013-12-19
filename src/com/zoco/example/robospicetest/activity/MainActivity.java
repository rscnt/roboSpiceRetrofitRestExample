package com.zoco.example.robospicetest.activity;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.zoco.example.robospicetest.R;
import com.zoco.example.robospicetest.http.requests.ProductListRequest;
import com.zoco.example.robospicetest.models.Product;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseAbstractActivity {

	TextView txtViewHelloWorld;
	Button buttonGetList;
	ProductListRequest prdctLstRqst;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
		initRequests();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initUI() {
		txtViewHelloWorld = (TextView) findViewById(R.id.txt_hello_world);
		buttonGetList = (Button) findViewById(R.id.buttonGetList);
		buttonGetList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				executeProductListRequest();
			}
		});
	}

	private void initRequests() {
		prdctLstRqst = new ProductListRequest();
	}

	private void executeProductListRequest() {
		if (prdctLstRqst != null) {
			// [request, name, duration, listener]
			getSpiceManager().execute(prdctLstRqst, "zocoProductList",
					DurationInMillis.ONE_MINUTE,
					new ProductListRequestListener());
		}

	}

	public final class ProductListRequestListener implements
			RequestListener<Product.List> {

		@Override
		public void onRequestFailure(SpiceException spcExcptn) {
			System.out.println("-------------------------------------");
			spcExcptn.getCause().printStackTrace();
			System.out.println("-------------------------------------");
		}

		@Override
		public void onRequestSuccess(Product.List products) {
			String msg = "Products Obtained: " + products.size();
			Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
			if (txtViewHelloWorld != null) {
				txtViewHelloWorld.setText(msg);
			}
		}
	}
}