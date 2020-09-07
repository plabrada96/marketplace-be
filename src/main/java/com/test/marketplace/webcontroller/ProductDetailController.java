package com.test.marketplace.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.marketplace.common.data.specifiers.CommonSpecifications;
import com.test.marketplace.common.webcontroller.CommonController;
import com.test.marketplace.constants.MappingConstants;
import com.test.marketplace.constants.SwaggerConstants;
import com.test.marketplace.data.ProductDetail;
import com.test.marketplace.data.builder.ProductDetailBuilder;
import com.test.marketplace.service.ProductDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = SwaggerConstants.API_VALUE_PRODUCT_DETAIL, tags = { SwaggerConstants.API_TAGS_PRODUCT_DETAIL })
@RequestMapping(SwaggerConstants.API_VALUE_PRODUCT_DETAIL)
public class ProductDetailController extends CommonController<ProductDetail>{

	@Autowired
	private ProductDetailService service;

	@Override
	@ApiOperation(value = "${swagger.SEARCH_PRODUCT_DETAIL}")
	public ProductDetail consult(@PathVariable(MappingConstants.PATH_ID) Long id) {
		return service.consult(id);
	}

	@Override
	@ApiOperation(value = "${swagger.SAVE_PRODUCT_DETAIL}")
	public ProductDetail save(@RequestBody(required = true) ProductDetail productDetail) {
		return service.save(productDetail);
	}

	@Override
	@ApiOperation(value = "${swagger.UPDATE_PRODUCT_DETAIL}")
	public ProductDetail update(@PathVariable(MappingConstants.PATH_ID) Long id, @RequestBody(required = true) ProductDetail productDetail) {
		productDetail.setId(id);
		return service.save(productDetail);
	}

	@Override
	@ApiOperation(value = "${swagger.DELETE_PRODUCT_DETAIL}")
	public void delete(@PathVariable(MappingConstants.PATH_ID) Long id) {
		service.delete(id);
	}
	
	@GetMapping("")
	@ApiOperation(value = "${swagger.LIST_PRODUCT_DETAIL}")
	public Page<ProductDetail> list(ProductDetailBuilder productDetailBuilder) {
		return service.list(new CommonSpecifications<ProductDetail>(productDetailBuilder));
	}
}
