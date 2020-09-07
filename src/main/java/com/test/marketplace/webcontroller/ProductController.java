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
import com.test.marketplace.data.Product;
import com.test.marketplace.data.builder.ProductBuilder;
import com.test.marketplace.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = SwaggerConstants.API_VALUE_PRODUCT, tags = { SwaggerConstants.API_TAGS_PRODUCT })
@RequestMapping(SwaggerConstants.API_VALUE_PRODUCT)
public class ProductController extends CommonController<Product>{

	@Autowired
	private ProductService service;
	
	@Override
	@ApiOperation(value = "${swagger.SEARCH_PRODUCT}")
	public Product consult(@PathVariable(MappingConstants.PATH_ID) Long id) {
		return service.consult(id);
	}

	@Override
	@ApiOperation(value = "${swagger.SAVE_PRODUCT}")
	public Product save(@RequestBody(required = true) Product product) {
		return service.save(product);
	}

	@Override
	@ApiOperation(value = "${swagger.UPDATE_PRODUCT}")
	public Product update(@PathVariable(MappingConstants.PATH_ID) Long id, @RequestBody(required = true) Product product) {
		product.setId(id);
		return service.save(product);
	}

	@Override
	@ApiOperation(value = "${swagger.DELETE_PRODUCT}")
	public void delete(@PathVariable(MappingConstants.PATH_ID) Long id) {
		service.delete(id);
	}
	
	@GetMapping("")
	@ApiOperation(value = "${swagger.LIST_PRODUCT}")
	public Page<Product> list(ProductBuilder productBuilder) {
		return service.list(new CommonSpecifications<Product>(productBuilder));
	}
}
