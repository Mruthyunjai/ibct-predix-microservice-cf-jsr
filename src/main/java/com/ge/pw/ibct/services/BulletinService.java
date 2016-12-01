package com.ge.pw.ibct.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ge.pw.ibct.beans.CcttBulletin;
import com.ge.pw.ibct.repository.CcttBulletinJpaRepository;
import com.ge.pw.ibct.entity.CcttBulletinEntity;

@Service
public class BulletinService{
	
	
	@Autowired
	
	CcttBulletinJpaRepository ccttBulletinJpaRepository;
	public List<CcttBulletin> getBulletins(String bullNum) {

		List<CcttBulletinEntity> entirylist = ccttBulletinJpaRepository.findByBulletinNum(bullNum);
				
		List<CcttBulletin> beanList = new ArrayList<CcttBulletin>();

		for (CcttBulletinEntity entity : entirylist) {
			if (entity != null) {
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setMatchingStrategy(
						MatchingStrategies.STRICT);
				CcttBulletin order = modelMapper.map(entity, CcttBulletin.class);
				beanList.add(order);
			}
		}
		return beanList;
		
	}
}