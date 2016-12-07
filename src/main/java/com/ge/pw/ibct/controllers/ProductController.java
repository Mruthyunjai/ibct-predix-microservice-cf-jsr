package com.ge.pw.ibct.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.;



import com.ge.pw.ibct.services.ProductService;
import com.ge.pw.ibct.utils.CommonUtil;
import com.ge.pw.ibct.utils.WSResponseStatus;

@RestController
@RequestMapping("/ibct")
public class ProductController{
	@Autowired
	ProductService productService;
	
	@RequestMapping("/Product")
	@CrossOrigin(origins = "http://localhost:8080")
	public @ResponseBody WSResponseStatus getProductLines(){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(productService.getProductLine());
		return wsResponseStatus;
		
	}
	
}