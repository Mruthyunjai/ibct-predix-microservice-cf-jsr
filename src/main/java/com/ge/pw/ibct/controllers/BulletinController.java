package com.ge.pw.ibct.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ge.pw.ibct.services.BulletinService;
import com.ge.pw.ibct.utils.CommonUtil;
import com.ge.pw.ibct.utils.WSResponseStatus;

@RestController
@RequestMapping("/ibct")
public class BulletinController{
	
	@Autowired
	BulletinService bulletinService;
	
	@RequestMapping("/Bulletin/{bullNum}")
	public @ResponseBody WSResponseStatus getBulletin(
			@PathVariable String bullNum) {
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(bulletinService.getBulletin(bullNum));
		return wsResponseStatus;
	}
	
	@RequestMapping("/Bulletin/Cancel/{bullNum}")
	public @ResponseBody WSResponseStatus cancelBulletin(
			@PathVariable String bullNum) {
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(bulletinService.cancelBulletin(bullNum));
		return wsResponseStatus;
	}
	
	
	@RequestMapping("/Bulletin/Superced/{bulletinTypeCode}/{productLine}/{bulletinStatus}")
    public @ResponseBody WSResponseStatus getSupercedValues(
                                    @PathVariable Integer bulletinTypeCode,@PathVariable String productLine,
                                    @PathVariable String bulletinStatus) {
                    WSResponseStatus wsResponseStatus = new WSResponseStatus();
                    CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
                    wsResponseStatus.setData(bulletinService.getSupercedValues(
                                                    bulletinTypeCode, productLine, bulletinStatus));
                    
                    return wsResponseStatus;
    }
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/Bulletin/BulletinTypes")
	public @ResponseBody WSResponseStatus getBulletinTypeValues()
			throws IOException {

		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(bulletinService.getBulletinTypeValues());

		return wsResponseStatus;
	}
	
	@RequestMapping("/Bulletin/CodeDescription/{codeType}")
	public @ResponseBody WSResponseStatus getCategoryCodeValues(
			@PathVariable String codeType) throws IOException {

		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(bulletinService
				.getCategoryAndTimings(codeType));
		
		return wsResponseStatus;
	}
	
	@RequestMapping("/Bulletin/Serials/{productLine}/{bulletinTypeCode}")
	public @ResponseBody WSResponseStatus getSerials(
			@PathVariable String productLine,
			@PathVariable Integer bulletinTypeCode)
			throws JsonGenerationException, JsonMappingException, IOException {
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);

		wsResponseStatus.setData(bulletinService.getSerialValues(productLine,
				bulletinTypeCode));
		
		return wsResponseStatus;
	}
	
	
}