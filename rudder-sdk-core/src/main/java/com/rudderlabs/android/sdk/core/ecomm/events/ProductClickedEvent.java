package com.rudderlabs.android.sdk.core.ecomm.events;

import com.google.gson.Gson;
import com.rudderlabs.android.sdk.core.RudderProperty;
import com.rudderlabs.android.sdk.core.ecomm.ECommerceEvents;
import com.rudderlabs.android.sdk.core.ecomm.ECommerceProduct;
import com.rudderlabs.android.sdk.core.ecomm.ECommercePropertyBuilder;
import com.rudderlabs.android.sdk.core.util.Utils;

public class ProductClickedEvent extends ECommercePropertyBuilder {
    private ECommerceProduct product;

    public ProductClickedEvent withProduct(ECommerceProduct product) {
        this.product = product;
        return this;
    }

    public ProductClickedEvent withProductBuilder(ECommerceProduct.Builder builder) {
        this.product = builder.build();
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.PRODUCT_CLICKED;
    }

    @Override
    public RudderProperty build() {
        RudderProperty property = new RudderProperty();
        if (this.product != null) {
            property.putValue(Utils.convertToMap(new Gson().toJson(this.product)));
        }
        return property;
    }
}