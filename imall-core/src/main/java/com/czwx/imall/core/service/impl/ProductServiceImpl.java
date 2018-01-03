package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.Product;
import com.czwx.imall.core.mapper.ProductMapper;
import com.czwx.imall.core.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("productService")
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public BaseMapper<Product, Long> getMapper() {
        return productMapper;
    }

    @Override
    public Product getProduct() {
        String prodNo = Global.getValue("prod_no");
        //获取产品
        Product product = productMapper.selectByPrimaryKey(Long.parseLong(prodNo));
        return product;
    }
}
