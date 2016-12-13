package com.ge.pw.ibct.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.neo4j.cypher.internal.compiler.v2_1.ast.rewriters.distributeLawsRewriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ge.pw.ibct.beans.CcttBulletin;
import com.ge.pw.ibct.repository.CcttBulletinJpaRepository;
import com.ge.pw.ibct.repository.CcttBulletinProductJpaRepository;
import com.ge.pw.ibct.repository.CcttBulletinRevisionJpaRepository;
import com.ge.pw.ibct.repository.CcttCrProductJpaRepository;
import com.ge.pw.ibct.repository.CcttProductLineJpaRepository;
import com.ge.pw.ibct.repository.CcttProductTypeJpaRepository;
import com.ge.pw.ibct.repository.ComtApplicationCodeJpaRepository;
import com.ge.pw.ibct.utils.BulletinTypes;
import com.ge.pw.ibct.entity.CcttBulletinEntity;
import com.ge.pw.ibct.entity.CcttBulletinProductEntity;
import com.ge.pw.ibct.entity.CcttBulletinRevisionEntity;
import com.ge.pw.ibct.entity.CcttCrProductEntity;
import com.ge.pw.ibct.entity.CcttProductLineEntity;
import com.ge.pw.ibct.entity.CcttProductTypeEntity;
import com.ge.pw.ibct.entity.ComtApplicationCodeEntity;

@Service
public class BulletinService {

	@Autowired
	CcttBulletinJpaRepository ccttBulletinJpaRepository;
	@Autowired
	CcttProductLineJpaRepository ccttProductLineJpaRepository;
	@Autowired
	CcttBulletinRevisionJpaRepository ccttBulletinRevisionJpaRepository;
	@Autowired
	CcttBulletinProductJpaRepository ccttBulletinProductJpaRepository;
	@Autowired
	CcttCrProductJpaRepository ccttcrRepository;
	@Autowired
	CcttProductTypeJpaRepository ccttProductTRepository;
	@Autowired
	ComtApplicationCodeJpaRepository codeJpaRepository;

	public CcttBulletinEntity getBulletin(String bulletinNumber) {
		return ccttBulletinJpaRepository.findOne(bulletinNumber);
	}

	public Object cancelBulletin(String bulletinNumber) {
		CcttBulletinEntity bulletin = ccttBulletinJpaRepository
				.findOne(bulletinNumber);
		bulletin.setBulletinStatus("EFFECTIVE");
		return ccttBulletinJpaRepository.save(bulletin);
	}

	public List getSupercedValues(Integer bulletinTypeCode, String productLine,
			String bulletinStatus) {
		return ccttBulletinJpaRepository
				.findByBulletinTypeCodeAndProductLineAndBulletinStatus(
						bulletinTypeCode, productLine, bulletinStatus);
	}

	public List<String> getBulletinTypeValues() {
		List<CcttBulletinEntity> bulletinTypeValues = ccttBulletinJpaRepository
				.findAll();
		List<String> bulletinNameSet = new ArrayList<String>();
		for (CcttBulletinEntity types : bulletinTypeValues) {

			if (types.getBulletinTypeCode().equals(20000043)) {
				bulletinNameSet.add("PRODUCT BULLETIN");
			}
			if (types.getBulletinTypeCode().equals(20000042)) {
				bulletinNameSet.add("SERVICE BULLETIN");
			}
			if (types.getBulletinTypeCode().equals(2726)) {
				bulletinNameSet.add("SYSTEM BULLETIN");
			}
		}
		return new ArrayList<String>(new HashSet<String>(bulletinNameSet));
	}

	public Integer getBulletinTypeCode(String bulletinTypeCode) {
		if (bulletinTypeCode.equalsIgnoreCase("PRODUCT BULLETIN")) {
			return 20000043;
		}
		else if (bulletinTypeCode.equalsIgnoreCase("SERVICE BULLETIN")) {
			return 20000042;
		}
		else if (bulletinTypeCode.equalsIgnoreCase("SYSTEM BULLETIN")) {
			return 2726;
		}
		else{
			return 0;
		}

	}

	/*
	 * public List<String> getBulletinTypeValues(){ List<CcttBulletinEntity>
	 * bulletinTypeValues = ccttBulletinJpaRepository.findAll(); }
	 */

	/*
	 * @Autowired ComtApplicationCodeJpaRepository codeJpaRepository;
	 * 
	 * public Map<Integer, Object> getCategoryAndTimings(String codeType) {
	 * List<ComtApplicationCodeEntity> categories = codeJpaRepository
	 * .findByCodeType(codeType);
	 * 
	 * Map<Integer, Object> idDescriptionMap = new HashMap<Integer, Object>();
	 * for (ComtApplicationCodeEntity codes : categories) {
	 * idDescriptionMap.put( codes.getCodeId(), new Object[] {
	 * codes.getCodeName(), codes.getCodeDescription() }); } return
	 * idDescriptionMap; }
	 */

	public List<String> getSerialValues(String productLine,
			Integer bulletinTypeCode) {
		List<String> serials = new ArrayList<String>();
		List<CcttCrProductEntity> cccpList = ccttcrRepository
				.findByProductLine(productLine);
		//Integer bulletinTypeCode = getBulletinTypeCode(bulletinType);
		if (bulletinTypeCode == 2726) {
			for (CcttCrProductEntity serial : cccpList) {
				serials.add(serial.getProductSerialNum());
			}
		} else {
			List<CcttProductTypeEntity> ccttProTypeList = ccttProductTRepository
					.findByBulletinTypeCode(bulletinTypeCode);
			for (CcttCrProductEntity crProduct : cccpList) {
				for (CcttProductTypeEntity proType : ccttProTypeList) {
					if (crProduct.getProductType().equals(
							proType.getProductType())) {
						serials.add(crProduct.getProductSerialNum());
					}
				}
			}

		}
		return serials;
	}

