package com.ge.pw.ibct.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ge.pw.ibct.beans.CcttBulletin;
import com.ge.pw.ibct.dto.Bulletindto;
import com.ge.pw.ibct.services.BulletinService;
import com.ge.pw.ibct.utils.CommonUtil;
import com.ge.pw.ibct.utils.WSResponseStatus;

@RestController
@RequestMapping("/ibct")
public class BulletinController{
	
	@Autowired
	BulletinService bulletinService;
	
	
	@RequestMapping("/Bulletins/{bullNum}")
	public @ResponseBody WSResponseStatus getBulletins(
			@PathVariable String bullNum) {
		WSResponseStatus wsResponseStatus = new WSResponseStatus();
		CommonUtil.populateWSResponseStatusSuccessResponse(wsResponseStatus);
		wsResponseStatus.setData(bulletinService.getBulletins(bullNum));
		return wsResponseStatus;
	}
	
	/*@RequestMapping("/Bulletin/{bulletinNum}")
	public @ResponseBody Bulletindto getBulletins(@PathVariable String bulletinNum){
		return new Bulletindto().getBulletindtofromEntity(bulletinService.getBulletins(bulletinNum).get(1));
	}*/
	
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
}