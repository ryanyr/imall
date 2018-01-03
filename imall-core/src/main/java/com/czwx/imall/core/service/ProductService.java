package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.Product;

public interface ProductService extends BaseService<Product, Long>{

    Product getProduct();

}