	/*
	 * @Autowired public Object getCategoryAndTimings(String codeType) {
	 * 
	 * List<ComtApplicationCodeEntity> categories =
	 * codeJpaRepository.findByCodeType(codeType);
	 * 
	 * Map<Long, Object> idDescriptionMap = new HashMap<Long, Object>(); for
	 * (ComtApplicationCodeEntity codes : categories) { idDescriptionMap.put(
	 * codes.getCodeId(), new Object[] { codes.getCodeName(),
	 * codes.getCodeDescription() }); } return idDescriptionMap; 
	 * 
	 * } 
	 * 
	 * */
	  
	  public List<ComtApplicationCodeEntity> getCode(String codeType) {
	  
	  return codeJpaRepository.findByCodeType(codeType); 
	  }
	 
	
	public Object insertBulletin(String bulletinNum, String bulletinStatus,
			Integer bulletinTypeCode, String category, String complianceLevel,
			String createdBy, Object createdDate, Date issueDate,
			String lastUpdatedBy, Object lastUpdatedDate, Integer latestRevId,
			String remarks, Object revisionDate, Boolean significant,
			String supercededBulletinNum, Boolean trackImplimentationPlan,
			Boolean voucherProgram, String productLine, String description,
			String revision, String[] fromSerials, String[] toSerials,
			List<String> timings) {

		CcttBulletinEntity bulletinEnt = new CcttBulletinEntity();
		CcttBulletinRevisionEntity revEnt = new CcttBulletinRevisionEntity();
		CcttProductLineEntity plEnt = ccttProductLineJpaRepository
				.findByProductLine(productLine);
		bulletinStatus = "EFFECTIVE";
		// ArrayList<Object> timings = new ArrayList<Object>();
		// timings.add(new Integer(20000031));
		// timings.add(new Integer(20000032));
		for (int i = 0; i < timings.size(); i++) {
			revEnt.setBulletinDesc(description);
			revEnt.setBulletinNum(bulletinNum);
			revEnt.setBulletinRevision(revision); // if null, set it to 0. If
													// not 0, increment it. If
													// 0, and 0 already exists,
													// check for different value
													// of timing.
			revEnt.setCategoryCode(20000028); // refers to code_id in
												// comt_application_code table -
												// code_type =
												// 'BULLETIN_CATEGORY'
			revEnt.setComplianceLevel("SPS"); // Hardcoded in Constant.java
			revEnt.setCreatedBy(createdBy);
			revEnt.setCreatedDate(new Date());
			// revEnt.setFieldImplMetricInd("fieldImplMetricInd"); //Not null.
			// Only while updating.
			revEnt.setLastUpdatedBy(createdBy);
			revEnt.setLastUpdatedDate(new Date());
			revEnt.setRemarks(remarks);
			revEnt.setRevDate(new Date());
			revEnt.setRevId(0);
			// revEnt.setTimingCode(getCode("TIMING_CODE")); //refers to code_id
			// in comt_application_code table - code_type = 'TIMING_CODE'
			
			if (trackImplimentationPlan) {
				revEnt.setTrackImplementationInd("Y");
			} else {
				revEnt.setTrackImplementationInd("N");
			}
			if (significant) {
				revEnt.setSignificantInd("Y");
			} else {
				revEnt.setSignificantInd("N");
			}
			if (voucherProgram) {
				revEnt.setVoucherProgramInd("Y");
			} else {
				revEnt.setVoucherProgramInd("N");
			}

			// int tim = ((Integer)timings.get(i)).intValue();
			// revEnt.setTimingCode(tim);
			ccttBulletinRevisionJpaRepository.save(revEnt);
		}

		// Set Serial Number Ranges
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("481758", "751002");
		hm.put("751002", "751005");
		for (String key : hm.keySet()) {
			CcttBulletinProductEntity serialEnt = new CcttBulletinProductEntity();
			serialEnt.setBulletinNum(bulletinNum);
			serialEnt.setRevId(Integer.parseInt(revision));
			serialEnt.setFromSerialNum(key);
			serialEnt.setToSerialNum(hm.get(key));
			serialEnt.setTimingCode(20000044);
			serialEnt.setCreatedBy(createdBy);
			serialEnt.setCreatedDate(new Date());
			serialEnt.setLastUpdatedBy(createdBy);
			serialEnt.setLastUpdatedDate(new Date());
			serialEnt.setCmplEndDate(new Date());
			ccttBulletinProductJpaRepository.save(serialEnt);
		}

		// Set Bulletin
		bulletinEnt.setBulletinNum(bulletinNum);
		bulletinEnt.setBulletinStatus(bulletinStatus);
		bulletinEnt.setBulletinTypeCode(100);
		bulletinEnt.setProductLine(plEnt.getProductLine());

		bulletinEnt.setCreatedBy(createdBy);
		bulletinEnt.setCreatedDate(new Date());
		bulletinEnt.setIssueDate(new Date());
		bulletinEnt.setLastUpdatedBy(createdBy);
		bulletinEnt.setLastUpdatedDate(new Date());
		bulletinEnt.setLatestRevId(101);
		bulletinEnt.setSupercededBulletinNum(supercededBulletinNum);
		// bulletin.setFlagContinueAddTimings(getParameterAsString(HtmlField.FLAG_CONTINUE_ADD));
		// Not present in table.
		// bulletin.setSelectRowTiming(getParameterAsString(HtmlField.SELECT_ROW));
		// bulletin.setBulletinType(bulletinType); ???

		//ccttBulletinJpaRepository.save(bulletinEnt);

		return 1;

	}

}