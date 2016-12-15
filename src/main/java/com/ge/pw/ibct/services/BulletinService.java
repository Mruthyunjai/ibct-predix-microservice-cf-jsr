package com.ge.pw.ibct.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.ge.pw.ibct.dto.AddBulletinDto;
import com.ge.pw.ibct.entity.CcttBulletinEntity;
import com.ge.pw.ibct.entity.CcttBulletinProductEntity;
import com.ge.pw.ibct.entity.CcttBulletinProductEntityKey;
import com.ge.pw.ibct.entity.CcttBulletinRevisionEntity;
import com.ge.pw.ibct.entity.CcttBulletinRevisionEntityKey;
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
		bulletin.setBulletinStatus("INEFFECTIVE");
		ccttBulletinJpaRepository.save(bulletin);
		return ccttBulletinJpaRepository.findOne(bulletinNumber);
	}

	public List<String> getSupercedValues(Integer bulletinTypeCode,
			String productLine, String bulletinStatus) {
		return ccttBulletinJpaRepository
				.findByBulletinTypeCodeAndProductLineAndBulletinStatus(
						bulletinTypeCode, productLine, bulletinStatus).stream()
				.map(CcttBulletinEntity::getBulletinNum)
				.collect(Collectors.toList());
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
		} else if (bulletinTypeCode.equalsIgnoreCase("SERVICE BULLETIN")) {
			return 20000042;
		} else if (bulletinTypeCode.equalsIgnoreCase("SYSTEM BULLETIN")) {
			return 2726;
		} else {
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
		// Integer bulletinTypeCode = getBulletinTypeCode(bulletinType);
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
	 */

	public List<ComtApplicationCodeEntity> getCode(String codeType) {

		return codeJpaRepository.findByCodeTypeOrderByCodeName(codeType);
	}

	public Object insertBulletin(String bulletinNum, String bulletinStatus,
			Integer bulletinTypeCode, String category, String complianceLevel,
			String createdBy, Object createdDate, Date issueDate,
			String lastUpdatedBy, Object lastUpdatedDate, Integer latestRevId,
			String remarks, Date revisionDate, Boolean significant,
			String supercededBulletinNum, Boolean trackImplimentationPlan,
			Boolean voucherProgram, Boolean fieldImplementationMetric,
			String productLine, String description, String revision,
			String[] fromSerials, String[] toSerials, Integer[] timings) {

		CcttBulletinEntity bulletinEnt = new CcttBulletinEntity();
		CcttBulletinRevisionEntity revEnt = new CcttBulletinRevisionEntity();
		CcttProductLineEntity plEnt = ccttProductLineJpaRepository
				.findByProductLine(productLine);
		bulletinStatus = "EFFECTIVE";

		// Set Bulletin
		bulletinEnt.setBulletinNum(bulletinNum);
		bulletinEnt.setBulletinStatus(bulletinStatus);
		bulletinEnt.setBulletinTypeCode(bulletinTypeCode);
		bulletinEnt.setProductLine(plEnt.getProductLine());

		bulletinEnt.setCreatedBy(createdBy);
		bulletinEnt.setCreatedDate(new Date());
		bulletinEnt.setIssueDate(issueDate);
		bulletinEnt.setLastUpdatedBy(createdBy);
		bulletinEnt.setLastUpdatedDate(new Date());
		bulletinEnt.setLatestRevId(Integer.parseInt(revision));
		bulletinEnt.setSupercededBulletinNum(supercededBulletinNum);

		ccttBulletinJpaRepository.save(bulletinEnt);
		for (int i = 0; i < timings.length; i++) {
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

			// Only while updating.
			revEnt.setLastUpdatedBy(createdBy);
			revEnt.setLastUpdatedDate(new Date());
			revEnt.setRemarks(remarks);
			revEnt.setRevDate(revisionDate);
			revEnt.setRevId(Integer.parseInt(revision));
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
			if (fieldImplementationMetric) {
				revEnt.setFieldImplMetricInd("Y");
			} else {
				revEnt.setFieldImplMetricInd("N");
			}

			revEnt.setTimingCode(timings[i]);

			for (int j = 0; j < fromSerials.length; j++) {
				CcttBulletinProductEntity serialEnt = new CcttBulletinProductEntity();
				serialEnt.setBulletinNum(bulletinNum);
				serialEnt.setRevId(Integer.parseInt(revision));
				serialEnt.setFromSerialNum(fromSerials[j]);
				serialEnt.setToSerialNum(toSerials[j]);
				serialEnt.setTimingCode(timings[i]);
				serialEnt.setCreatedBy(createdBy);
				serialEnt.setCreatedDate(new Date());
				serialEnt.setLastUpdatedBy(createdBy);
				serialEnt.setLastUpdatedDate(new Date());
				serialEnt.setCmplEndDate(new Date());
				ccttBulletinProductJpaRepository.save(serialEnt);
			}

			ccttBulletinRevisionJpaRepository.save(revEnt);
		}
		return 1;

	}

	public Object updateBulletin(String bulletinNum, String bulletinStatus,
			Integer bulletinTypeCode, String category, String complianceLevel,
			String createdBy, Date createdDate, Date issueDate,
			String lastUpdatedBy, Date lastUpdatedDate, Integer latestRevId,
			String remarks, Date revisionDate, Boolean significant,
			String supercededBulletinNum, Boolean trackImplimentationPlan,
			Boolean voucherProgram, Boolean fieldImplementationMetric,
			String productLine, String description, String revision,
			String[] fromSerials, String[] toSerials, Integer[] timings) {

		
		  
		  
		  CcttBulletinRevisionEntityKey ccttBulletinRevisionEntityKey=new
		  CcttBulletinRevisionEntityKey();
		  ccttBulletinRevisionEntityKey.setBulletinNum(bulletinNum);
		  ccttBulletinRevisionEntityKey.setRevId(latestRevId);
		  if(timings.length >0)
		  ccttBulletinRevisionEntityKey.setTimingCode(timings[0]);
		  CcttBulletinRevisionEntity revEnt =ccttBulletinRevisionJpaRepository.findOne(ccttBulletinRevisionEntityKey);
		 

		CcttBulletinEntity bulletinEnt = ccttBulletinJpaRepository
				.findOne(bulletinNum);
		//
		CcttProductLineEntity plEnt = ccttProductLineJpaRepository
				.findByProductLine(productLine);
		bulletinStatus = "EFFECTIVE";

		// Set Bulletin
		bulletinEnt.setBulletinNum(bulletinNum);
		bulletinEnt.setBulletinStatus(bulletinStatus);
		bulletinEnt.setBulletinTypeCode(bulletinTypeCode);
		bulletinEnt.setProductLine(plEnt.getProductLine());

		bulletinEnt.setCreatedBy(createdBy);
		bulletinEnt.setCreatedDate(new Date());
		bulletinEnt.setIssueDate(issueDate);
		bulletinEnt.setLastUpdatedBy(createdBy);
		bulletinEnt.setLastUpdatedDate(new Date());
		bulletinEnt.setLatestRevId(Integer.parseInt(revision));
		bulletinEnt.setSupercededBulletinNum(supercededBulletinNum);

		ccttBulletinJpaRepository.save(bulletinEnt);

		for (int j = 0; j < fromSerials.length; j++) {

			CcttBulletinProductEntityKey ccttBulletinProductEntityKey = new CcttBulletinProductEntityKey();
			ccttBulletinProductEntityKey.setBulletinNum(bulletinNum);
			ccttBulletinProductEntityKey.setRevId(latestRevId);
			ccttBulletinProductEntityKey.setFromSerialNum(fromSerials[j]);
			CcttBulletinProductEntity existserialEnt = ccttBulletinProductJpaRepository
					.findOne(ccttBulletinProductEntityKey);
			if (existserialEnt != null) {
				CcttBulletinProductEntity serialEnt = new CcttBulletinProductEntity();
				serialEnt.setBulletinNum(bulletinNum);
				serialEnt.setRevId(Integer.parseInt(revision));
				serialEnt.setFromSerialNum(fromSerials[j]);
				serialEnt.setToSerialNum(toSerials[j]);
				// serialEnt.setTimingCode(timings[j]);
				serialEnt.setCreatedBy(createdBy);
				serialEnt.setCreatedDate(new Date());
				serialEnt.setLastUpdatedBy(createdBy);
				serialEnt.setLastUpdatedDate(new Date());
				serialEnt.setCmplEndDate(new Date());
				ccttBulletinProductJpaRepository.save(serialEnt);
			}
		}

		ccttBulletinRevisionJpaRepository.save(revEnt);
		// }
		return 1;

	}

	public Object getBulletins(Integer bulletinTypeCode, String productLine) {
		// TODO Auto-generated method stub
		return ccttBulletinJpaRepository
				.findByBulletinTypeCodeAndProductLineOrderByBulletinNum(
						bulletinTypeCode, productLine);
	}

	public List<CcttBulletinRevisionEntity> bulletinRevisions(String bulletinNum) {
		// TODO Auto-generated method stub
		return ccttBulletinRevisionJpaRepository
				.findByCompositePrimaryKeyBulletinNum(bulletinNum);
	}

	public String bulletinCodeValues(Long code) {
		// TODO Auto-generated method stub
		return codeJpaRepository.findByCodeId(code).getCodeDescription();
	}

}