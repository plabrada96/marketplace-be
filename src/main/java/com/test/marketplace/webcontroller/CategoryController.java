package com.test.marketplace.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.marketplace.common.webcontroller.CommonController;
import com.test.marketplace.constants.MappingConstants;
import com.test.marketplace.constants.SwaggerConstants;
import com.test.marketplace.data.Category;
import com.test.marketplace.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = SwaggerConstants.API_VALUE_CATEGORY, tags = { SwaggerConstants.API_TAGS_CATEGORY })
@RequestMapping(SwaggerConstants.API_VALUE_CATEGORY)
public class CategoryController extends CommonController<Category>{

	@Autowired
	private CategoryService service;
	
	@Override
	@ApiOperation(value = "${swagger.SEARCH_CATEGORY}")
	public Category consult(@PathVariable(MappingConstants.PATH_ID) Long id) {
		return service.consult(id);
	}

	@Override
	@ApiOperation(value = "${swagger.SAVE_CATEGORY}")
	public Category save(@RequestBody(required = true) Category category) {
		return service.save(category);
	}

	@Override
	@ApiOperation(value = "${swagger.UPDATE_CATEGORY}")
	public Category update(@PathVariable(MappingConstants.PATH_ID) Long id, @RequestBody(required = true) Category category) {
		category.setId(id);
		return service.save(category);
	}

	@Override
	@ApiOperation(value = "${swagger.DELETE_CATEGORY}")
	public void delete( @PathVariable(MappingConstants.PATH_ID) Long id) {
		service.delete(id);
	}
	
	@GetMapping("")
	@ApiOperation(value = "${swagger.LIST_CATEGORY}")
	public List<Category> list() {
		return service.list();
	}

}
