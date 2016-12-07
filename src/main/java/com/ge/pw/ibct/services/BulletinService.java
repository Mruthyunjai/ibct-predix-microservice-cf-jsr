package com.ge.pw.ibct.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.neo4j.cypher.internal.compiler.v2_1.ast.rewriters.distributeLawsRewriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ge.pw.ibct.beans.CcttBulletin;
import com.ge.pw.ibct.repository.CcttBulletinJpaRepository;
import com.ge.pw.ibct.repository.CcttCrProductJpaRepository;
import com.ge.pw.ibct.repository.CcttProductLineJpaRepository;
import com.ge.pw.ibct.repository.CcttProductTypeJpaRepository;
import com.ge.pw.ibct.utils.BulletinTypes;
import com.ge.pw.ibct.entity.CcttBulletinEntity;
import com.ge.pw.ibct.entity.CcttCrProductEntity;
import com.ge.pw.ibct.entity.CcttProductTypeEntity;

@Service
public class BulletinService{
	
	
	@Autowired
	CcttBulletinJpaRepository ccttBulletinJpaRepository;
	
	
	public CcttBulletinEntity getBulletin(String bulletinNumber){
		return ccttBulletinJpaRepository.findOne(bulletinNumber);
	}
	
	public Object cancelBulletin(String bulletinNumber){
		CcttBulletinEntity bulletin = ccttBulletinJpaRepository.findOne(bulletinNumber);
		bulletin.setBulletinStatus("INEFFECTIVE");
		return ccttBulletinJpaRepository.save(bulletin);
	}
	
	public List getSupercedValues(Integer bulletinTypeCode,String productLine,String bulletinStatus ){
		return ccttBulletinJpaRepository.findByBulletinTypeCodeAndProductLineAndBulletinStatus(bulletinTypeCode, productLine, bulletinStatus);
	}


	public List<String> getBulletinTypeValues() {
		List<CcttBulletinEntity> bulletinTypeValues = ccttBulletinJpaRepository.findAll();
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

	/*public List<String> getBulletinTypeValues(){
		List<CcttBulletinEntity> bulletinTypeValues = ccttBulletinJpaRepository.findAll();
	}*/
	
	

		/*@Autowired
		ComtApplicationCodeJpaRepository codeJpaRepository;

		public Map<Integer, Object> getCategoryAndTimings(String codeType) {
			List<ComtApplicationCodeEntity> categories = codeJpaRepository
					.findByCodeType(codeType);

			Map<Integer, Object> idDescriptionMap = new HashMap<Integer, Object>();
			for (ComtApplicationCodeEntity codes : categories) {
				idDescriptionMap.put(
						codes.getCodeId(),
						new Object[] { codes.getCodeName(),
								codes.getCodeDescription() });
			}
			return idDescriptionMap;
		}*/



		@Autowired
		CcttCrProductJpaRepository ccttcrRepository;
		@Autowired
		CcttProductTypeJpaRepository ccttProductTRepository;

		public List<String> getSerialValues(String productLine,Integer bulletinTypeCode) {
			List<String> serials = new ArrayList<String>();
			List<CcttCrProductEntity> cccpList = ccttcrRepository
					.findByProductLine(productLine);

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


		public Object getCategoryAndTimings(String codeType) {
			// TODO Auto-generated method stub
			return null;
		}
}