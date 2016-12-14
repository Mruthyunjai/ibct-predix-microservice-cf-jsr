/**
 * 
 */
package com.ge.pw.ibct.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.ge.pw.ibct.entity.ComtApplicationCodeEntity;

/**
 * @author MV355484
 *
 */
public class TimingDto {

	private long codeID;
	private String codeDescription;
	private String codeName;
	public long getCodeID() {
		return codeID;
	}

	public void setCodeID(long codeID) {
		this.codeID = codeID;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	/**
	 * Constructor for TimingDto Dummy
	 */
	public TimingDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor to get only timing code and values
	 * @param code
	 */
	public TimingDto(List<ComtApplicationCodeEntity> code) {
		// TODO Auto-generated constructor stub
	}
	
	public List<TimingDto> getTimingCodesFromEntity(List<ComtApplicationCodeEntity> code){
		List<TimingDto> lstTimingDto = new ArrayList<TimingDto>();
		TimingDto objTimingDto;
		for (ComtApplicationCodeEntity comtApplicationCodeEntity : code) {
			objTimingDto = new TimingDto();
			objTimingDto.codeID = comtApplicationCodeEntity.getCodeId();
			objTimingDto.codeDescription = comtApplicationCodeEntity.getCodeDescription();
			objTimingDto.codeName = comtApplicationCodeEntity.getCodeName();
			lstTimingDto.add(objTimingDto);
		}
		return lstTimingDto;
		
	}
}
