package com.ge.pw.ibct.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RevisionsDto{
	private String bulletinNum;
	private Integer timingCode;
	private Integer revisionId;
	private Date revisionDate;
	private String revisionDescription;
	private String category;
	private String complianceLevel;
	private String trackImplimentationPlan;
	public String getBulletinNum() {
		return bulletinNum;
	}
	public void setBulletinNum(String bulletinNum) {
		this.bulletinNum = bulletinNum;
	}
	public Integer getTimingCode() {
		return timingCode;
	}
	public void setTimingCode(Integer timingCode) {
		this.timingCode = timingCode;
	}
	public Integer getRevisionId() {
		return revisionId;
	}
	public void setRevisionId(Integer revisionId) {
		this.revisionId = revisionId;
	}
	public Date getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}
	public String getRevisionDescription() {
		return revisionDescription;
	}
	public void setRevisionDescription(String revisionDescription) {
		this.revisionDescription = revisionDescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComplianceLevel() {
		return complianceLevel;
	}
	public void setComplianceLevel(String complianceLevel) {
		this.complianceLevel = complianceLevel;
	}
	public String getTrackImplimentationPlan() {
		return trackImplimentationPlan;
	}
	public void setTrackImplimentationPlan(String trackImplimentationPlan) {
		this.trackImplimentationPlan = trackImplimentationPlan;
	}
	
}