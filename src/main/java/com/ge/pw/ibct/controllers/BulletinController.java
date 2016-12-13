package com.ge.pw.ibct.controllers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Date;

import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ge.pw.ibct.dto.AddBulletinDto;
//import com.ge.pw.ibct.dto.AddBulletinDto.Serials;
import com.ge.pw.ibct.dto.Bulletindto;
import com.ge.pw.ibct.services.BulletinService;
import com.ge.pw.ibct.utils.CommonUtil;
import com.ge.pw.ibct.utils.WSResponseStatus;

@RestController
@RequestMapping("/ibct")
public class BulletinController{
	
	Bulletindto bulletindto;
	
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
	@RequestMapping(value = "/Bulletin/CancelBulletin", method = RequestMethod.POST)
    public @ResponseBody WSResponseStatus getSupercedValuesRequest(
                                    @RequestBody Bulletindto bulletin) {
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(bulletinService.cancelBulletin(bulletin.getBulletinNum()));
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
		//wsResponseStatus.setData(bulletinService
		//		.getCategoryAndTimings(codeType));
		
		return wsResponseStatus;
	}
	@CrossOrigin(origins = "http://localhost:8080")
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
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/Bulletin/TimingCodes")
	public @ResponseBody WSResponseStatus getCode(){
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(bulletinService.getCode("TIMING CODE"));
		return wsResponseStatus;
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/Bulletin/Insert")
	public @ResponseBody WSResponseStatus insertBulletin(@RequestBody AddBulletinDto bulletinDetails) {
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		
		try{
			bulletinDetails.setCreatedDate(new Date());
			bulletinDetails.setLastUpdatedDate(new Date());
			bulletinDetails.setRevisionDate(new Date());
			
		
			wsResponseStatus.setData(bulletinService.insertBulletin(bulletinDetails.getBulletinNum(),
					bulletinDetails.getBulletinStatus(),
					bulletinDetails.getBulletinTypeCode(),
					bulletinDetails.getCategory(),
					bulletinDetails.getComplianceLevel(),
					bulletinDetails.getCreatedBy(),
					bulletinDetails.getCreatedDate(),
					bulletinDetails.getIssueDate(),
					bulletinDetails.getLastUpdatedBy(),
					bulletinDetails.getLastUpdatedDate(),
					bulletinDetails.getLatestRevId(),
					bulletinDetails.getRemarks(),
					bulletinDetails.getRevisionDate(),
					bulletinDetails.getSignificant(),
					bulletinDetails.getSupercededBulletinNum(),
					bulletinDetails.getTrackImplimentationPlan(),
					bulletinDetails.getVoucherProgram(),
					bulletinDetails.getProductLine(),
					bulletinDetails.getDescription(),
					bulletinDetails.getRevision(),
					bulletinDetails.getFromserials(),
					bulletinDetails.getToserials(),
					bulletinDetails.getTimings()));
			return wsResponseStatus;
		}
		catch(Exception ex){
			CommonUtil.populateWSResponseStatusFailsureStatusResponse(wsResponseStatus, ex.fillInStackTrace().toString());
			return wsResponseStatus;
		}
		//
		//System.out.println("plList :"+i);
		
	}
}